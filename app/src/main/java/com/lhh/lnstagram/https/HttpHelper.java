package com.lhh.lnstagram.https;


import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.lhh.lnstagram.base.BaseApplication;
import com.lhh.lnstagram.https.gson.GsonAdapter;
import com.lhh.lnstagram.https.rx.RxSchedulers;
import com.lhh.lnstagram.mvvm.base.BaseConfig;
import com.lhh.lnstagram.mvvm.util.TimeUtil;
import com.lhh.lnstagram.sp.HttpCacheSP;
import com.lhh.lnstagram.sp.LanguageSP;

import java.io.File;
import java.util.concurrent.TimeUnit;

import guide.util.StringUtil;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Http请求帮助类
 */
public class HttpHelper {

    private Retrofit mRetrofit = null;
    private BaseApiService mBaseApiService = null;

    private static class SingletonHolder {
        private static final HttpHelper INSTANCE = new HttpHelper();
    }

    public static HttpHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            synchronized (HttpHelper.class) {
                if (mRetrofit == null) {
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger())
                            .setLevel(HttpLoggingInterceptor.Level.BODY);
                    Interceptor headerInterceptor = chain -> {
                        Request request = chain.request().newBuilder()
                                .header("Content-Type", "application/json;charset=UTF-8")
                                .header("Language", LanguageSP.getInstance().getCurrentLanguageCode())
                                .build();
                        return chain.proceed(request);
                    };
                    Cache cache = new Cache(new File(BaseApplication.getInstance().getCacheDir(), "HttpCache"), 1024 * 1024 * 10);

                    OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(loggingInterceptor)
                            .addInterceptor(headerInterceptor)
                            .connectTimeout(BaseConfig.HTTP_TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(BaseConfig.HTTP_TIME_OUT, TimeUnit.SECONDS)
                            .readTimeout(BaseConfig.HTTP_TIME_OUT, TimeUnit.SECONDS);

                    TLS12SocketClient.CompatTLS12OkHttpClient(mOkHttpClientBuilder);

                    Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create(GsonAdapter.buildGson()))
                            .baseUrl(BaseConfig.HTTP_HOST);

                    mRetrofit = retrofitBuilder.client(mOkHttpClientBuilder.build()).build();
                }
            }
        }
        return mRetrofit;
    }

    public BaseApiService getApiService() {
        if (mBaseApiService == null) {
            synchronized (HttpHelper.class) {
                if (mBaseApiService == null) {
                    mBaseApiService = getRetrofit().create(BaseApiService.class);
                }
            }
        }
        return mBaseApiService;
    }

    public Disposable reqHttp(BaseReq baseReq, @NonNull Observer<BaseResp> onChanged) {
        if (baseReq.isShowCache()) {
            String oldJson = HttpCacheSP.getInstance().getHttpCache(baseReq.getCacheKey());
            // 有缓存，先返回缓存
            if (StringUtil.isNotEmpty(oldJson)) {
                BaseResp result = new BaseResp(oldJson);
                result.setCache(true);
                onChanged.onChanged(result);

                // 限制请求
                if ("/common/countryCode".equals(baseReq.getPath())) {
                    long blockNetworkTime = 24 * 60 * 60; // 24小时有效
                    blockNetworkTime = 60; // 一分钟有效
                    long lastCacheTime = HttpCacheSP.getInstance().getCountryCodeCacheTime();
                    if (TimeUtil.getCurrentTime4Local() < lastCacheTime + blockNetworkTime) {
                        // 当前缓存有效，不请求网络
                        return null;
                    }
                }
            } else {
                // 国家编码，应用层的缓存
//                if ("/common/countryCode".equals(baseReq.getPath())) {
//                    String localJson = FileUtil.readAssetFile(BaseApplication.getInstance(), "region.txt");
//                    BaseResp result = new BaseResp(localJson);
//                    result.setCache(true);
//                    onChanged.onChanged(result);
//                }
            }
        }


        Flowable<ResponseBody> flowable;
        if (baseReq.isGetMethod()) {
            flowable = getApiService().get(baseReq.getUrl(), baseReq.getStrBodyMap(), baseReq.getHeader());
        } else {
            flowable = getApiService().postJson(baseReq.getUrl(), baseReq.getBody(), baseReq.getHeader());
        }
        if (baseReq.getTimeOut() > 0) {
            flowable.timeout(baseReq.getTimeOut(), TimeUnit.SECONDS);
        }
        return flowable.compose(RxSchedulers.io_main())
                .subscribeWith(new BaseRxSubscriber(baseReq, onChanged));
    }

}

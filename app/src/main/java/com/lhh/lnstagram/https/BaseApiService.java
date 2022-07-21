package com.lhh.lnstagram.https;

import com.lhh.lnstagram.filehttps.AzureRequestBody;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 *
 */
public interface BaseApiService {

    @POST
    Flowable<ResponseBody> postJson(@Url String url, @Body RequestBody body, @HeaderMap Map<String, String> headers);

    @POST
    Flowable<ResponseBody> postForm(@Url String url, @Body RequestBody body, @HeaderMap Map<String, String> headers);

    @GET
    Flowable<ResponseBody> get(@Url String url, @QueryMap Map<String, String> map, @HeaderMap Map<String, String> headers);

    @PUT
    Observable<AzureRequestBody> put(@Url String url, @Body RequestBody body, @HeaderMap Map<String, String> headers);

}

package com.lhh.lnstagram.https.rx;

import com.google.gson.JsonParseException;
import com.lhh.lnstagram.R;
import com.lhh.lnstagram.https.BaseReq;
import com.lhh.lnstagram.https.play.PayReq;
import com.lhh.lnstagram.mvvm.util.NetworkUtil;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import guide.util.StringUtil;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.HttpException;

/**
 * @author tqzhang
 */
public abstract class RxSubscriber<T> extends DisposableSubscriber<T> {

    private BaseReq baseReq;
    private PayReq payReq;

    public RxSubscriber(BaseReq baseReq) {
        this.baseReq = baseReq;
    }

    public RxSubscriber(PayReq payReq) {
        this.payReq = payReq;
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLoading();
        if (!NetworkUtil.isNetworkAvailable()) {
            onNoNetWork();
            cancel();
            return;
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        String message = null;
        String tipMsg = StringUtil.getString(R.string.common_txt_request_not_completed);
        if (e instanceof UnknownHostException) {
            message = "UnknownHostException:" + e.getMessage();
        } else if (e instanceof HttpException) {
            message = "HttpException:code=" + ((HttpException)e).code() + ",response=" + ((HttpException)e).response().toString();
        } else if (e instanceof SocketTimeoutException) {
            tipMsg = StringUtil.getString(R.string.common_txt_net_timeout_econection_exception);
            message = "SocketTimeoutException:" + e.getMessage();
        } else if (e instanceof JsonParseException || e instanceof JSONException) {
            message = "JSONException" + e.getMessage();
        } else if (e instanceof ConnectException) {
            tipMsg = StringUtil.getString(R.string.common_txt_net_timeout_econection_exception);
            message = "ConnectException" + e.getMessage();
        } else {
            message = e.getMessage();
        }

        if (baseReq != null) {

        } else if (payReq != null) {

        }

        onFailure(tipMsg);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }




    protected void showLoading() {

    }

    protected void onNoNetWork() {

    }

    /**
     * success
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * failure
     *
     * @param msg
     */
    public abstract void onFailure(String msg);
}

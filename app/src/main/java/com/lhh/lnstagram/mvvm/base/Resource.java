package com.lhh.lnstagram.mvvm.base;

/**
 * Created by changxing on 2017/12/3.
 */

public class Resource<T> {

    private boolean fromNet;
    private int code;
    private String msg;
    private T data;

    public Resource(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Resource(int code, String msg, T data, boolean fromNet) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.fromNet = fromNet;
    }

    public static <T> Resource<T> error(T data) {
        return new Resource<>(-1, null, data);
    }

    public static <T> Resource<T> error(T data, String message) {
        return new Resource<>(-1, message, data);
    }

    public static <T> Resource<T> loading(T data) {
        return new Resource<>(0, null, data);
    }

    public static <T> Resource<T> success(T data, boolean fromNet) {
        return new Resource<>(1, null, data, fromNet);
    }

    public static <T> Resource<T> moreSucceed(T data) {
        return new Resource<>(2, null, data);
    }

    public boolean isOk() {
        return code == 1 || code == 2;
    }

    public boolean isFromNet() {
        return fromNet;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

}

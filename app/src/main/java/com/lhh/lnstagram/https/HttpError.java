package com.lhh.lnstagram.https;

/**
 * Created by XieWenJun on 2018/12/14.
 */
public class HttpError {


    // 成功
    public static final int SUCCESS = 200;


    // 本地异常
    public static final int ERROR_LOCAL_EXCEPTION = -1;
    // 没有网络
    public static final int ERROR_LOCAL_NO_NETWORK = -2;


    // 登录需要验证码
    public static final int USER_LOGIN_NEED_IMAGE_CODE = 1045;
    // 新设备
    public static final int USER_LOGIN_NEW_DEVICE = 23021;

    // 第三方登录，用户未注册
    public static final int USER_PLATFORM_LOGIN_NOT_REGISTER = 20007;
    // 手机号已注册，也绑定这类第三方账号
    public static final int USER_PLATFORM_OLD_USER_AND_HAD_BIND = 1048;
    // 手机号未注册
    public static final int USER_PLATFORM_NEW_USER = 1047;


    // TOKEN异常
    public static final int USER_TOKEN_ERROR = 21327;
    // 当前为游客模式
    public static final int USER_ERROR_VISITOR = 401;


    // 用户已经不在群了
    public static final int USER_NOT_IN_THIS_GROUP = 20008;


    // 钱包支付密码错误
    public static final int WALLET_PWD_ERR = 22001;
    // 钱包支付可以借款
    public static final int WALLET_CAN_OVERDRAFT = 22002;


}
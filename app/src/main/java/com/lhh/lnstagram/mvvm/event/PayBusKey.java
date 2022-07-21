package com.lhh.lnstagram.mvvm.event;

/**
 * Created by XieWenJun on 2019/12/17.
 */
public class PayBusKey {

    public static String PAY_GET_TOKEN = "pay_get_token";

    public static String PAY_CHOOSE_COUNTRY = "pay_choose_country";


    public static String PAY_NOTICE_SCAN_CODE_PROCESS = "pay_notice_scan_code_process";
    public static String PAY_NOTICE_SCAN_CODE_RESULT = "pay_notice_scan_code_result";


    public static String PAY_WHITE_LIST = "pay_white_list";

    public static String PAY_PAY_SUBMIT_ORDER = "pay_pay_submit_order";

    public static String PAY_WIFI_FINDER_SUBMIT_ORDER = "pay_wifi_finder_submit_order";

    public static String PAY_WIFI_FINDER_NO_BALANCE = "pay_wifi_finder_no_balance";//提示余额弹窗

    // 从第三方拉起支付,内部拉起支付
    public static String PAY_LAUNCH_FROM_SPLASH = "pay_launch_from_splash";
}

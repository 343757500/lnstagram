/**
 * Copyright 2013-2033 Xia Jun(3979434@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * **************************************************************************************
 * *
 * Website : http://www.farsunset.com                           *
 * *
 * **************************************************************************************
 */
package com.lhh.lnstagram.mvvm.base;


import com.lhh.lnstagram.base.BaseApplication;

/**
 * 常量
 */
public class BaseConfig {

    // BaseApplication动态修改
    public static int VERSION_CODE = 1;
    public static String VERSION_NAME = "1.0.0";
    public static String APPLICATION_ID = "";
    public static boolean isInner = false;
    // BaseApplication动态修改
    public static int ENV_TYPE = 1;
    public static int TCP_PORT = 0;
    public static String TCP_HOST = "";
    public static String HTTP_HOST = "https://apitest.im.sasai.mobi:44381";
    public static String HTTP_HOST_WATCH = "";
    public static String HTTP_HOST_WATCH_REPORT = "";
    public static String HTTP_HOST_LIVE = "";
    public static String HTTP_HOST_POD_CAST = "";
    public static String HTTP_HOST_TEAM_TALK_RATING = "";
    public static String GAME_HTTP_HOST = "";
    public static String ENV_HOST_OPEN = "";
    public static String ENV_HOST_MERCHANT = "";
    public static String IP_HTTP_HOST = "";
    public static String FILE_HOST = "";
    public static String PAY_HOST = "";
    public static String PAY_HOST_H5 = "";
    public static String PAY_HOST_PARAM = "?accountToken=%s&ver=%s&client_id=%s";


    // 加密KEY
    public static String KEY_HTTP_AES_ENCRYPT_IV = "";
    public static String KEY_HTTP_AES_ENCRYPT_SIGN = "";
    public static String KEY_HTTP_RSA_ENCRYPT_PUBLIC_KEY = "";
    public static String KEY_HTTP_RSA_DECODE_AES_IV = "";
    // 朋友圈文件服务器加密KEY
    public static String KEY_POST_FILE_RSA_ENCRYPT_PUBLIC_KEY = "";
    public static String KEY_POST_FILE_AES_ENCRYPT_IV = "";

    // 钱包2.0
    public static String KEY_PAY_RSA_ENCRYPT_PUBLIC = "";
    public static String KEY_PAY_MD5_APP_SALT = "";
    public static String KEY_PAY_MD5_SERVICE_SALT = "";
    public static String KEY_PAY_AES_ENCRYPT_IV = "";
    public static String KEY_PAY_AES_DECODE_IV = "";
    public static String KEY_PAY_AES_DECODE_KEY = "01234567890123456789012345678900";//扫码登录用到的key


    //    //音视频地址
//    public static String CSM_HTTP_HOST_URL = "";
//    public static String CSM_HTTP_HOST_IP = "";///最终拿这个ip
//    //    public static String CSM_HTTP_HOST_IP_PRO = "102.133.163.174";//生产
////    public static String CSM_HTTP_HOST_IP_DEV = "40.83.98.103";//开发
////    public static String CSM_HTTP_HOST_IP_TEST = "52.229.169.87";//测试
//    public static short CSM_HTTP_HOST_PORT = 14888;
    public static String ENV_HOST_AV_COLLECT_URL = "";//上报

    // File参数
    public static String AVATAR_HOST = "";
    public static String AVATAR_THUMB_HOST = "";
    public static String FILE_DOWNLOAD_IMAGE = "";
    public static String FILE_DOWNLOAD_HOST = "";

    //朋友圈文件
    public static String MOMENT_FILE_DOWNLOAD_HOST = "";
    public static String POST_FILE_HOST = "";
    public static int POST_SUPPORT_AUDIO_DURATION = 60;//音频最大支持60s

    //watch file
    public static String ENV_HOST_FILE_WATCH = "";

    // Http参数
    public static String APP_TYPE = "2"; // 1IOS 2Android
    public static String OS_TYPE = "1"; // 1Android(别问我为什么)
    public static String HTTP_MEDIA_TYPE_JSON = "Content-Type, application/json";
    public static int HTTP_TIME_OUT = 30; // Http超时30秒
    public static int HTTP_UPLOAD_MOBILE_PAGE_SIZE = 100; // 每次上传通讯录500人


    // TCP参数
    public static int SOCKET_SEND_BUFFER_SIZE = 2048;
    public static int SOCKET_READ_BUFFER_SIZE = 2048;
    public static int SOCKET_CONNECTION_TIME_OUT = 10 * 1000; // 连接超时，10秒->15秒->10秒（20200310改为10秒）
    public static int SOCKET_CONNECTION_TIME_OUT_WIFI = 5 * 1000; // 连接超时，wifi情况下5秒（20200310新增）
    public static int SOCKET_CONNECTION_TIME_OUT_CHECK_INTERVAL = 1 * 1000; // 连接超时检查时间
    public static int SOCKET_WRITE_TIMEOUT = 1000;// 写入网卡超时，2秒（第一次1000毫秒，第二次2000毫秒）
    public static int SOCKET_READ_TIME_OUT = 10; // ACK读取超时，10秒 -> 60秒  -> 10秒（20200310改为10秒）
    public static int SOCKET_READ_TIME_OUT_CHECK_INTERVAL = 2 * 1000; // 检查ACK读取超时的循环时间，2秒

    public static int HEART_BEAT_CHECK_INTERVAL = 2 * 1000; // 心跳检查间隔，2秒
    public static int HEART_BEAT_SEND_INTERVAL = 58 * 1000; // 心跳发送间隔
    public static int HEART_BEAT_TIME_OUT = 120 * 1000; // 心跳超时，2个心跳时间+1秒
    public static int HEART_BEAT_SEND_INTERVAL_QUICK = 5 * 1000; // 心跳发送间隔
    public static int HEART_BEAT_TIME_OUT_QUICK = 10 * 1000; // 10秒都没收到就断开重连(已经发过2次心跳了)

    public static int CHAT_REBUILD_TIME_OUT = 10 * 60; // 消息Resend还是Rebuild的时间，600秒（超过10分钟重新生成MsgId当新消息，10分钟内就保持原理MsgId）
    public static int CHAT_RESEND_TIME_OUT = 10 * 60; // 消息发送失败，120秒会自动重发（20200310改为10分钟）


    // 固定路径
    public static String APP_FAQ_URL = ""; // 常见问题
    public static String APP_FEEDBACK_URL = ""; // 反馈地址
    public static String WALLET_FEEDBACK_URL = ""; // 钱包反馈地址
    public static String WALLET_OVERDRAFT_AGREEMENT = ""; //overdraft
    public static String WATCH_APP_FEEDBACK_URL = ""; // Watch反馈地址
    public static String WATCH_APP_PUBLIC_URL = ""; // Watch_Public反馈地址
    public static String HTTP_ALL_ICONS_URL = "http://52.139.170.62:8080/allicons.json?url="; // 获取URL图标
    public static String MOMENT_REPORT_AD_URL = ""; // 广告举报地址
    public static String MOMENT_REPORT_URL = ""; // 朋友圈举报地址
    public static String MOMENT_USER_REPORT_URL = ""; // 朋友圈用户举报地址

    public static String WALLET_VIEW_BILL = ""; // 账单地址
    public static String WALLET_TRANSACTION_HISTORY = ""; // 转账详情
    public static String APP_PAY_TERM_URL = ""; //钱包二期条款
    public static String APP_PAY_KYC = ""; //钱包二期KYC
    public static String ENV_HOST_PAY_TRANSACTION = ""; //钱包二期转账
    public static String ENV_HOST_PAY_STATEMENT = ""; //钱包二期statement


    public static String ENV_HOST_PODCAST_H5 = ""; //PodCast_H5

    public static String URL_PRIVACY_POLICY = ""; // 注册协议
    public static final String APP_SPREAD_URL = "http://spread.sasai.mobi:8768/im"; // 跳转应用市场（固定环境）
    public static String APP_CONTACT_US_URL = ""; // 联系我们
    public static final String APP_QR_PAY_URL = "http://qr.im.sasai.mobi:8768/im"; // 二维码

    //钱包1.0
    public static String REQUEST_WALLET_STATEMENT_URL = "http://217.15.118.15:8184/ecocash/statement?userId="; // Request wallet statemen
    public static String REQUEST_HELP_URL = "";
    public static String REQUEST_WALLET_LIMITS_URL = "";//获取钱包限额信息
    public static String REQUEST_WALLET_LOCATEUS_URL = "";//获取不同国家agent详细资料


    public static final String MASTER_CARD_PAY_CALL_BACK_URL = "sasai://masterCardLinkCallBack";
    public static final String MASTER_CARD_PAY_CALL_BACK_URL_V2 = "sasai://masterCardLinkCallBack/v2";

    public static final String OPEN_WATCH_URL = "sasai://com.gigaiot.sasa.open/watch";
    public static final String OPEN_PODCAST_URL = "sasai://com.gigaiot.sasa.open/podCast";
    public static final String OPEN_PAY_URL = "sasai://com.gigaiot.sasa.open/sdk?key=PaySdk&PaySdkId=1";
    public static final String OPEN_MOMENT_URL = "sasai://com.gigaiot.sasa.open/moment";
    public static final String OPEN_SASAI_URL = "sasai://com.gigaiot.sasa.open/main";

    public static String HTTP_HOST_DISCOVERY_URL = "";
    public static String HTTP_HOST_DISCOVERY_URL_BASE = "";

    public static String HTTP_HOST_OPEN_FILE_URL = "";

    public static String HTTP_HOST_WORK_ORDER_LIST_DETAIL = "";

    //直播
    public static String LIVE_URL_PRIVACY_POLICY = "";//直播隐私协议
    public static String LIVE_URL_TRANSACTION_DETAIL = "";//星星交易详情H5
    public static String LIVE_URL_TV_CHANNEL_APP = ""; // live tv
    //Moment广告
    public static String MOMENT_URL_AD_REPORT = "";//广告报表H5
    public static String MOMENT_URL_AD_PAY_DETAIL = "";//广告交易详情H5
    public static String MOMENT_URL_POLICY = "";//隐私协议

    //发现页面游戏url
    // public static final String DISCOVERY_GAMES = "http://139.159.141.45/sasai-client/#/systemsasailogin";

    // 聊天消息的撤回
    public static int CHAT_RECALL_TIME_OUT = 120; // 消息可撤回时间，120秒

    public static String NOTIFYCATION_CHANNEL_ID = "";

    // 普通用户ID最小长度(不小于100000000)
    public static int FRIEND_ID_MIN_LENGTH = 9;
    public static int MESSAGE_PAGE_SIZE = 100;

    public static boolean formLogin = false; // 来自登录页面，把置顶的好友和群设置在最近会话页面
    public static boolean formSearch = false;


    public static int miniKeyboardHeight = 300;

    public static int SUPPORT_FILE_SIZE = 100 * 1024 * 1024;
    public static int SUPPORT_MOMENT_VIDEO_FILE_SIZE = 200 * 1024 * 1024;
    public static int WATCH_SUPPORT_FILE_SIZE = 500 * 1024 * 1024;
    //分片下载 每片大小
    public static int DOWNLOAD_PART_SIZE = 512 * 1024;
    //分片上传 每片大小
    public static int UPLOAD_PART_SIZE = 512 * 1024;
    //分片上传 每片大小
    public static int UPLOAD_WATCH_PART_SIZE = 512 * 1024;

    public static int TRANSFER_MESSAGE_MAX_MESSAGE_SELECT = 10;
    public static int TRANSFER_MESSAGE_MAX_MEMBER_SELECT = 5;

    // 基本固定的功能点
    public static boolean uploadTcpLog = false;
    public static boolean uploadAvLog = true;
    public static boolean localFileLog = false; // 一般都改成false，不开本地日志

    public static boolean openWifiFinder = true;
    public static boolean offlineUseTcp = true; // 时候用Tcp获取离线消息

    public static boolean isNeedGroupVideo = true;//false时候是不需要的


    public static boolean isSupportPay = true;//是否支持打开一期钱包页面

    public static boolean isSuportMoreThan5 = true;

    public static boolean moreThan5WantToAddIcon = true;

//    public static boolean isEncryptOTP = true;//是否开启otp加密

    public static boolean isSupportWatch = true;//是否开启Watch

    public static boolean isSupportWatchUpload = true;//是否开启Watch用户上传功能

    public static boolean isSupportPodCast = true;//是否开启PodCast

    public static boolean isSupportWatchMonitor = true;//是否开启Watch上报和上传视频禁言判断功能

    public static boolean isSupportShareH5 = true;//是否开启分享功能

    public static boolean isSupportTopNew = true;//是否支持置顶替换功能

    public static boolean isSupportWatchAndWin = true;//是否支持观看有奖功能

    public static boolean isSupportWifiFinderLog = false;//是否支持wifi finder log

    public static boolean isSupportMomentTrending = true;//是否支持moment 热搜trending

    public static boolean isSupportMomentLive = true;//是否开启Moment直播

    public static boolean isSupportLiveShare = true;//是否开启直播分享

    public static boolean isSupportLiveDownload = true;//是否开启直播下载

    public static boolean isSupportCustomerService = true;//是否支持 CustomerService

    public static boolean isSupportPublicAccount3 = true;//是否开启公众号3期

    public static boolean isSupportWatchKids = false;//是否开启Watch儿童模式/首页隐私协议弹框

    public static boolean isSupportObsLive = false; // 是否开启obs第三方平台拉流直播入口

    public static boolean isSupportMomentAd = true;//是否开启朋友圈广告

    public static boolean isSupportMomentAzure = true;//是否开启朋友圈Azure上传

    public static boolean isSupportWebLive = true; // 是否开启web第三方平台拉流直播入口

    public static String HTTP_REMIT_POLICY_EUROPE_URL = "";
    public static String HTTP_REMIT_POLICY_AFRICA_URL = "";

}

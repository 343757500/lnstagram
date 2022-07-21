package com.lhh.lnstagram.mvvm.event;

public class WatchBusKey {


    //main页面切换
    public static final String WATCH_MAIN_TAB_SWITCH = "watch_main_tab_switch";

    // 从第三方拉起视频详情
    public static final String WATCH_LAUNCH_FROM_SPLASH = "watch_launch_from_splash";

    // 修改资料
    public static final String WATCH_USER_MODIFY_INFO_SUCCESS = "watch_user_modify_info_success";

    // 选择好设置偏好
    public static final String WATCH_SELECT_TAG = "watch_select_tag";
    // 选择分类
    public static final String WATCH_SELECT_CATEGORY = "watch_select_category";

    // 消息未读数
    public static final String WATCH_NOTIFICATION_NUMBER = "watch_notification_number";

    // 清空所有消息
    public static final String WATCH_NOTIFICATION_DEL_ALL = "watch_notification_del_all";

    // 创建相册成功
    public static final String WATCH_CREATE_ALBUM_SUCCESS = "watch_create_album_success";

    // 创建作品成功
    public static final String WATCH_CREATE_WORK_SUCCESS = "watch_create_work_success";

    //wish做了增删操作
    public static final String WATCH_WISH_UPDATE = "watch_wish_update";

    //album做了增删操作
    public static final String WATCH_ALBUM_UPDATE = "watch_album_update";

    //进入视频详情时发现视频已经被删除
    public static final String WATCH_VIDEO_DELETED = "watch_video_deleted";

    //全屏播放视频点击订阅按钮
    public static final String WATCH_VIDEO_FULL_SCREEN_SUBSCRIPTION = "watch_video_full_screen_subscription";

    //全屏播放视频点击抽奖按钮
    public static final String WATCH_VIDEO_FULL_SCREEN_SPIN_AND_WIN = "watch_video_full_screen_spin_and_win";

    //悬浮窗全屏播放
    public static final String WATCH_VIDEO_FULL_SCREEN = "watch_video_full_screen";

    //AlbumDetail修改成功
    public static final String WATCH_ALBUM_DETAIL_UPDATE = "watch_album_detail_update";

    //关注有更新
    public static final String WATCH_FOLLOW_UPDATE = "watch_follow_update";

    //选择可见/不可见好友列表
    public static final String WATCH_PRIVACY_FOLLOW_LIST = "watch_privacy_follow_list";
    //设置可见/不可见好友列表
    public static final String WATCH_SETTING_PRIVACY = "watch_setting_privacy";

    //下载文件异常重新下载
    public static final String WATCH_VIDEO_DOWNLOAD_AGAIN = "watch_video_download_again";

    public static final String WATCH_PAY_PAY_SUBMIT_ORDER = "watch_pay_pay_submit_order";

    //支付方式
    public static final String WATCH_PAYMENT_METHOD = "watch_payment_method";

    //更新Watch View Count
    public static final String WATCH_VIEW_COUNT_UPDATE = "WATCH_VIEW_COUNT_UPDATE";

    //刷新Watch主页
    public static final String POST_REFRESH_WATCH_MAIN = "post_refresh_watch_main";

    //刷新Watch主页分类列表
    public static final String POST_REFRESH_WATCH_MAIN_CATEGORIES = "post_refresh_watch_main_categories";

    //更新视频页关注状态
    public static final String WATCH_REFRESH_FOLLOW = "watch_refresh_follow";

    //直播结束通知
    public static final String WATCH_LIVE_END = "watch_live_end";

    //主播直播本地视图
    public static final String WATCH_SETUP_LOCAL_VIDEO = "watch_live_setupLocal_Video";

    //watch支付订阅结果
    public static final String WATCH_SUB_PAY_RESULT = "watch_sub_pay_result";

    //直播间网络状态
    public static final String WATCH_LIVE_NETWORK_STATUS = "watch_live_network_status";


    //青少年模式切换重启
    public static final String WATCH_MAIN_RESTART = "watch_main_restart";

    //获取视频总数
    public static final String WATCH_GET_TOTAL_VIDEOS = "watch_get_total_videos";

    //账号封禁
    public static final String WATCH_MAIN_BLOCK= "watch_main_block";

    //封禁申诉
    public static final String WATCH_APPEAL_APPLY_SUCCESS= "watch_appeal_apply_success";

    //打开推荐视频
    public static final String WATCH_OPEN_RECOMMENDED_VIDEO = "watch_open_recommended_video";
}

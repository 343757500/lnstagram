package com.lhh.lnstagram.mvvm.event;


public class PostBusKey {
    //收到朋友圈聊天通知
    public static final String POST_NEW_NOTICE_PUBLIC_ACCOUNT_CHAT = "post_new_notice_public_account_chat";
    //收到朋友圈新通知
    public static final String POST_NEW_NOTICE = "post_new_notice";
    //收到好友发布新朋友圈的通知
    public static final String POST_NEW_MOMENT = "POST_NEW_MOMENT";
    //收到红点推送
    public static final String POST_NEW_TREND_NOTIFY = "POST_NEW_TREND_NOTIFY";
    //发布朋友圈通知
    public static final String POST_SEND_MOMENT = "post_send_moment";
    //清空朋友圈消息通知
    public static final String POST_EMPTY_MESSAGE = "post_empty_message";
    //刷新朋友圈
    public static final String POST_REFRESH_MOMENT = "post_refresh_moment";
    //置顶成功刷新朋友圈
    public static final String POST_REFRESH_MOMENT_TOP = "post_refresh_moment_top";
    //首次加载朋友圈
    public static final String POST_REFRESH_FIRST_MOMENT = "post_refresh_first_moment";
    //朋友圈视频编辑完成通知
    public static final String POST_MOMENT_VIDEO_EDIT_UPDATE = "post_moment_video_edit_update";
    //朋友圈发送上传进度
    public static final String POST_SEND_PROGRESS = "post_send_progress";
    //朋友圈发送成功
    public static final String POST_SEND_SUCCESS = "post_send_success";
    //朋友圈发送上传失败
    public static final String POST_SEND_ERROR = "post_send_error";
    //朋友圈广告发送成功
    public static final String POST_SEND_AD_SUCCESS = "post_send_ad_success";
    //朋友圈广告上传失败
    public static final String POST_SEND_AD_ERROR = "post_send_ad_error";
    //广告恢复成功
    public static final String POST_AD_RESUME_OK = "post_ad_resume_ok";
    //朋友圈发送本地文件被删除提示
    public static final String POST_SEND_ERROR_FILES_LOST = "post_send_error_files_lost";
    //朋友圈详情点赞
    public static final String POST_MOMENT_DETAIL_LIKE = "post_moment_detail_like";
    //正在关注的人总数
    public static final String POST_FOLLOWING_LIST_COUNT = "post_following_list_count";
    //关注我的人总数（粉丝总数）
    public static final String POST_FOLLOWER_LIST_COUNT = "post_follower_list_count";
    //首页回复评论/回复回复
    public static final String POST_REPLY_COMMENT = "POST_REPLY_COMMENT";
    //回复列表删除后更新详情页面
    public static final String POST_REPLY_COMMENT_DEL = "POST_REPLY_COMMENT_DEL";
    //更新最新评论
    public static final String POST_COMMENT_UPDATE_NEWEST = "POST_COMMENT_UPDATE_NEWEST";
    //删除朋友圈通知
    public static final String POST_MOMENT_DEL = "POST_MOMENT_DEL";
    //设置朋友圈展示时长
    public static final String POST_MOMENT_SETTING_VIEWABLE = "POST_MOMENT_SETTING_VIEWABLE";
    //选择可见/不可见好友列表
    public static final String POST_MOMENT_PRIVACY_FOLLOW_LIST = "POST_MOMENT_PRIVACY_FOLLOW_LIST";
    //设置可见/不可见好友列表
    public static final String POST_MOMENT_SETTING_PRIVACY = "POST_MOMENT_SETTING_PRIVACY";
    //回复评论时计算列表滑动时需要的一个参数,位置
    public static final String POST_MOMENT_ITEM_MOVE_POSITION = "POST_MOMENT_ITEM_MOVE_POSITION";
    //刷新朋友圈通知
    public static final String POST_MOMENT_NOTIFICATION_UPDATE = "POST_MOMENT_NOTIFICATION_UPDATE";
    //表情输入框展开/隐藏
    public static final String POST_EMOJI_KEYBOARD_STATUS = "post_emoji_keyboard_status";
    //用户朋友圈列表滑动
    public static final String POST_USER_LIST_SCROLL = "post_user_list_scroll";
    //用户朋友圈数据更新
    public static final String POST_USER_INDEX_UPDATE = "post_user_index_update";
    //用户触发发布新的朋友圈，点击发布朋友圈按钮的通知
    public static final String POST_USER_NEW_RELEASE_MOMENT = "post_user_new_release_moment";

    // 发布朋友圈选表情
    public static final String POST_SELECT_FEELING_RESULT = "post_select_feeling_result";
    // 发布朋友圈选朋友
    public static final String POST_SELECT_FRIEND_RESULT = "post_select_friend_result";
    // 管理员选中的数据
    public static final String POST_SELECT_ADMINISTRATOR_SELECTED_DATA = "post_select_administrator_selected_data";

    //自动播放视频
    public static final String POST_LIST_AUTO_PLAY_VIDEO = "post_list_auto_play_video";

    //切换Public Account和普通账号
    public static final String POST_PUBLIC_ACCOUNT_SWITCH = "post_public_account_switch";

    //获得活跃用户标志消息
    public static final String POST_UPDATE_ACTIVE_USER = "post_update_active_user";

    // 从第三方拉起详情
    public static final String POST_LAUNCH_FROM_SPLASH = "post_launch_from_splash";

    // 直播相关 编辑直播
    public static final String POST_MODIFY_LIVE_BROADCAST = "post_modify_live_broadcast";
    //直播编辑 朋友圈列表 包括个主页 和不是个人页
    public static final String POST_REFRESH_MOMENT_DATA = "post_refresh_moment_data";

    //屏蔽的标志信息
    public static final String POST_REFRESH_BLOCK = "post_refresh_block";

    public static final String POST_ADD_HIDE_MOMENT = "post_add_hide_moment";

    //不让他人看自己朋友圈的标志
    public static final String POST_HIDE_MY_MOMENTS = "hide_my_moments";

    //不看别人朋友圈的标志
    public static final String POST_HIDE_THEIR_MOMENTS = "hide_their_moments";

    //评论朋友圈的标志
    public static final String POST_HIDE_MOMENTS_COMMENT = "comment";

    //屏蔽
    public static final String POST_BLOCK = "block";

    public static final String POST_TURN_ON_OFF_NOTIFICATION = "turn_on_off_notification";

    public static final String POST_COMMENT_HIDE_THEIR_MOMENTS = "post_comment_hide_their_moments";

    public static final String POST_COMMENT = "post_comment";

    public static final String POST_DELETE_COMMENT_SUCCESS = "post_delete_comment_success";

    public static final String POST_HIDE_MOMENTS_SEARCH_RESULT = "post_hide_moments_search_result";

    public static final String POST_CLOSE_MY_MOMENTS = "post_close_my_moments";

    public static final String POST_CLOSE_MY_MOMENTS_FLAGS = "post_close_my_moments_flags";
    //PUBLIC账号被删除
    public static final String POST_MOMENT_PUBLIC_DELETE = "post_public_delete";
    public static final String POST_MOMENT_PUBLIC_DELETE_NOTICE = "post_public_delete_notice";

    //设置青少年模式密码
    public static final String POST_WATCH_GET_ADD_USER_PIN = "post_watch_get_add_user_pin";
    //验证青少年模式密码
    public static final String POST_WATCH_GET_CHECK_USER_PIN = "post_watch_get_check_user_pin";
    //验证用户是否设置青少年模式密码
    public static final String POST_WATCH_GET_CHECK_USER_IS_OPEN = "post_watch_get_check_user_is_open";
    //watch设置是否是青少年模式
    public static final String POST_WATCH_KIDS_WATCH_GUIDE = "post_watch_kids_watch_guide";
    //青少年模式开关
    public static final String POST_WATCH_KIDS_WATCH_GUIDE_UP_PIN_STATE = "post_watch_kids_watch_guide_up_pin_state";
    //watch验证Sasai密码是否正确
    public static final String POST_WATCH_KIDS_WATCH_GUIDE_SASAI_PASSWORD = "post_watch_kids_watch_guide_sasai_password";
    //watch发送验证码
    public static final String POST_WATCH_KIDS_WATCH_GUIDE_SEND_OPT = "post_watch_kids_watch_guide_send_opt";
    //watch验证验证码
    public static final String POST_WATCH_KIDS_WATCH_GUIDE_VERIFY_OPT = "post_watch_kids_watch_guide_verify_opt";

    public static final String POST_LIVE_DOWNLOAD = "post_live_download";

    //申诉提交成功
    public static final String POST_APPEAL_APPLY_SUCCESS = "post_appeal_apply_success";
    //申诉提交成功刷新通知列表
    public static final String POST_APPEAL_APPLY_SUCCESS_MSG_ID = "post_appeal_apply_success_msg_id";
}
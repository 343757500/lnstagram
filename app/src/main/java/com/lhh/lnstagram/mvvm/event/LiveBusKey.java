package com.lhh.lnstagram.mvvm.event;

import android.text.TextUtils;

/**
 * 事件总线-key
 */
public enum LiveBusKey {


    APP_LAUNCH_TASK_FINISH("app_launch_task_finish", "success"),

    // 选择地区
    COMMON_SELECT_REGION_RESULT("user_modify_region", "result"),

    // 选择地区多选
    COMMON_SELECT_MULTIPLE_REGION_RESULT("user_modify_region_multiple", "result"),

    // TAB点击事件
    MAIN_TAB_CLICK("main_tab_click", "result"),

    // 用户注册成功
    USER_REGISTER_SUCCESS("user_register", "success"),
    USER_LOGIN_ERROR("user_login", "error"),


    // 获取好友列表成功
    REFER_FRIEND_LIST_FROM_SASAI_SUCCESS("refer_friend_list_from_sasai_success", "success"),
    REFER_FRIEND_LIST_FROM_MOBILE_SUCCESS("refer_friend_list_from_mobile_success", "success"),


    // 刷新wifi
    REFER_WIFI_FINDER_DISTANCE_SUCCESS("refer_wifi_finder_distance", "success"),


    // 修改用户信息
    USER_MODIFY_INFO_SUCCESS("user_modify_info", "success"),
    // 修改用户名片
    USER_MODIFY_CARD_SUCCESS("user_modify_card", "success"),
    // 用户修改图像
    USER_MODIFY_IMAGE_SUCCESS("user_modify_image", "success"),
    // 修改邮箱
    USER_MODIFY_EMAIL_SUCCESS("user_modify_email", "success"),
    // 修改手机
    USER_MODIFY_MOBILE_SUCCESS("user_modify_email", "success"),
    // 忘记密码成功
    USER_FORGET_PASSWORD_SUCCESS("user_Forget_password", "success"),

    // 编辑备注名的结果
    CONTACTS_EDIT_REMARK_NAME_RESULT("contacts_edit_remark_name", "result"),
    // 添加好友申请的处理结果
    @Deprecated
    CONTACTS_ADD_FRIEND_HANDLE_APPLY("contacts_add_friend_handle_apply", "result"),
    // 更新通讯录
    CONTACTS_UPDATE_LIST("contacts_update_list", "success"),
    // 更新通讯录某个人
    CONTACTS_UPDATE_FRIEND("contacts_update_friend", "success"),
    CONTACTS_DEL_FRIEND("contacts_del_friend", "success"),

    // 系统通讯录选择号码
    MOBILE_CONTACTS_SELECT_PHONE_SUCCESS("contacts_select_phone", "success"),
    MOBILE_CONTACTS_SELECT_PHONE_AND_NAME_SUCCESS("contacts_select_phone_and_name", "success"),

    // 通讯录选择号码
    CONTACTS_SELECT_FRIEND_SUCCESS("contacts_select_friend", "success"),
    CONTACTS_MULTIPLE_SELECT_FRIEND_IDS("contacts_multiple_select_friend_ids", "result"),
    CONTACTS_MULTIPLE_SELECT_FRIEND_LIST("contacts_multiple_select_friend_list", "result"),

    //图片预览右上角button点击
    PHOTO_PREVIEW_RIGHT_CLICK("photo_preview_right_click", "result"),

    // 转账结果
    CHAT_TRANSFER_RESULT("chat_transfer_result", "result"),
    // 发送礼品卡
    CHAT_GIFT_CARD_RESULT("chat_gift_card_result", "result"),
    // 好友名片选择
    CHAT_PROFILE_CARD_RESULT("chat_profile_card_result", "result"),
    // 个人联系名片选择
    CHAT_CONTACT_CARD_RESULT("chat_contact_card_result", "result"),
    // @ 信息
    CHAT_AT_RESULT("chat_at_result", "result"),
    // 选择位置
    CHAT_LOCATION_RESULT("chat_location_result", "result"),
    // 选择gif表情
    CHAT_EMOTICON_GIF_RESULT("chat_emoticon_gif_result", "result"),

    // 转发成功
    CHAT_SEND_MESSAGE_FORWARD("chat_send_message_result_forward", "result"),
    // 发送消息-滑动到底部
    CHAT_SEND_MESSAGE_RESULT_ADD_ITEM("chat_send_message_result_scroll_bottom", "result"),
    // 发送消息-状态改变
    CHAT_SEND_MESSAGE_RESULT_UPDATE_ITEM("chat_send_message_result_update", "result"),
    // 发送消息-发送成功
    CHAT_SEND_MESSAGE_RESULT_UPDATE_ITEM_SUCCESS("chat_send_message_result_send_success", "result"),
    // 接受消息
    CHAT_RECEIVE_MESSAGE_RESULT_ADD_ITEM("chat_receive_message_result_add_item", "result"),
    // 更新消息
    CHAT_RECEIVE_MESSAGE_RESULT_UPDATE("CHAT_RECEIVE_MESSAGE_RESULT_UPDATE", "result"),
    //更新点赞状态
    CHAT_RECEIVE_MESSAGE_RESULT_UPDATE_LINK_STATUS_P2P("CHAT_RECEIVE_MESSAGE_RESULT_UPDATE_LINK_STATUS_P2P", "result"),
    CHAT_RECEIVE_MESSAGE_RESULT_UPDATE_LINK_STATUS_GROUP("CHAT_RECEIVE_MESSAGE_RESULT_UPDATE_LINK_STATUS_GROUP", "result"),

    // 接受消息-新增
    CHAT_RECEIVE_MESSAGE_RESULT_UPDATE_ITEM("chat_receive_message_result_update_item", "result"),
    // 删除消息
    CHAT_DEL_MESSAGE_SUCCESS_UPDATE_ITEM("chat_del_message_from_list", "success"),
    // 语音消息下载完成
    CHAT_AUDIO_MESSAGE_SUCCESS_UPDATE_ITEM("chat_audio_message_from_list", "success"),
    // 语音消息播放
    CHAT_AUDIO_PLAY_MESSAGE_SUCCESS_UPDATE_ITEM("chat_audio_play_message_from_list", "success"),
    // 文件下载
    CHAT_DOWNLOAD_MESSAGE_SUCCESS_UPDATE_ITEM("chat_download_message_from_list", "success"),

    //group chat groupName changed
    CHAT_FRIEND_INFO_CHANGED("chat_friend_info_change", "send_obj"),
    CHAT_GROUP_INFO_CHANGED("chat_group_info_change", "send_obj"),
    CHAT_GROUP_INFO_CHANGED_GROUP_ID("chat_group_info_change", "send_id"),

    // 通知,
    CHAT_NOTICE_FRIEND("chat_notice_friend", "success"),
    CHAT_NOTICE_GROUP("chat_notice_group", "success"),
    CHAT_NOTICE_WALLET_ING("chat_notice_wallet_ing", "success"),
    CHAT_NOTICE_WALLET_RESULT("chat_notice_wallet_result", "success"),
    CHAT_NOTICE_WALLET_BALANCE_RESULT("chat_notice_wallet_balance_result", "success"),
    CHAT_NOTICE_GROUP_REMOVE_RESULT("chat_notice_group_remove_result", "result"),

    // 发送输入状态
    CHAT_SEND_INPUT_STATE_OVER("chat_send_input_state_over", "1"),
    CHAT_SEND_INPUT_STATE_INPUT_TEXT("chat_send_input_state_input_text", "2"),
    CHAT_SEND_INPUT_STATE_INPUT_AUDIO("chat_send_input_state_input_audio", "3"),
    // 接收输入状态
    CHAT_RECEIVE_INPUT_STATE_OVER("chat_receive_input_state_over", "1"),
    CHAT_RECEIVE_INPUT_STATE_INPUT_TEXT("chat_receive_input_state_input_text", "2"),
    CHAT_RECEIVE_INPUT_STATE_INPUT_AUDIO("chat_receive_input_state_input_audio", "3"),

    // 发送消息状态
    CHAT_SEND_MESSAGE_STATE_READ("chat_send_message_state_read", "result"),
    CHAT_SEND_MESSAGE_STATE_RECALL("chat_send_message_state_recall", "result"),
    // 接收消息状态
    CHAT_RECEIVE_MESSAGE_STATE_RECEIVE("chat_receive_message_state_receive", "result"),
    CHAT_RECEIVE_MESSAGE_STATE_READ("chat_receive_message_state_read", "result"),
    CHAT_RECEIVE_MESSAGE_STATE_RECALL("chat_receive_message_state_recall", "result"),

    //分享转发消息
    SHARE_MESSAGE_MSG("share_message_msg", "result"),
    SHARE_MESSAGE_MSG_FRIEND("share_message_msg_friend", "result"),

    // 分享之后关闭"最新会话选择，好友列表，群列表"
    SHARE_MESSAGE_CLOSE_ACTIVITY("share_message_close_activity", "result"),

    // 分享之后关闭"最新会话选择，好友列表，群列表"
    SHARE_MESSAGE_MOMENT_CLOSE_ACTIVITY("share_message_moment_close_activity", "result"),

    // 网络连接结果
    NETWORK_CONNECTION_RESULT("network_connection", "result"),
    // TCP的连接结果
    MAIN_TCP_CONNECTION_RESULT("main_tcp_connection", "result"),
    // TCP的登录结果
    MAIN_TCP_LOGIN_RESULT("main_tcp_login", "result"),
    // 拉取离线之后（分页成功之后就通知）
    MAIN_GET_OFFLINE_MESSAGE_SUCCESS_PART("main_get_offline_message_success_part", "result"),

    // 登录成功跳去主页
    LOGIN_TO_MAIN("login_to_main", "result"),
    // 跳去登录页面
    MAIN_TO_LOGIN("main_to_login", "result"),
    // 游客模式登陆提示
    MAIN_TO_LOGIN_VISITOR("main_to_login_visitor", "result"),
    // 被踢下线
    MAIN_TO_LOGIN_FROM_PUSH_LOGOUT("main_to_login_from_push_logout", "result"),
    // 新朋友未读数
    MAIN_NEW_FRIEND_UNREAD_NUMBER("main_new_friend_unread_number", "count"),
    // 消息未读数
    MAIN_MESSAGE_UNREAD_NUMBER("main_message_unread_number", "count"),
    // 跳去CallLog
    MAIN_CHAT_TO_AV_CALL("main_chat_to_av_call", "result"),

    // 最新消息获取群id
    CHAT_RECENT_CHAT_LIST_GET_GROUP_INFO("chat_recent_chat_get_group_info", "group_id"),

    // 最近会话列表移除item,群列表Iten
    CHAT_RECENT_CHAT_ITEM_REMOVE_RESULT("chat_recent_chat_item_remove_result", "result"),
    // 转发给一个收件人时关闭当前聊天页面
    CHAT_TRANSFER_MESSAGE_RESULT("chat_transfer_message_result", "result"),
    // 清空个人或群的聊天记录
    CHAT_MESSAGE_CHAT_HISTORY_CLEAR_RESULT("chat_message_chat_history_clear_result", "result"),


    //主页面(MainActivity) OnTouch 事件
//    MAIN_PAGE_TOUCH_RESULT("main_page_touch_result", "result"),


    //以下是音视频得
    //注册成功
    CHAT_AV_REGISTER_SYSTEM_SUCCESS_RESULT("chat_av_register_system_success_result", "count"),
    //发起人发起音视频登陆成功的时候
    CHAT_AV_LOGIN_SYSTEM_SUCCESS_RESULT("chat_av_login_system_success_result", "count"),

    //创建成功后，自己加入会议室成功
    CHAT_AV_JOIN_ROOM_SELF_SUCCESS_RESULT("chat_av_join_room_self_success_result", "result"),


    //创建成功后，自己加入会议室成功
    CHAT_AV_ON_FIRST_BROCASTSELECT_RESULT("chat_av_on_first_brocaseselect_result", "result"),


    //离线的时候收到一次会议邀请的推送....然后进行登陆
    CHAT_AV_ON_HAS_ONE_MEETING_INVITE_INFO_RESULT("chat_av_on_has_one_meeting_invite_info_result", "result"),

    //会议结束之后就去更新会议状态..
    CHAT_AV_ON_HAS_ONE_MEETING_END_INFO_RESULT("chat_av_on_has_one_meeting_end_info_result", "result"),


    //获取正在开的...
    CHAT_AV_MEETING_CALLING_INFO_INFO_RESULT("chat_av_meeting_calling_info_result", "result"),

    //获取成员
    CHAT_AV_MEETING_CALLING_GET_MEMBER_INFO_RESULT("chat_av_meeting_calling_info_result", "result"),

    //获取callLog
    CHAT_AV_MEETING_CALL_LOG_UPDATE_FRESH_INFO_RESULT("chat_av_meeting_call_log_update_fresh_result", "result"),

    // av被踢下线
    AV_TO_LOGIN_FROM_PUSH_LOGOUT("av_to_login_from_push_logout", "result"),

    //关闭所有
    AV_CLOSE_CHOICE_FRIENDS_FROM_PUSH("av_close_choice_friends_push", "result"),

    //中途加入不成功回调
    AV_JOIN_IN_MEETING_MIDDLE_PUSH("av_join_in_meeting_middle_push", "result"),

    //未读数提醒...
    AV_MEETING_MISS_CALL_PUSH("av_meeting_miss_call_push", "result"),
    IM_MESSAGE_NOTICE_NEW_PUSH("im_message_notice_new_push", "result"),

    //未读数变化提醒...
    AV_MEETING_MISS_CALL_CHANGE_PUSH("av_meeting_miss_call_change_push", "result"),


    //获取callLog--本地
    CHAT_AV_MEETING_UPDATE_CALL_LOGIN_FRESH_INFO_RESULT("chat_av_meeting_update_call_log_fresh_result", "result"),


    // 网络连接结果--音视频
    CHAT_AVNETWORK_CONNECTION_RESULT("chat_av_network_connection", "result"),

    //AV_MEMBER_PAGE_NOT_NET_STATUS_DATA
    //音视频丢包率...
    AV_MEMBER_PAGE_NOT_NET_STATUS_DATA("av_member_page_not_net_status_data", "result"),


    //H5 授权登录
    H5_OAUTH2_RESULT("h5_oauth2_result", "result"),
    H5_PAY_RESULT("h5_pay_result", "result"),


    //这次是登录操作...
    AV_CALL_INVITE_MSG_LOGIN_RESULT("av_call_invite_msg_login_result", "result"),


    // 音视频更新消息广播..状态
    CHAT_AV_MESSAGE_RESULT_UPDATE_ITEM_SUCCESS("chat_av_message_result_send_success", "result"),

    // 音视频更新消息广播..状态By MeetingMsgId
    CHAT_AV_MESSAGE_RESULT_UPDATE_ITEM_BY_MEETINGMSGID_SUCCESS("chat_av_message_result_update_item_by_meetingmsgid_success", "result"),

    //注册--音视频
    CHAT_AV_REGISTER_SYSTEM_INFO_RESULT("chat_av_register_system_info_result", "count"),

    // 设置聊天背景图片
    CHAT_BG_SET_RESULT("chat_bg_set_result", "result"),
    // 设置全局聊天背景图片
    CHAT_BG_GLOBAL_RESULT("chat_bg_global_result", "result"),


    //更新IM进程的ulMeetingId--
    CHAT_IM_UPDATE_CALL_STATUS_FRESH_INFO_RESULT("chat_im_update_call_status_fresh_result", "result"),


    //更新Av进程的sp--
    CHAT_IM_UPDATE_AV_PROCESS_CALL_SP_FRESH_INFO_RESULT("chat_im_update_av_process_call_sp_fresh_result", "result"),

    //前台回来......权限没开会导致通话界面消失...
    CHAT_IM_UPDATE_AV_PROCESS_NO_PERMI_NEED_RESULT("chat_im_update_av_process_no_permi_need_result", "result"),

    //对方挂断之后
    CHAT_AV_MEETING_HAND_UP_INFO_RESULT("chat_av_meeting_hand_up_info_result", "result"),

    //音视频进程通信...
    CHAT_AV_PROCEES_MEETING_HAND_UP_INFO_RESULT("chat_av_process_meeting_hand_up_info_result", "result"),


    //没有存储权限
    PERMISSION_NO_STORAGE("permission_no_storage", "result"),

    //执行登录操作(发送在connect成功之后)
    CHAT_AV_MEETING_LOGIN_OPERATE_BEFOR_RESULT("chat_av_meeting_login_operate_before_result", "result"),

    //add成功之後,发送invite
    CHAT_AV_MEETING_ADD_MEMEBER_AFTER_INVITE_BEFOR_RESULT("chat_av_meeting_add_member_after_invite_befor_info_result", "result"),

    // tcp---av被踢下线
    CHAT_DEAL_WITH_AV_TO_LOGIN_FROM_PUSH_LOGOUT("chat_deal_with_av_to_login_from_push_logout", "result"),


    // tcp---断开了--此时收到音视频邀请走150推送-这时候就得去拉去离线消息
    CHAT_DEAL_WITH_AV_FROM_GOOGLE_PUSH("chat_deal_with_av_from_google_push", "result"),

    //锁屏的时候需要出现这个....
    CHAT_IS_NO_PERMISSION_AND_PHONE_IS_LOCK("chat_is_no_permission_and_phone_is_lock", "result"),


    //在接听界面时候，需要把首页待接听的icon去掉 ....
    CHAT_IS_ON_THE_CALL_VIEW_MISS_CALL_ION("chat_is_no_the_call_view_miss_call_icon", "result"),


    //停止av进程的service ....
    CHAT_IS_STOP_AV_PROCESS_SERVICE("chat_is_stop_av_process_service", "result"),

    //main页面切换
    MAIN_TAB_SWITCH("main_tab_switch"),


    //对方挂断之后
    CHAT_AV_MEETING_HAND_UP_CALL_GONE_INFO_RESULT("chat_av_meeting_hand_up_call_gone_info_result", "result"),

    //NS_DROPPED... 直接无法发送出去的回调
    AV_MEMBER_PAGE_NOT_NET_STATUS_NS_DROPPED_DATA("av_member_page_not_net_status_ns_dropped_data", "result"),

    //IM添加好友无须同意
    ACCEPT_FRIEND_REFRESH_CHAT("accept_friend_friend_chat", "result"),

    // 音视频获取评分数据
    MAIN_TO_RATING_GET_DATA("main_to_rating_get_data", "result"),

    // 音视频获取评分数据获取成功
    MAIN_TO_RATING_GET_DATA_SUCCESS("MAIN_TO_RATING_GET_DATA_SUCCESS", "result"),

    //打开WIFI FINDER
    OPEN_WIFI_FINDER_BY_H5("OPEN_WIFI_FINDER_BY_H5", "result"),

    //更新发现页未读数
    UPDATE_SASAI_MODULE_UNREAD_COUNT("UPDATE_SASAI_MODULE_UNREAD_COUNT", "result"),

    //附近wifi
    NEAR_WIFI("NEAR_WIFI", "result"),

    //弹出隐私协议
    SHOW_PRIVACY_PROTOCOL("SHOW_PRIVACY_PROTOCOL", "result"),

    //客服聊天状态
    UPDATE_CUSTOMER_SERVICE_CHAT_STATUS("UPDATE_CUSTOMER_SERVICE_CHAT_STATUS", "result"),

    //客服问题列表
    CUSTOMER_SERVICE_REPORTER_LIST("CUSTOMER_SERVICE_REPORTER_LIST", "result"),

    //发送工单
    CUSTOMER_SERVICE_REPORTER_SEND("CUSTOMER_SERVICE_REPORTER_SEND", "result"),

    //分配客服
    CUSTOMER_SERVICE_APPORTION("CUSTOMER_SERVICE_APPORTION", "result"),
    //群成员信息更新
    UPDATE_GROUP_MEMBER_INFO_CHANGE("UPDATE_GROUP_MEMBER_INFO_CHANGE", "result"),
    //游客模式倒计时
    COUNTDOWN_IN_TOURIST_MODE("COUNTDOWN_IN_TOURIST_MODE", "result"),
    // End of key
    @Deprecated
    KEY_DEMO("key", "tag");


    // Start of Class
    private Object key; // 名称
    private String tag; // 标签

    LiveBusKey(Object key, String tag) {
        this.key = key;
        this.tag = tag;
    }

    LiveBusKey(Object key) {
        this.key = key;
    }

    public String getEventKey() {
        String result;
        if (!TextUtils.isEmpty(tag)) {
            result = key + tag;
        } else {
            result = (String) key;
        }
        return result;
    }

    @Override
    public String toString() {
        return getEventKey();
    }
}

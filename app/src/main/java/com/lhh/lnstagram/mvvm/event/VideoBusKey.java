package com.lhh.lnstagram.mvvm.event;


/**
 * 事件总线-key
 */
public class VideoBusKey {

    public static final String VIDEO_ADD_USER = "video_add_user";

    public static final String VIDEO_ROOM_ACTION = "video_room_action";
    public static final String VIDEO_CANCEL_BY_SENDER = "video_cancel_by_sender";
    public static final String VIDEO_REJECT_BY_RECEIVER = "video_reject_by_receiver";
    public static final String VIDEO_ACCEPT_BY_RECEIVER = "video_accept_by_receiver";
    public static final String VIDEO_EXIT_BY_ROOMING = "video_exit_by_rooming";

    public static final String VIDEO_NO_PERMI_FLOAT_ACTION = "video_no_permi_float_action";

    public static final String VIDEO_ROOM_STATUS_UPDATE_ACTION = "video_room_status_update_action";

    public static final String VIDEO_OLD_VERSION_ROOM_ACTION = "video_old_version_room_action";

    public static final String VIDEO_COMPLETE_CALL_LOG_ACTION = "video_complete_call_log_action";

    public static final String VIDEO_UPLOAD_ALL_LOG_ACTION = "video_upload_all_log_action";


    public static final String VIDEO_UPDATE_TY_MUTIL_ACTION = "video_update_ty_mutil_action";

    public static final String VIDEO_SINGLE_AUDIO_ENABLE_OR_NOT_ACTION = "video_single_audio_enable_or_not_action";
    public static final String VIDEO_SINGLE_VIDEO_ENABLE_OR_NOT_ACTION = "video_single_video_enable_or_not_action";


    //20人音视频
    public static final String TY_VIDEO_REMOVE_MUTE_ACTION = "ty_video_remove_mute_action";
    public static final String TY_VIDEO_GROUP_NOTIFY_ACTION = "ty_video_group_notify_action";
    public static final String TY_VIDEO_UREAD_FIND_ACTION = "ty_video_unread_find_action";
    public static final String TY_VIDEO_GET_ROOM_BY_OFFlINE_ACTION = "ty_video_get_room_by_offline_action";
}

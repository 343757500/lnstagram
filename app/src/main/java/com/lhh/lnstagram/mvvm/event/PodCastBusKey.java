package com.lhh.lnstagram.mvvm.event;

public class PodCastBusKey {

    // 修改资料
    public static final String PODCAST_USER_MODIFY_INFO_SUCCESS = "podCast_user_modify_info_success";

    // 选择好设置偏好
    public static final String PODCAST_SELECT_TAG = "podCast_select_tag";

    // 选择TagUser
    public static final String PODCAST_SELECT_TAG_USER = "podcast_select_tag_user";

    // 选择分类
    public static final String PODCAST_SELECT_CATEGORY = "podCast_select_category";

    // 消息未读数
    public static final String PODCAST_NOTIFICATION_NUMBER = "podCast_notification_number";

    // 清空所有消息
    public static final String PODCAST_NOTIFICATION_DEL_ALL = "podCast_notification_del_all";

    // 创建专辑成功
    public static final String PODCAST_CREATE_ALBUM_SUCCESS = "podCast_create_album_success";

    // 创建作品成功
    public static final String PODCAST_CREATE_WORK_SUCCESS = "podCast_create_work_success";

    //wish做了增删操作
    public static final String PODCAST_WISH_UPDATE = "podCast_wish_update";

    //album做了增删操作
    public static final String PODCAST_ALBUM_UPDATE = "podCast_album_update";

    //进入视频详情时发现视频已经被删除
    public static final String PODCAST_VIDEO_DELETED = "podCast_video_deleted";

    //悬浮窗全屏播放
    public static final String PODCAST_VIDEO_FULL_SCREEN = "podCast_video_full_screen";

    //AlbumDetail修改成功
    public static final String PODCAST_ALBUM_DETAIL_UPDATE = "podCast_album_detail_update";

    //关注有更新
    public static final String PODCAST_FOLLOW_UPDATE = "podCast_follow_update";

    //选择可见/不可见好友列表
    public static final String PODCAST_PRIVACY_FOLLOW_LIST = "podCast_privacy_follow_list";
    //设置可见/不可见好友列表
    public static final String PODCAST_SETTING_PRIVACY = "podCast_setting_privacy";

    //下载文件异常重新下载
    public static final String PODCAST_VIDEO_DOWNLOAD_AGAIN = "podCast_video_download_again";

    //通知底部播放器更新数据
    public static final String POD_CAST_NOTIFICATION_BOTTOM_UPDATE = "pod_cast_notification_bottom_update";

    //通知底部更新进度条数据
    public static final String POD_CAST_PROGRESS_BAR_UPDATE = "pod_cast_progress_bar_update";

    //通知列表专辑更新播放状态
    public static final String POD_CAST_NOTIFICATION_LIST_ALBUM_UPDATE = "pod_cast_notification_list_album_update";

    //删除与编辑评论
    public static final String POD_CAST_COMMENT_DELETE_AN_UPDATE_BY_APP = "pod_cast_comment_delete_an_update_by_app";

    //评论的itemView
    public static final String POD_CAST_COMMENT_ITEM_VIEW = "pod_cast_comment_item_view";

    //音频订阅通知
    public static final String POD_CAST_SUBSCRIBE_NOTICE = "pod_cast_subscribe_notice";

    //音频收藏通知
    public static final String POD_CAST_FAVORITE_NOTICE = "pod_cast_favorite_notice";

    // 从第三方拉起详情
    public static final String POD_CAST_LAUNCH_FROM_SPLASH = "pod_cast_launch_from_splash";

    // 合并音频选择文件
    public static final String PODCAST_SELECT_AUDIO_MERGE = "podcast_select_audio_merge";

    // 背景音乐选择文件
    public static final String PODCAST_SELECT_AUDIO_BGM = "podcast_select_audio_bgm";

    // 合并音频返回
    public static final String PODCAST_SELECT_AUDIO_MERGE_SUCCESS = "podcast_select_audio_merge_success";

    // 裁剪音频返回
    public static final String PODCAST_SELECT_AUDIO_CUT_SUCCESS = "podcast_select_audio_cut_success";

    // 音频编辑完成返回
    public static final String PODCAST_SELECT_AUDIO_EDIT_SUCCESS = "podcast_select_audio_edit_success";

    // 音频切换
    public static final String PODCAST_SONG_CHANGE = "podcast_song_change";
}

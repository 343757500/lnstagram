package com.lhh.lnstagram.mvvm.util;

import android.app.ActivityManager;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;


import com.lhh.lnstagram.base.BaseApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 回调常量
 */
public class AVEmuType {
    public static final int JOIN_MEETING_SUCCESS = 0;

    public static final int JOIN_MEETING_ERROR = 1;

    public static final int JOIN_MEETING_ROOM_NOT_EXIST = 2;

    public static final int JOIN_MEETING_ROOM_NOT_ACTIVATE = 3;
    public static final int JOIN_MEETING_NOT_ROOM_MEMBER = 4;
    public static final int JOIN_MEETING_ALREADY_EXIST = 5;
    public static final int JOIN_MEETING_LOCKED = 6;
    public static final int JOIN_MEETING_JOIN_MORE_MEETING = 7;

    public static final int MODIFY_MEETING_SUCCESS = 8;
    public static final int MODIFY_MEETING_ERROR = 9;
    public static final int MODIFY_MEETING_ROOM_NOT_EXIST = 10;
    public static final int MODIFY_MEETING_NOT_ROOM_CREATOR = 11;

    public static final int LOCK_MEETING_SUCCESS = 12;
    public static final int LOCK_MEETING_ERROR = 13;
    public static final int LOCK_MEETING_NOT_ROOM_CREATOR = 14;

    public static final int SET_CHAT_MODE_SUCCESS = 15;
    public static final int SET_CHAT_MODE_ERROR = 16;
    public static final int SET_CHAT_MODE_NOT_ROOM_CREATOR = 17;

    public static final int LOCK_DATA_MEETING_SUCCESS = 18;
    public static final int LOCK_DATA_MEETING_ERROR = 19;
    public static final int LOCK_DATA_MEETING_NOT_ROOM_OWNER = 20;

    public static final int DELETE_MEETING_SUCCESS = 21;
    public static final int DELETE_MEETING_ERROR = 22;
    public static final int DELETE_MEETING_ROOM_NOT_EXIST = 23;
    public static final int DELETE_MEETING_NOT_ROOM_CREATOR = 24;
    public static final int DELETE_MEETING_ACTIVATE = 25;

    public static final int DELETE_MEMBER_SUCCESS = 26;
    public static final int DELETE_MEMBER_ERROR = 27;
    public static final int DELETE_MEMBER_NOT_ROOM_CREATOR = 28;
    public static final int DELETE_MEMBER_NOT_DELETE_SELF = 29;
    public static final int DELETE_MEMBER_NOT_DELETE_ROOM_CREATOR = 30;

    public static final int CREATE_MEETING_SUCCESS = 31;
    public static final int CREATE_MEETING_ERROR = 32;

    public static final int ADD_MEETING_MEMBER_SUCCESS = 33;
    public static final int ADD_MEETING_MEMBER_ERROR = 34;
    public static final int ADD_MEETING_MEMBER_ROOM_NOT_EXIST = 35;
    public static final int ADD_MEETING_MEMBER_MEMBER_ALREADY_EXIST = 36;
    public static final int ADD_MEETING_MEMBER_NOT_ROOM_OWNER = 37;

    public static final int APPLY_JOIN_MEETING_SUCCESS = 38;
    public static final int APPLY_JOIN_MEETING_ERROR = 39;
    public static final int APPLY_JOIN_MEETING_ROOM_NOT_EXIST = 40;
    public static final int APPLY_JOIN_MEETING_NOT_OPEN_MEETING = 41;
    public static final int APPLY_JOIN_MEETING_ROOM_NOT_ACTIVATE = 42;
    public static final int APPLY_JOIN_MEETING_ROOM_MEMBER = 43;
    public static final int APPLY_JOIN_MEETING_ALREADY_JOIN_MEETING = 44;

    //注册成功
    public static final int REGISTER_SYSTEM_SUCCESS = 7;
    //登录成功...
    public static final int LOGIN_SYSTEM_SUCCESS = 0;
    public static final int LOGIN_SYSTEM_MESSAGE_MYSEFE = 100;//自己发起会议登陆成功

    //创建会议室成功...
    public static final int AV_CREATE_ROOM_SUCCESS = 200;

    //发起人进入会议室成功...
    public static final int AV_ENTER_ROOM_SUCCESS = 51;
    public static final int AV_GET_USER_STATUS = 61;
    public static final int AV_ADD_MEMBER_SUCCES = 71;
    public static final int AV_INVITE_MEMBER_WARMING = 81;
    public static final int AV_MEMBER_REJECT_INVITE = 91;
    //对方接受了，然后回调
    public static final int AV_MEMBER_ACCEPT_INVITE = 101;
    //connect成功之后
    public static final int AV_MEMBER_CONNECT_SUCCESS = 11111;

    //发起者直接关闭会议
    public static final int AV_MEETING_HASH_FINISH = 121;

    //个人退出会议...
    public static final int MEMBER_LEAVE_MEETING_LOGOUT_OR_OFFLINE = 131;


    //参加人员的状态...
    public static final int AV_MEETING_MEMBER_UPDATE_STATUS = 141;


    //中途加人.....
    public static final int AV_ADD_MEMBER_IN_ROOM = 151;

    public static final int AV_ON_MEETING_MEMBER_BROCAST = 161;


    public static final int BROCAST_AUDIO_VIDEO = 0;
    public static final int BROCAST_VIDEO = 1;
    public static final int BROCAST_AUDIO = 2;
    public static final int BROCAST_NONE = 3;
    public static final int BROCAST_NONE_IS_CALLING = 3;//这个是对方拨电话当中

    public static long userMemberId = 0;
    //ulMeetingID

    public static long ulMeetingID = 0;
    public static String meetingMsgId = "0";


    // 拒绝邀请 0--用户拒绝，1---等待超时拒绝，2---用户已在房间拒绝
    public static final int AV_CALL_CSM_REJECT_INVITE = 0;
    public static final int AV_CALL_CSM_TIME_OVER_INVITE = 1;
    public static final int AV_CALL_CSM_USER_IS_IN_ROOM_INVITE = 2;

    //这个是60s不接听就职挂断
    public static final int AV_NOT_RESPONE_TIME_PER = 1000;
    public static final int AV_NOT_RESPONE_TIME = 60 * AV_NOT_RESPONE_TIME_PER;
    public static final int AV_NOT_RESPONE_ACEEPT_TIME = 65 * AV_NOT_RESPONE_TIME_PER;
    //断网时候，20s未联上，则会退出...
    public static final int AV_NOT_RESPONE_ON_NET_WORK = 30 * AV_NOT_RESPONE_TIME_PER;

    public static final int AV_NOT_CONNECT_SUCCESS_WORK = 30 * AV_NOT_RESPONE_TIME_PER;


    public static final int AV_VOICE_SERVICE_HAND_UP_TYPE = 991;//这个是service关闭时候

    //4是语音通话，5是视频通话
    public static final int AV_CALL_IS_VOICE_TYPE = 4;
    public static final int AV_CALL_IS_VIDEO_TYPE = 5;
    public static final int AV_CALL_IS_VOICE_GROUP_TYPE = 6;
    public static final int AV_CALL_IS_VIDEO_GROUP_TYPE = 7;

    //切换框体
    public static final int AV_CALL_SWITCH_VIDEO_TYPE = 121212;

    public static final int AV_MEMBER_LOGIN_OUT = 99989;

    //中途邀请人/群聊的时候无人响应的时候...发起方把人delete
    public static final int AV_CALL_MEMBER_NOT_RESPOND_DELETE = 181;
    //获取正在开的会议
    public static final int AV_CALL_HIS_CALL_LOG_INFO = 191;


    public static final int MEMBER_IS_ON_CALL_NOTICE = 171;
    public static final int VOICE_PLAYER_CREATE_NOTIFY=201;
    public static final int VIDEO_PLAYER_CREATE_NOTIFY=211;


    public static final String AV_AREADY_REGISTER = "av_ready_register";
    public static final String AV_AREADY_REGISTER_NOT = "av_ready_register_not";
    public static final String EXTRA_AV_GROUP_CALL = "AV_GROUP_CALL";

    public static final String ISCLICKACCEPTBEFORCONNECT = "isAceptAready";

    public static String token = "";
    public static final String ISFROMIMPROPAGE = "isInnerPage";
    public static boolean isBackGround = false;//是否是后台运行

    public static String PUSH_NOTIFICATION_TAG="av";


    //--------------以下是跨进场通信消息
    public static String AV_NDK_MSG_TAG="av_ndk_msg_post";
    public static String AV_NDK_MSG_DATA="av_ndk_msg_data";
    public static String AV_NDK_MSG_DATA_LENGTH="av_ndk_msg_data_length";
    public static String AV_NDK_MSG_DATA_FROM="av_ndk_msg_data_from";
    public static String AV_NDK_MSG_DATA_TO="av_ndk_msg_data_to";

    //-------
    public static String AV_MSG_PUSH_COME_TAG="av_msg_push_come_tag";
    //
    public static String AV_MISS_CALL_NOTIFITY_MSG_TAG="av_miss_call_notify_msg_post";
    public static String AV_FROM_USER_MSG_DATA="av_from_user_msg_data";

    //160
    public static String AV_160_MSG_TAG="av_160_msg_post";
    //updateCallLog
    public static String AV_UPDATE_CHAT_CALL_LOG_TAG="av_update_chat_call_log_tag";

    //161
    public static String AV_161_MSG_SINGLE_TAG="av_161_msg_post";

    //memberSave----
    public static String AV_MEMBER_SAVE_MSG_TAG="av_member_save_msg_tag";
    public static String AV_MEMBER_SAVE_MSG_MIDDLE_ADD_TAG="av_member_save_msg_middle_add_tag";
    public static String AV_MEMBER_SAVE_MSG_BEAN_TAG="av_member_save_msg_bean_tag";
    public static String AV_MEMBER_SAVE_MSG_AV_MEMBER_TAG="av_member_save_msg_av_member_tag";
    public static String AV_MEMBER_SAVE_MSG_ONLY_MEETING_BEAN_TAG="av_member_save_msg_only_meeting_bean_tag";
    public static String AV_MEMBER_DELETE_ALL_MEMBERS_TAG="av_member_delete_all_members_tag";


    //保存callLog的post  Tag
    public static String AV_MAIN_CALL_LOG_MSG_TAG="av_member_save_msg_tag";
    public static String AV_MAIN_CALL_LOG_BEFORE_DELETE_MSG_TAG="av_member_save_before_delete_msg_tag";
    public static String AV_MAIN_CALL_LOG_UPDATE_IS_SINGLE_OR_GROUP="updateIsSigleOrGroup";//
    public static String AV_MAIN_CALL_LOG_UPDATEMEETINGENDTIME_MSG_TAG="updateMeetingEndTime";//
    public static String AV_MAIN_CALL_LOG_UPDATEMEETINGBEGINTIME_MSG_TAG="updateMeetingBeginTime";//
    public static String AV_MAIN_CALL_LOG_UPDATEMEETINGREADSTATUS_MSG_TAG="updateMeetingReadStatus";//
    public static String AV_MAIN_CALL_LOG_UPDATEMEETINGMEETINGTYPE_MSG_TAG="updateMeetingType";
    public static String AV_MAIN_CALL_LOG_UPDATERIGHTMEETINGID_MSG_TAG="updateRightMeetingId";
    //deleteMemberByMeetingIdAndMemberId
    public static String AV_MEMBER_DELETE_BY_ID_OPTION_TAG="deleteMemberByMeetingIdAndMemberId";
    //
    public static String AV_MEMBER_DELETE_BY_MEETINGID_OPTION_TAG="deleteAllMemberByMeetingId";


    public static String AV_NDK_VERSION="5.0";

    public static String Av_NDK_FIND_MEETING_STATUS="av_ndk_find_meeting_status";
    public static String AV_REGISTER_MEETING_TO_IM="av_register_meeting_to_im";

    //tcp---重新链接
    public static String AV_TELL_NDK_TRY_TO_RECONNECT="reconnect";

    public static String AV_MEETING_DATA_COLLECT_TAG="av_meeting_data_collect_tag";
    //public static int logCount=0;
    public static String AV_MEETING_UPDATE_ASYNC_MSG_TAG="update_aync_msg_tag";

    public static String AV_MEETING_UPDATE_MSG_TAG="av_update_msg_tag";
    //updateIsCallJoin
    public static String AV_MEETING_UPDATE_IS_CALL_JOIN_TAG="av_meeting_update_is_call_join_tag";

    public static String AV_MEETING_RINGTON_IM_STOP="av_meeting_rington_im_stop";

    public static boolean isRingStoneStart=false;

    //是否正在会议当中
    public static boolean isMeeting(long meetingID) {
        if (ulMeetingID != 0L && ulMeetingID != meetingID) {
            return true;//在会议当中
        } else {
            return false;
        }
    }


    public static boolean AV_REALLY_CALL_TAG = false;//


    public static boolean AV_REALLY_LOGIN_TAG = false;//是否已经登录了....


    //public static final String AV_PHONE_RECEIVER="android.intent.action.NEW_OUTGOING_CALL";

    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;

    //这是音视频进程
    public static String AV_CALL_PROCESS = ":sasavideo";
    //
    public static String AV_CALL_VOICE_ACTION_FORM_OUTSIDE = "com.gigaiot.sasa.chatm.video.AvSendVoiceAVideo";

    public static final String BIND_ACTION = "com.young.server.START_AIDL_SERVICE";


    public static boolean isAvProcessBackGroup = false;//默认是在前台的...
    public static String AV_CALL_PROCESS_PID = "AV_PID";

    public static int getProcessID() {
        // BaseApplication没依赖AV模块所以在这里初始化
        ActivityManager activityManager = (ActivityManager) BaseApplication.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
        //获得进程列表
        List<ActivityManager.RunningAppProcessInfo> processList = activityManager.getRunningAppProcesses();
        //获得当前pid
        int pid = android.os.Process.myPid();
        //判断当前pid属于哪个进程
        for (ActivityManager.RunningAppProcessInfo process : processList) {
            if (pid == process.pid) {
                //找到了所属进程，判断进程名是否为 主进程名（默认为包名）
                //LogUtil.e("chenkecai909000DB", "进程名：" + process.processName);
                if (process.processName.equals(BaseApplication.getInstance().getPackageName())) {
                    //LogUtil.e("chenkecai909000", "主进程：全局初始化，只执行一次");//这要改为主进程需要加载的...
                } else if (process.processName.equals(BaseApplication.getInstance().getPackageName() + AVEmuType.AV_CALL_PROCESS)) {
                    return process.pid;
                }


            }
        }

        return 0;
    }

    public static void checkIfInBackGroup(String processName, boolean isBackGroup) {
        if (!TextUtils.isEmpty(processName)) {
            if (processName.equals(BaseApplication.getInstance().getPackageName() + AVEmuType.AV_CALL_PROCESS)) {
                isAvProcessBackGroup = isBackGroup;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static String getTopStackClass() {
        String activityName = "";
        ActivityManager activityManager = (ActivityManager) BaseApplication.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
        if (null != activityManager) {
            List<ActivityManager.RunningTaskInfo> processInfos = activityManager.getRunningTasks(1);
            if (processInfos != null && processInfos.size() > 0) {
                ActivityManager.RunningTaskInfo info = processInfos.get(0);
                if (info != null) {
                    if (info.topActivity != null) {
                        activityName = info.topActivity.getClassName();
                    }
                }
            }
        }
        return activityName;
    }

    //是否在视频当中
    public static boolean isCallOrNot() {
        if (AVEmuType.ulMeetingID > 1L) {
            return true;
        }
        return false;
    }






    public static boolean isVisieble = false;



    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }


    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

















}

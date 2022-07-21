package com.lhh.lnstagram.bean;

import java.io.Serializable;

/**
 * author : mazhihao
 * e-mail : 475451576@qq.com
 * date   : 2019/11/28 15:44
 * desc   :
 * version: 1.0
 */
public class FollowListBean implements Serializable {
    /**
     * userId : 100000002
     * followName : X000
     * followPic : 1563851465
     * relation : 3
     */

    private String userId;
    private String followName;
    private String followPic;
    //1、关注我      * 2、我关注      * 3、相互关注
    private int relation;
    private boolean selected;//存储选中状态
    private int userType;//0为普通用户 1=个人公众号    2=公司公众号
    private int mute;//0未静音 1静音
    private int activeType;
    private String activeIcon;
    private int blockByMe;//是否屏蔽    1为已屏蔽   0为未屏蔽
    private int blockedByTarget;//是否被对方屏蔽    1为已屏蔽   0为未屏蔽

    public int getBlockedByTarget() {
        return blockedByTarget;
    }

    public void setBlockedByTarget(int blockedByTarget) {
        this.blockedByTarget = blockedByTarget;
    }

    public int getBlockByMe() {
        return blockByMe;
    }

    public void setBlockByMe(int blockByMe) {
        this.blockByMe = blockByMe;
    }

    public String getActiveIcon() {
        return activeIcon;
    }

    public void setActiveIcon(String activeIcon) {
        this.activeIcon = activeIcon;
    }

    public int getActiveType() {
        return activeType;
    }

    public void setActiveType(int activeType) {
        this.activeType = activeType;
    }

    public int getMute() {
        return mute;
    }

    public void setMute(int mute) {
        this.mute = mute;
    }

    public boolean isMute() {
        return mute == 1;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFollowName() {
        return followName;
    }

    public void setFollowName(String followName) {
        this.followName = followName;
    }

    public String getFollowPic() {
        return followPic;
    }

    public void setFollowPic(String followPic) {
        this.followPic = followPic;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "FollowListBean{" +
                "userId='" + userId + '\'' +
                ", followName='" + followName + '\'' +
                ", followPic='" + followPic + '\'' +
                ", relation=" + relation +
                '}';
    }
}

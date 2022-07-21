package com.lhh.lnstagram.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * author : mazhihao
 * e-mail : 475451576@qq.com
 * date   : 2019/11/28 9:22
 * desc   :
 * version: 1.0
 */
public class ReplyCommentBean implements Serializable {

    /**
     * commentId : 150
     * id : 1000158
     * content : 嗯嗯嗯你好
     * createTime : 1575509786
     * fromUid : 100000005
     * fromUnickname : 138003
     * fromUimg : 1553043040
     * replyUserId : 100000002
     * replyNickName : X000
     * replyUimg : 1563851465
     */
    @SerializedName("id")
    private String replyCommentId;
    private String commentId;
    private String content;
    private String contentTranslation;//翻译后的回复
    private String createTime;
    private String fromUid;
    private String fromUnickname;
    private String fromUimg;
    private String replyUserId;
    private String replyNickName;
    private String replyUimg;
    private int replyUserType;


    private int userType;//0为普通用户 1=个人公众号    2=公司公众号

    private int isLike;// 0未点赞  1点赞
    private int state;//0显示1隐藏：评论和回复增加了这个字段
    private int activeType;
    private String activeIcon;




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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }


    public int getReplyUserType() {
        return replyUserType;
    }

    public void setReplyUserType(int replyUserType) {
        this.replyUserType = replyUserType;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(String replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentTranslation() {
        return contentTranslation;
    }

    public void setContentTranslation(String contentTranslation) {
        this.contentTranslation = contentTranslation;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }

    public String getFromUnickname() {
        return fromUnickname;
    }

    public void setFromUnickname(String fromUnickname) {
        this.fromUnickname = fromUnickname;
    }

    public String getFromUimg() {
        return fromUimg;
    }

    public void setFromUimg(String fromUimg) {
        this.fromUimg = fromUimg;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyNickName() {
        return replyNickName;
    }

    public void setReplyNickName(String replyNickName) {
        this.replyNickName = replyNickName;
    }

    public String getReplyUimg() {
        return replyUimg;
    }

    public void setReplyUimg(String replyUimg) {
        this.replyUimg = replyUimg;
    }

}

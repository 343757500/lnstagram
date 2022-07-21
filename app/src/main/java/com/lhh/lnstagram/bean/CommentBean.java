package com.lhh.lnstagram.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CommentBean implements Serializable {
    /**
     * id : 93
     * createTime : 1574817770
     * updateTime : 1574817770
     * articleId : 279
     * articleType : 1
     * content : 的呢娜塔莎
     * fromUid : 100000005
     * fromUnickname : 138003
     * fromUimg : 1553043040
     * replyList : []
     */
    @SerializedName("id")
    private String commentId;
    private String createTime;
    private String updateTime;
    private String articleId;
    private int articleType;
    private String content;
    private String contentTranslation;//翻译后的评论
    private int replyType;
    private String fromUid;
    private String fromUnickname;
    private String fromUimg;
    private int totalReply;
    private List<ReplyCommentBean> replyList;

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



    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getTotalReply() {
        return totalReply;
    }

    public void setTotalReply(int totalReply) {
        this.totalReply = totalReply;
    }


    public int getReplyType() {
        return replyType;
    }

    public void setReplyType(int replyType) {
        this.replyType = replyType;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public int getArticleType() {
        return articleType;
    }

    public void setArticleType(int articleType) {
        this.articleType = articleType;
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

    public List<ReplyCommentBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyCommentBean> replyList) {
        this.replyList = replyList;
    }

}

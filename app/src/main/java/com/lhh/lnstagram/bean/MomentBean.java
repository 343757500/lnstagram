package com.lhh.lnstagram.bean;


import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.lhh.lnstagram.MomentType;


import java.io.Serializable;
import java.util.List;

public class MomentBean implements Serializable, MultiItemEntity {

    private String articleId;//朋友圈id
    private int articleType;//朋友圈类型
    private String userId;//用户id
    private String articleContent;//内容
    private String articleContentTranslation;//翻译后的内容
    private List<PostArticleInfoBean> articleInfo;//文件信息
    private int viewable;//是否可见
    private List<FollowListBean> viewableUserList;//可见/不可见用户列表
    private String showTime;//展示时间
    private int isForward;//能否转发
    private long postTime;//定时发布用
    private long createTime;//发布时间
    private int likeCount;//点赞数
    private int commentCount;//评论数量
    private String nickName;//昵称
    private String image;//头像
    private List<CommentBean> commentList;//评论

    private int isLike;//0为未点赞， 1为点赞
    private String longitude;//经度
    private String latitude;//纬度
    private String location;//poi
    private ForwardBean transArticleBo;
    private int isTransArticle;//是否转发  1是 2否
    private int isExpire;  //是否过期 1表示未过期 2表示过期

    private int state;//0 有限公开（指定用户） 1 公开 2 私有 3 即将过期 4 逻辑删除  5 已过期
    private long expireDate;//过期时间 单位秒
    private int userType;//0为普通用户 1=个人公众号    2=公司公众号
    private List<FollowLikeBean> followLikes;//关注的人点赞信息
    private int top;//1=置顶 默认0
    private int activeType;//0为非活跃用户1为活跃用户
    private String activeIcon;
    private String createUuid;
    private int collectType;//0为未收藏， 1为收藏
    private long liveCount;//直播观看数
    private int blockMoments; //是否关闭朋友圈  1是开启   2是关闭
    private int followed;//0未关注1关注

    public int getFollowed() {
        return followed;
    }

    public void setFollowed(int followed) {
        this.followed = followed;
    }

    private int blockedByMe;  //是否屏蔽    1为已屏蔽   0为未屏蔽

    public int getBlockedByMe() {
        return blockedByMe;
    }

    public void setBlockedByMe(int blockedByMe) {
        this.blockedByMe = blockedByMe;
    }

    public int getBlockMoments() {
        return blockMoments;
    }

    public void setBlockMoments(int blockMoments) {
        this.blockMoments = blockMoments;
    }

    public long getLiveCount() {
        return liveCount;
    }

    public void setLiveCount(long liveCount) {
        this.liveCount = liveCount;
    }

    private int infom;      //0是通知   1是不通知

    private int allowcomment;   //判断用户是否朋友圈的所属用户的粉丝或者关注者   0是   1不是

    private int comment;  //0是可以评论   1是不可以评论

    private int dataType;//0 moment 1 广告
    private int campaignStatus;//广告状态 2: 投放中


    public int getCampaignStatus() {
        return campaignStatus;
    }

    public void setCampaignStatus(int campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public boolean isMomentAd() {
        return dataType == 1;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getAllowcomment() {
        return allowcomment;
    }

    public void setAllowcomment(int allowcomment) {
        this.allowcomment = allowcomment;
    }

    public int getInfom() {
        return infom;
    }

    public void setInfom(int infom) {
        this.infom = infom;
    }

    public int getIsSave() {
        return collectType;
    }

    public void setIsSave(int isSave) {
        this.collectType = isSave;
    }

    public String getCreateUuid() {
        return createUuid;
    }

    public void setCreateUuid(String createUuid) {
        this.createUuid = createUuid;
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

    public static final int MULTI_TEXT = 0;//文本
    public static final int MULTI_IMG = 1;//图片
    public static final int MULTI_VIDEO = 2;//视频
    public static final int MULTI_AUDIO = 3;//音频
    public static final int MULTI_URL = 4;//网址
    public static final int MULTI_LIVE = 5;//直播
    public static final int MULTI_LOCATION = 6;//定位
    public static final int MULTI_FORWARD_TEXT = 10;//转发文本
    public static final int MULTI_FORWARD_IMG = 11;//转发图片
    public static final int MULTI_FORWARD_VIDEO = 12;//转发视频
    public static final int MULTI_FORWARD_AUDIO = 13;//转发音频
    public static final int MULTI_FORWARD_URL = 14;//转发网址
    public static final int MULTI_FORWARD_LIVE = 15;//转发直播
    public static final int MULTI_FORWARD_LOCATION = 16;//转发定位

    /**
     * 是否支持添加广告
     */
    public boolean isSupportPowerUp() {
        if (dataType == 1 || isTransArticle == 1) {
            return false;
        }
        return getArticleType() == MULTI_TEXT || getArticleType() == MULTI_IMG || getArticleType() == MULTI_VIDEO || getArticleType() == MULTI_AUDIO;
    }

    @Override
    public int getItemType() {
        if (isTransArticle()) {
            int articleType = transArticleBo.getArticleType();
            if (articleType == MomentType.TEXT) {
                return MULTI_FORWARD_TEXT;
            } else if (articleType == MomentType.IMG) {
                return MULTI_FORWARD_IMG;
            } else if (articleType == MomentType.VIDEO) {
                return MULTI_FORWARD_VIDEO;
            } else if (articleType == MomentType.AUDIO) {
                return MULTI_FORWARD_AUDIO;
            } else if (articleType == MomentType.URL) {
                return MULTI_FORWARD_URL;
            } else if (articleType == MomentType.LIVE) {
                return MULTI_FORWARD_LIVE;
            } else if (articleType == MomentType.LOCATION) {
                return MULTI_FORWARD_LOCATION;
            } else {
                return MULTI_FORWARD_TEXT;
            }
        } else {
            if (getArticleType() == MomentType.TEXT) {
                return MULTI_TEXT;
            } else if (getArticleType() == MomentType.IMG) {
                return MULTI_IMG;
            } else if (getArticleType() == MomentType.VIDEO) {
                return MULTI_VIDEO;
            } else if (getArticleType() == MomentType.AUDIO) {
                return MULTI_AUDIO;
            } else if (getArticleType() == MomentType.URL) {
                return MULTI_URL;
            } else if (getArticleType() == MomentType.LIVE) {
                return MULTI_LIVE;
            } else if (getArticleType() == MomentType.LOCATION) {
                return MULTI_LOCATION;
            } else {
                return MULTI_TEXT;
            }
        }
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    //当前图片下标，不存db
    public int index;

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleContentTranslation() {
        return articleContentTranslation;
    }

    public void setArticleContentTranslation(String articleContentTranslation) {
        this.articleContentTranslation = articleContentTranslation;
    }

    public List<PostArticleInfoBean> getArticleInfo() {
        return articleInfo;
    }

    public void setArticleInfo(List<PostArticleInfoBean> articleInfo) {
        this.articleInfo = articleInfo;
    }

    public List<FollowListBean> getViewableUserList() {
        return viewableUserList;
    }

    public void setViewableUserList(List<FollowListBean> viewableUserList) {
        this.viewableUserList = viewableUserList;
    }

    public int getViewable() {
        return viewable;
    }

    public void setViewable(int viewable) {
        this.viewable = viewable;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public int getIsForward() {
        return isForward;
    }

    public void setIsForward(int isForward) {
        this.isForward = isForward;
    }

    public long getPostTime() {
        return postTime;
    }

    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }


    public int getArticleType() {
        return articleType;
    }

    public void setArticleType(int articleType) {
        this.articleType = articleType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<CommentBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentBean> commentList) {
        this.commentList = commentList;
    }


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public ForwardBean getTransArticleBo() {
        return transArticleBo;
    }

    public void setTransArticleBo(ForwardBean transArticleBo) {
        this.transArticleBo = transArticleBo;
    }

    public int getIsTransArticle() {
        return isTransArticle;
    }

    public void setIsTransArticle(int isTransArticle) {
        this.isTransArticle = isTransArticle;
    }

    public int getIsExpire() {
        return isExpire;
    }

    public void setIsExpire(int isExpire) {
        this.isExpire = isExpire;
    }

    public boolean isTransArticle() {
        return isTransArticle == 1;
    }

    public boolean isExpire() {
        return isExpire == 2;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public List<FollowLikeBean> getFollowLikes() {
        return followLikes;
    }

    public void setFollowLikes(List<FollowLikeBean> followLikes) {
        this.followLikes = followLikes;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    /**
     * 转发相关内容(transArticleBo)
     */
    public static class ForwardBean implements Serializable {
        String articleId;//id
        String content;//内容
        int articleType;//被转发朋友圈类型
        List<PostArticleInfoBean> articleInfo;
        String id;
        String sasaiId;
        String nick;
        String head;

        long createTime;//发布时间
        String location;//poi
        int state;
        private int userType;//0为普通用户 1=个人公众号    2=公司公众号
        private int activeType;
        private String activeIcon;
        private long liveCount;//直播观看数

        public long getLiveCount() {
            return liveCount;
        }

        public void setLiveCount(long liveCount) {
            this.liveCount = liveCount;
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

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<PostArticleInfoBean> getArticleInfo() {
            return articleInfo;
        }

        public void setArticleInfo(List<PostArticleInfoBean> articleInfo) {
            this.articleInfo = articleInfo;
        }

        public int getArticleType() {
            return articleType;
        }

        public void setArticleType(int articleType) {
            this.articleType = articleType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSasaiId() {
            return sasaiId;
        }

        public void setSasaiId(String sasaiId) {
            this.sasaiId = sasaiId;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }


        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }

    /**
     * 关注的人点赞信息(返回两个用户)
     */
    public static class FollowLikeBean implements Serializable {
        String userId;
        int userType;
        String userNick;
        String userHead;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getUserNick() {
            return userNick;
        }

        public void setUserNick(String userNick) {
            this.userNick = userNick;
        }

        public String getUserHead() {
            return userHead;
        }

        public void setUserHead(String userHead) {
            this.userHead = userHead;
        }
    }

    @Override
    public String toString() {
        return "MomentBean{" +
                "articleId='" + articleId + '\'' +
                ", articleType=" + articleType +
                ", userId='" + userId + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", articleInfo=" + articleInfo +
                ", viewable=" + viewable +
                ", showTime='" + showTime + '\'' +
                ", isForward=" + isForward +
                ", postTime=" + postTime +
                ", createTime=" + createTime +
                ", likeCount=" + likeCount +
                ", commentCount=" + commentCount +
                ", nickName='" + nickName + '\'' +
                ", image='" + image + '\'' +
                ", commentList=" + commentList +

                ", isLike=" + isLike +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", location='" + location + '\'' +
                ", transArticleBo=" + transArticleBo +
                ", isTransArticle=" + isTransArticle +
                ", isExpire=" + isExpire +

                ", state=" + state +


                ", index=" + index +
                '}';
    }
}

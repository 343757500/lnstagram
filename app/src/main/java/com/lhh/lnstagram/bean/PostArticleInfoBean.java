package com.lhh.lnstagram.bean;

import java.io.Serializable;

public class PostArticleInfoBean implements Serializable {

    private String url;//地址
    private int fileType; //1 图片，2语音，3视频 4.url 5.直播 6.定位
    private int duration;//时长
    private String title;//网页标题
    private String imgUrl;//fileType4缩略图地址
    private int imgWidth;//fileType4缩略图宽度
    private int imgHeight;//fileType4缩略图高度
    private String liveId;//fileType5 直播id
    private int liveSate;//直播状态 0直播中1直播结束2回放
    private boolean isSelected;
    private String proId;
    private String downloadUrl;//下载地址(直播录播)
    private int width;//宽
    private int height;//高
    private String md5;

    //streamUrl > azureUrl > 旧token逻辑
    private String streamUrl;//视频流媒体URL
    private String azureUrl;//文件微软云URL

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public String getAzureUrl() {
        return azureUrl;
    }

    public void setAzureUrl(String azureUrl) {
        this.azureUrl = azureUrl;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }


    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public String getProId() {
        return proId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public int getLiveSate() {
        return liveSate;
    }

    public void setLiveSate(int liveSate) {
        this.liveSate = liveSate;
    }
}

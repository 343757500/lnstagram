package com.lhh.lnstagram.bean;


import com.google.gson.annotations.SerializedName;
import com.lhh.lnstagram.R;


import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

import guide.util.StringUtil;


/**
 * Created by XieWenJun on 2018/10/31.
 */
public class UserInfo extends LitePalSupport implements Serializable, Cloneable {
    /**
     * mobile : 10086
     * email : 474171905@qq.com
     * emailState : 2
     * userNumber : 816033144
     * userId : 816033144
     * nickName : test
     * image : https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2277598369,746711024&fm=26&gp=0.jpg
     * thumbnail : null
     * autograph : 123145
     * gender : 0
     * region : 12123
     * qrCode : 192161c0450c47579a450eccbf4b220b
     * token : b3338ccec4634feb9389c96500b30259
     */

    @Column(unique = true, defaultValue = "")
    public String userId;
    private String token;
    private String tcpToken;
    private String encryption;
    private String mobile;
    private String email;
    private String userNumber; // SASA账号,初期默认为userid
    public String nickName;
    public String image; // 头像
    private String watchNickName; // 缓存watch昵称
    private String watchImage; // 缓存watch头像
    @SerializedName("backImage")
    private String bgImage; // 背景图
    private Object thumbnail; // 头像缩略图
    private String autograph;
    private String countryId;
    private String countryName;
    private String region; // 地区(仅仅是用户在个人资料选择的所在地区，和注册手机对应的国家名字没有半毛钱关系)
    private String mobileCode; // 手机区号
    private String qrCode;
    private int emailState; // 邮箱状态：1，没有绑定，2：未验证 3：绑定成功
    private int gender; // 1--男 2--女 3--其他
    private String kyc; //0为游客、1为手机注册用户
    private String dateOfBirth;
    private long updateTime;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmailState() {
        return emailState;
    }

    public void setEmailState(int emailState) {
        this.emailState = emailState;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserId() {
        return userId + "";
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickName + "";
    }

    public void setNickname(String nickName) {
        this.nickName = nickName;
    }

    public String getImage() {
        return image + "";
    }

    public int getImageInteger() {
        try {
            return Integer.valueOf(image);
        } catch (Exception e) {
            return 0;
        }
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBgImage() {
        return bgImage;
    }

    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
    }

    public Object getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Object thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getToken() {
        return token + "";
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTcpToken() {
        return tcpToken + "";
    }

    public void setTcpToken(String tcpToken) {
        this.tcpToken = tcpToken;
    }

    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getWatchNickName() {
        return watchNickName;
    }

    public void setWatchNickName(String watchNickName) {
        this.watchNickName = watchNickName;
    }

    public String getWatchImage() {
        return watchImage;
    }

    public void setWatchImage(String watchImage) {
        this.watchImage = watchImage;
    }

    public String getKyc() {
        return kyc;
    }

    public void setKyc(String kyc) {
        this.kyc = kyc;
    }

    public String getEmailStateStr() {
        // 邮箱状态：1，没有绑定，2：未验证 3：绑定成功
        if (1 == emailState) {
            return "";
        } else if (2 == emailState) {
            return StringUtil.getString(R.string.common_txt_link_wait_verify);
        } else if (3 == emailState) {
            return "";
        }
        return "";
    }

    @Override
    public UserInfo clone() {
        UserInfo bean = null;
        try {
            bean = (UserInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            bean = this;
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", encryption='" + encryption + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", nickName='" + nickName + '\'' +
                ", image='" + image + '\'' +
                ", bgImage='" + bgImage + '\'' +
                ", thumbnail=" + thumbnail +
                ", autograph='" + autograph + '\'' +
                ", region='" + region + '\'' +
                ", mobileCode='" + mobileCode + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", emailState=" + emailState +
                ", gender=" + gender +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}

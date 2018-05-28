
package com.reklamar.testpandora.classes.userauth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reklamar.testpandora.classes.stations.Station;

import java.util.List;

public class User {

    @SerializedName("stationCreationAdUrl")
    @Expose
    private String stationCreationAdUrl;
    @SerializedName("hasAudioAds")
    @Expose
    private Boolean hasAudioAds;
    @SerializedName("splashScreenAdUrl")
    @Expose
    private String splashScreenAdUrl;
    @SerializedName("videoAdUrl")
    @Expose
    private String videoAdUrl;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("canListen")
    @Expose
    private Boolean canListen;
    @SerializedName("nowPlayingAdUrl")
    @Expose
    private String nowPlayingAdUrl;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("listeningTimeoutMinutes")
    @Expose
    private String listeningTimeoutMinutes;
    @SerializedName("maxStationsAllowed")
    @Expose
    private Integer maxStationsAllowed;
    @SerializedName("listeningTimeoutAlertMsgUri")
    @Expose
    private String listeningTimeoutAlertMsgUri;
    @SerializedName("userProfileUrl")
    @Expose
    private String userProfileUrl;
    @SerializedName("minimumAdRefreshInterval")
    @Expose
    private Integer minimumAdRefreshInterval;
    @SerializedName("userAuthToken")
    @Expose
    private String userAuthToken;

    public String getStationCreationAdUrl() {
        return stationCreationAdUrl;
    }

    public void setStationCreationAdUrl(String stationCreationAdUrl) {
        this.stationCreationAdUrl = stationCreationAdUrl;
    }

    public Boolean getHasAudioAds() {
        return hasAudioAds;
    }

    public void setHasAudioAds(Boolean hasAudioAds) {
        this.hasAudioAds = hasAudioAds;
    }

    public String getSplashScreenAdUrl() {
        return splashScreenAdUrl;
    }

    public void setSplashScreenAdUrl(String splashScreenAdUrl) {
        this.splashScreenAdUrl = splashScreenAdUrl;
    }

    public String getVideoAdUrl() {
        return videoAdUrl;
    }

    public void setVideoAdUrl(String videoAdUrl) {
        this.videoAdUrl = videoAdUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getCanListen() {
        return canListen;
    }

    public void setCanListen(Boolean canListen) {
        this.canListen = canListen;
    }

    public String getNowPlayingAdUrl() {
        return nowPlayingAdUrl;
    }

    public void setNowPlayingAdUrl(String nowPlayingAdUrl) {
        this.nowPlayingAdUrl = nowPlayingAdUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getListeningTimeoutMinutes() {
        return listeningTimeoutMinutes;
    }

    public void setListeningTimeoutMinutes(String listeningTimeoutMinutes) {
        this.listeningTimeoutMinutes = listeningTimeoutMinutes;
    }

    public Integer getMaxStationsAllowed() {
        return maxStationsAllowed;
    }

    public void setMaxStationsAllowed(Integer maxStationsAllowed) {
        this.maxStationsAllowed = maxStationsAllowed;
    }

    public String getListeningTimeoutAlertMsgUri() {
        return listeningTimeoutAlertMsgUri;
    }

    public void setListeningTimeoutAlertMsgUri(String listeningTimeoutAlertMsgUri) {
        this.listeningTimeoutAlertMsgUri = listeningTimeoutAlertMsgUri;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }

    public Integer getMinimumAdRefreshInterval() {
        return minimumAdRefreshInterval;
    }

    public void setMinimumAdRefreshInterval(Integer minimumAdRefreshInterval) {
        this.minimumAdRefreshInterval = minimumAdRefreshInterval;
    }

    public String getUserAuthToken() {
        return userAuthToken;
    }

    public void setUserAuthToken(String userAuthToken) {
        this.userAuthToken = userAuthToken;
    }

}

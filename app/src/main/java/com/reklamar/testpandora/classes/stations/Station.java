
package com.reklamar.testpandora.classes.stations;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reklamar.testpandora.classes.song.Song;

public class Station {

    @SerializedName("suppressVideoAds")
    @Expose
    private Boolean suppressVideoAds;
    @SerializedName("isQuickMix")
    @Expose
    private Boolean isQuickMix;
    @SerializedName("stationId")
    @Expose
    private String stationId;
    @SerializedName("stationDetailUrl")
    @Expose
    private String stationDetailUrl;
    @SerializedName("isShared")
    @Expose
    private Boolean isShared;
    @SerializedName("dateCreated")
    @Expose
    private StationDateCreated dateCreated;
    @SerializedName("stationToken")
    @Expose
    private String stationToken;
    @SerializedName("stationName")
    @Expose
    private String stationName;
    @SerializedName("stationSharingUrl")
    @Expose
    private String stationSharingUrl;
    @SerializedName("requiresCleanAds")
    @Expose
    private Boolean requiresCleanAds;
    @SerializedName("allowRename")
    @Expose
    private Boolean allowRename;
    @SerializedName("allowAddMusic")
    @Expose
    private Boolean allowAddMusic;
    @SerializedName("quickMixStationIds")
    @Expose
    private List<String> quickMixStationIds = null;
    @SerializedName("allowDelete")
    @Expose
    private Boolean allowDelete;
    @SerializedName("allowEditDescription")
    @Expose
    private Boolean allowEditDescription;
    @SerializedName("artUrl")
    @Expose
    private String artUrl;

    @SerializedName("music")
    @Expose
    private Object music;

    public Object getMusic() {
        return music;
    }

    public void setMusic(List<Song> music) {
        this.music = music;
    }

    public String getArtUrl() {
        return artUrl;
    }

    public void setArtUrl(String artUrl) {
        this.artUrl = artUrl;
    }

    public Boolean getSuppressVideoAds() {
        return suppressVideoAds;
    }

    public void setSuppressVideoAds(Boolean suppressVideoAds) {
        this.suppressVideoAds = suppressVideoAds;
    }

    public Boolean getIsQuickMix() {
        return isQuickMix;
    }

    public void setIsQuickMix(Boolean isQuickMix) {
        this.isQuickMix = isQuickMix;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationDetailUrl() {
        return stationDetailUrl;
    }

    public void setStationDetailUrl(String stationDetailUrl) {
        this.stationDetailUrl = stationDetailUrl;
    }

    public Boolean getIsShared() {
        return isShared;
    }

    public void setIsShared(Boolean isShared) {
        this.isShared = isShared;
    }

    public StationDateCreated getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(StationDateCreated dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStationToken() {
        return stationToken;
    }

    public void setStationToken(String stationToken) {
        this.stationToken = stationToken;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationSharingUrl() {
        return stationSharingUrl;
    }

    public void setStationSharingUrl(String stationSharingUrl) {
        this.stationSharingUrl = stationSharingUrl;
    }

    public Boolean getRequiresCleanAds() {
        return requiresCleanAds;
    }

    public void setRequiresCleanAds(Boolean requiresCleanAds) {
        this.requiresCleanAds = requiresCleanAds;
    }

    public Boolean getAllowRename() {
        return allowRename;
    }

    public void setAllowRename(Boolean allowRename) {
        this.allowRename = allowRename;
    }

    public Boolean getAllowAddMusic() {
        return allowAddMusic;
    }

    public void setAllowAddMusic(Boolean allowAddMusic) {
        this.allowAddMusic = allowAddMusic;
    }

    public List<String> getQuickMixStationIds() {
        return quickMixStationIds;
    }

    public void setQuickMixStationIds(List<String> quickMixStationIds) {
        this.quickMixStationIds = quickMixStationIds;
    }

    public Boolean getAllowDelete() {
        return allowDelete;
    }

    public void setAllowDelete(Boolean allowDelete) {
        this.allowDelete = allowDelete;
    }

    public Boolean getAllowEditDescription() {
        return allowEditDescription;
    }

    public void setAllowEditDescription(Boolean allowEditDescription) {
        this.allowEditDescription = allowEditDescription;
    }

}

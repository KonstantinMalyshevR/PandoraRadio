
package com.reklamar.testpandora.classes.song;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song {

    @SerializedName("trackToken")
    @Expose
    private String trackToken;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("albumName")
    @Expose
    private String albumName;
    @SerializedName("amazonAlbumUrl")
    @Expose
    private String amazonAlbumUrl;
    @SerializedName("songExplorerUrl")
    @Expose
    private String songExplorerUrl;
    @SerializedName("albumArtUrl")
    @Expose
    private String albumArtUrl;
    @SerializedName("artistDetailUrl")
    @Expose
    private String artistDetailUrl;
    @SerializedName("audioUrlMap")
    @Expose
    private SongAudioUrlMap audioUrlMap;
    @SerializedName("itunesSongUrl")
    @Expose
    private String itunesSongUrl;
    @SerializedName("additionalAudioUrl")
    @Expose
    private List<String> additionalAudioUrl = null;
    @SerializedName("amazonAlbumAsin")
    @Expose
    private String amazonAlbumAsin;
    @SerializedName("amazonAlbumDigitalAsin")
    @Expose
    private String amazonAlbumDigitalAsin;
    @SerializedName("artistExplorerUrl")
    @Expose
    private String artistExplorerUrl;
    @SerializedName("songName")
    @Expose
    private String songName;
    @SerializedName("albumDetailUrl")
    @Expose
    private String albumDetailUrl;
    @SerializedName("songDetailUrl")
    @Expose
    private String songDetailUrl;
    @SerializedName("stationId")
    @Expose
    private String stationId;
    @SerializedName("songRating")
    @Expose
    private Integer songRating;
    @SerializedName("trackGain")
    @Expose
    private String trackGain;
    @SerializedName("albumExplorerUrl")
    @Expose
    private String albumExplorerUrl;
    @SerializedName("allowFeedback")
    @Expose
    private Boolean allowFeedback;
    @SerializedName("amazonSongDigitalAsin")
    @Expose
    private String amazonSongDigitalAsin;
    @SerializedName("nowPlayingStationAdUrl")
    @Expose
    private String nowPlayingStationAdUrl;

    public String getTrackToken() {
        return trackToken;
    }

    public void setTrackToken(String trackToken) {
        this.trackToken = trackToken;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAmazonAlbumUrl() {
        return amazonAlbumUrl;
    }

    public void setAmazonAlbumUrl(String amazonAlbumUrl) {
        this.amazonAlbumUrl = amazonAlbumUrl;
    }

    public String getSongExplorerUrl() {
        return songExplorerUrl;
    }

    public void setSongExplorerUrl(String songExplorerUrl) {
        this.songExplorerUrl = songExplorerUrl;
    }

    public String getAlbumArtUrl() {
        return albumArtUrl;
    }

    public void setAlbumArtUrl(String albumArtUrl) {
        this.albumArtUrl = albumArtUrl;
    }

    public String getArtistDetailUrl() {
        return artistDetailUrl;
    }

    public void setArtistDetailUrl(String artistDetailUrl) {
        this.artistDetailUrl = artistDetailUrl;
    }

    public SongAudioUrlMap getAudioUrlMap() {
        return audioUrlMap;
    }

    public void setAudioUrlMap(SongAudioUrlMap audioUrlMap) {
        this.audioUrlMap = audioUrlMap;
    }

    public String getItunesSongUrl() {
        return itunesSongUrl;
    }

    public void setItunesSongUrl(String itunesSongUrl) {
        this.itunesSongUrl = itunesSongUrl;
    }

    public List<String> getAdditionalAudioUrl() {
        return additionalAudioUrl;
    }

    public void setAdditionalAudioUrl(List<String> additionalAudioUrl) {
        this.additionalAudioUrl = additionalAudioUrl;
    }

    public String getAmazonAlbumAsin() {
        return amazonAlbumAsin;
    }

    public void setAmazonAlbumAsin(String amazonAlbumAsin) {
        this.amazonAlbumAsin = amazonAlbumAsin;
    }

    public String getAmazonAlbumDigitalAsin() {
        return amazonAlbumDigitalAsin;
    }

    public void setAmazonAlbumDigitalAsin(String amazonAlbumDigitalAsin) {
        this.amazonAlbumDigitalAsin = amazonAlbumDigitalAsin;
    }

    public String getArtistExplorerUrl() {
        return artistExplorerUrl;
    }

    public void setArtistExplorerUrl(String artistExplorerUrl) {
        this.artistExplorerUrl = artistExplorerUrl;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumDetailUrl() {
        return albumDetailUrl;
    }

    public void setAlbumDetailUrl(String albumDetailUrl) {
        this.albumDetailUrl = albumDetailUrl;
    }

    public String getSongDetailUrl() {
        return songDetailUrl;
    }

    public void setSongDetailUrl(String songDetailUrl) {
        this.songDetailUrl = songDetailUrl;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Integer getSongRating() {
        return songRating;
    }

    public void setSongRating(Integer songRating) {
        this.songRating = songRating;
    }

    public String getTrackGain() {
        return trackGain;
    }

    public void setTrackGain(String trackGain) {
        this.trackGain = trackGain;
    }

    public String getAlbumExplorerUrl() {
        return albumExplorerUrl;
    }

    public void setAlbumExplorerUrl(String albumExplorerUrl) {
        this.albumExplorerUrl = albumExplorerUrl;
    }

    public Boolean getAllowFeedback() {
        return allowFeedback;
    }

    public void setAllowFeedback(Boolean allowFeedback) {
        this.allowFeedback = allowFeedback;
    }

    public String getAmazonSongDigitalAsin() {
        return amazonSongDigitalAsin;
    }

    public void setAmazonSongDigitalAsin(String amazonSongDigitalAsin) {
        this.amazonSongDigitalAsin = amazonSongDigitalAsin;
    }

    public String getNowPlayingStationAdUrl() {
        return nowPlayingStationAdUrl;
    }

    public void setNowPlayingStationAdUrl(String nowPlayingStationAdUrl) {
        this.nowPlayingStationAdUrl = nowPlayingStationAdUrl;
    }

}

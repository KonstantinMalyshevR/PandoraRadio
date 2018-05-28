package com.reklamar.testpandora.classes.api;

/**
 * Created by Developer on 22.05.18.
 */

public class SongPlaylistRequest {

    private String userAuthToken;
    private String additionalAudioUrl;
    private int syncTime;
    private String stationToken;

    public SongPlaylistRequest(String userAuthToken, String additionalAudioUrl, int syncTime, String stationToken) {
        this.userAuthToken = userAuthToken;
        this.additionalAudioUrl = additionalAudioUrl;
        this.syncTime = syncTime;
        this.stationToken = stationToken;
    }
}
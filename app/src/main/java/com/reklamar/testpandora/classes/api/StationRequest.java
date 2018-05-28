package com.reklamar.testpandora.classes.api;

/**
 * Created by Developer on 21.05.18.
 */

public class StationRequest {

    private String userAuthToken;
    private int syncTime;
    private Boolean includeStationSeeds;
    private Boolean includeStationArtUrl;

    public StationRequest(String userAuthToken, int syncTime) {
        this.userAuthToken = userAuthToken;
        this.syncTime = syncTime;
        includeStationSeeds = true;
        includeStationArtUrl = true;
    }
}

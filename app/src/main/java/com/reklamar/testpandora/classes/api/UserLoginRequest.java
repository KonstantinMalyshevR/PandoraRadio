package com.reklamar.testpandora.classes.api;

/**
 * Created by Developer on 19.05.18.
 */

public class UserLoginRequest {

    private String loginType;
    private String username;
    private String password;
    private String partnerAuthToken;
    private String partner_id;
    private Boolean includePandoraOneInfo;
    private Boolean includeAdAttributes;
    private Boolean includeSubscriptionExpiration;
    private Boolean includeStationArtUrl;
    private Boolean returnStationList;
    private Boolean returnGenreStations;
    private int syncTime;

    public UserLoginRequest(String username, String password, String partnerAuthToken, String partnerId, int syncTime) {
        this.loginType = "user";
        this.username = username;
        this.password = password;
        this.partnerAuthToken = partnerAuthToken;
        this.partner_id = partnerId;
        this.syncTime = syncTime;

        this.includePandoraOneInfo = true;
        this.includeAdAttributes = true;
        this.includeSubscriptionExpiration = true;
        this.includeStationArtUrl = true;
        this.returnStationList = true;
        this.returnGenreStations = true;
    }
}
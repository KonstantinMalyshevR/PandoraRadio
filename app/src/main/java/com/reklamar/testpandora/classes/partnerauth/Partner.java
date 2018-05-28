
package com.reklamar.testpandora.classes.partnerauth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Partner {

    @SerializedName("syncTime")
    @Expose
    private String syncTime;
    @SerializedName("partnerAuthToken")
    @Expose
    private String partnerAuthToken;
    @SerializedName("partnerId")
    @Expose
    private String partnerId;
    @SerializedName("stationSkipUnit")
    @Expose
    private String stationSkipUnit;
    @SerializedName("stationSkipLimit")
    @Expose
    private Integer stationSkipLimit;

    public String getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(String syncTime) {
        this.syncTime = syncTime;
    }

    public String getPartnerAuthToken() {
        return partnerAuthToken;
    }

    public void setPartnerAuthToken(String partnerAuthToken) {
        this.partnerAuthToken = partnerAuthToken;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getStationSkipUnit() {
        return stationSkipUnit;
    }

    public void setStationSkipUnit(String stationSkipUnit) {
        this.stationSkipUnit = stationSkipUnit;
    }

    public Integer getStationSkipLimit() {
        return stationSkipLimit;
    }

    public void setStationSkipLimit(Integer stationSkipLimit) {
        this.stationSkipLimit = stationSkipLimit;
    }
}
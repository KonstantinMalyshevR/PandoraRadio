
package com.reklamar.testpandora.classes.stations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationResponse {

    @SerializedName("stat")
    @Expose
    private String stat;
    @SerializedName("result")
    @Expose
    private StationResult result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private String code;

    public String getMessage() {
        if(message != null){
            return message;
        }else {
            return "";
        }
    }

    public String getCode() {
        if(code != null){
            return code;
        }else {
            return "";
        }
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public StationResult getStationResult() {
        return result;
    }

    public void setStationResult(StationResult result) {
        this.result = result;
    }

}


package com.reklamar.testpandora.classes.stations;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationResult {

    @SerializedName("stations")
    @Expose
    private List<Station> stations = null;
    @SerializedName("checksum")
    @Expose
    private String checksum;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

}


package com.reklamar.testpandora.classes.song;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongAudioUrlMap {

    @SerializedName("highQuality")
    @Expose
    private HighQuality highQuality;
    @SerializedName("mediumQuality")
    @Expose
    private MediumQuality mediumQuality;
    @SerializedName("lowQuality")
    @Expose
    private LowQuality lowQuality;

    public HighQuality getHighQuality() {
        return highQuality;
    }

    public void setHighQuality(HighQuality highQuality) {
        this.highQuality = highQuality;
    }

    public MediumQuality getMediumQuality() {
        return mediumQuality;
    }

    public void setMediumQuality(MediumQuality mediumQuality) {
        this.mediumQuality = mediumQuality;
    }

    public LowQuality getLowQuality() {
        return lowQuality;
    }

    public void setLowQuality(LowQuality lowQuality) {
        this.lowQuality = lowQuality;
    }

}

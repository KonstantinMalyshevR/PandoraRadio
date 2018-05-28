
package com.reklamar.testpandora.classes.song;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HighQuality {

    @SerializedName("bitrate")
    @Expose
    private String bitrate;
    @SerializedName("encoding")
    @Expose
    private String encoding;
    @SerializedName("audioUrl")
    @Expose
    private String audioUrl;
    @SerializedName("protocol")
    @Expose
    private String protocol;

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

}

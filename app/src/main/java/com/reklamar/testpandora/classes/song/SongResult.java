
package com.reklamar.testpandora.classes.song;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongResult {

    @SerializedName("seed")
    @Expose
    private Object seed;

    @SerializedName("seeds")
    @Expose
    private Object seeds;

    @SerializedName("songs")
    @Expose
    private Object songs;

    @SerializedName("music")
    @Expose
    private Object music;

    @SerializedName("items")
    @Expose
    private List<Song> items = null;


    public List<Song> getItems() {
        return items;
    }

    public void setItems(List<Song> items) {
        this.items = items;
    }
}
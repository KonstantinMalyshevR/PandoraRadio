package com.reklamar.testpandora.classes.workclasses;

import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.reklamar.testpandora.classes.partnerauth.Partner;
import com.reklamar.testpandora.classes.stations.Station;
import com.reklamar.testpandora.classes.userauth.User;

/**
 * Created by Developer on 21.05.18.
 */

public class RootApplication extends android.app.Application {

    private static RootApplication instance;
    private Partner partner;
    private User user;
    private int timeOffset;
    private Station station;

    public RootApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initImageLoader(getApplicationContext());
    }

    public static RootApplication getInstance() {
        return instance;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setTimeOffset(int timeOffset) {
        this.timeOffset = timeOffset;
    }

    public int getTimeOffset() {
        return timeOffset;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Station getStation() {
        return station;
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }
}

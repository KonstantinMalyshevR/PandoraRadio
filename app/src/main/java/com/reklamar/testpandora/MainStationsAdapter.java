package com.reklamar.testpandora;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.reklamar.testpandora.classes.stations.Station;
import com.reklamar.testpandora.classes.workclasses.SupportClass;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Developer on 21.05.18.
 */

public class MainStationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected ImageLoader imageLoader;
    private List<Station> objects;

    private Context context;

    public MainStationsAdapter(Context context) {
        this.context = context;
        objects = new ArrayList<>();
        imageLoader = ImageLoader.getInstance();

    }

    public void setItems(List<Station> itemList) {
        objects.clear();
        objects.addAll(itemList);
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SolventViewHolders vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_item, null);
        vh = new SolventViewHolders(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holderF, final int position) {

        final SolventViewHolders holder = (SolventViewHolders) holderF;

        Station station = objects.get(position);

        holder.title.setText(SupportClass.checkStringNullAndTrim(station.getStationName()));

        final String urlImage = SupportClass.checkStringNullAndTrim(station.getArtUrl());
        imageLoader.displayImage(urlImage, holder.image, SupportClass.displayImageOptions(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                holder.progress.setVisibility(View.VISIBLE);
            }
            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                switch (failReason.getType()) {
                    case IO_ERROR:
                        break;
                    case DECODING_ERROR:
                        break;
                    case NETWORK_DENIED:
                        break;
                    case OUT_OF_MEMORY:
                        break;
                    case UNKNOWN:
                        break;
                }
                holder.progress.setVisibility(View.GONE);
                holder.image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.progress.setVisibility(View.GONE);
                if(urlImage.equals("")){
                    holder.image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                }else{
                    holder.image.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.objects.size();
    }

    class SolventViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.title) TextView title;
        @BindView(R.id.image) ImageView image;
        @BindView(R.id.progress) ProgressBar progress;

        public SolventViewHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(view, objects.get(getAdapterPosition()));
        }
    }

    //=========
    private StationOnItemClickListener mItemClickListener;

    public interface StationOnItemClickListener {
        void onItemClick(View view, Station station);
    }

    public void SetOnItemClickListener(final StationOnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}

package com.reklamar.testpandora;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.reklamar.testpandora.classes.stations.Station;
import com.reklamar.testpandora.classes.workclasses.SupportClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer on 21.05.18.
 */

public class MainSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected ImageLoader imageLoader;
    private List<Station> objects;

    private Context context;

    public MainSearchAdapter(Context context) {
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

        //holder.postitem_post.setText(SupportClass.checkStringNullAndTrim(station.getStationName()));
        //holder.postitem_site.setText(SupportClass.checkStringNullAndTrim(station.getStationId()));
    }

    @Override
    public int getItemCount() {
        return this.objects.size();
    }

    private class SolventViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView postitem_post;
        private TextView postitem_site;

        private SolventViewHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            //postitem_post = itemView.findViewById(R.id.postitem_post);
            //postitem_site = itemView.findViewById(R.id.postitem_site);
        }

        @Override
        public void onClick(View view) {
            mItemClickListener.onItemClick(view, objects.get(getAdapterPosition()));
        }
    }

    //=========
    private SearchOnItemClickListener mItemClickListener;

    public interface SearchOnItemClickListener {
        void onItemClick(View view, Station station);
    }

    public void SetOnItemClickListener(final SearchOnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
package com.reklamar.testpandora;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.reklamar.testpandora.classes.api.Controller;
import com.reklamar.testpandora.classes.api.PandoraApi;
import com.reklamar.testpandora.classes.api.StationRequest;
import com.reklamar.testpandora.classes.stations.Station;
import com.reklamar.testpandora.classes.stations.StationResponse;
import com.reklamar.testpandora.classes.workclasses.RootApplication;
import com.reklamar.testpandora.classes.workclasses.SupportClass;
import com.singh.daman.proprogressviews.CircleLineProgress;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Developer on 21.05.18.
 */

public class MainActivity extends AppCompatActivity {

    private PandoraApi pandoraApi;

    MainStationsAdapter adapterStations;
    MainSearchAdapter adapterSearch;
    protected ImageLoader imageLoader;

    @BindView(R.id.tool_bar) Toolbar tool_bar;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.progress) CircleLineProgress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(tool_bar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        tool_bar.setTitle("Pandora Radio, Станции");

        imageLoader = ImageLoader.getInstance();

        LinearLayoutManager magLinearManager = new LinearLayoutManager(this);
        magLinearManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(magLinearManager);

        setStationsAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkStationsList();
    }

    private void setStationsAdapter(){
        adapterStations = new MainStationsAdapter(MainActivity.this);
        recyclerView.setAdapter(adapterStations);

        adapterStations.SetOnItemClickListener(new MainStationsAdapter.StationOnItemClickListener() {
            @Override
            public void onItemClick(View view, Station station) {
                RootApplication.getInstance().setStation(station);

                startActivity(new Intent(MainActivity.this, PlayerActivity.class));
            }
        });
    }

    private void setSearchAdapter(){
        adapterSearch = new MainSearchAdapter(MainActivity.this);
        recyclerView.setAdapter(adapterSearch);

        adapterSearch.SetOnItemClickListener(new MainSearchAdapter.SearchOnItemClickListener() {
            @Override
            public void onItemClick(View view, Station object) {

            }
        });
    }

    private void checkStationsList(){

        progressEnabled(true);

        String userId = ((RootApplication) this.getApplication()).getUser().getUserId();
        String partnerId = ((RootApplication) this.getApplication()).getPartner().getPartnerId();

        String userAuthToken = ((RootApplication) this.getApplication()).getUser().getUserAuthToken();
        int timeOffset = ((RootApplication) this.getApplication()).getTimeOffset();
        int timeSync = (int) (System.currentTimeMillis() / 1000L) + timeOffset;

        pandoraApi = Controller.getApiSecure();

        StationRequest stationRequest = new StationRequest(userAuthToken, timeSync);

        pandoraApi.stationQuery(userId, partnerId, userAuthToken, stationRequest).enqueue(new Callback<StationResponse>() {
            @Override
            public void onResponse(@NonNull Call<StationResponse> call, @NonNull Response<StationResponse> response) {
                progressEnabled(false);
                if (response.code() == 200) {
                    if(response.body() != null && response.body().getStat() != null && response.body().getStat().equals("ok")){

                        Log.w("wrapped in gson Station",new Gson().toJson(response));

                        refreshRecyclerViewStations(response.body().getStationResult().getStations());

                    }else if(response.body().getCode().equals("12")){
                        SupportClass.ToastMessage(MainActivity.this, "Сервис не доступен в Вашей стране, проверьте VPN");
                    }else{
                        SupportClass.ToastMessage(MainActivity.this, "Что-то пошло не так=( " + response.body().getMessage() + " - Код: " + response.body().getCode());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<StationResponse> call, @NonNull Throwable t) {
                progressEnabled(false);
                SupportClass.ToastMessage(MainActivity.this, t.toString());
            }
        });
    }

    private void refreshRecyclerViewStations(List<Station> stations_list){
        adapterStations.setItems(stations_list);
        adapterStations.notifyDataSetChanged();
    }

    private void progressEnabled(Boolean value){
        if (value) {
            progress.setVisibility(View.VISIBLE);
        }else{
            progress.setVisibility(View.GONE);
        }
    }
}
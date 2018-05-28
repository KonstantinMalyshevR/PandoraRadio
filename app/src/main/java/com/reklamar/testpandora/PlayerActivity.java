package com.reklamar.testpandora;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.reklamar.testpandora.classes.api.Controller;
import com.reklamar.testpandora.classes.api.PandoraApi;
import com.reklamar.testpandora.classes.api.SongPlaylistRequest;
import com.reklamar.testpandora.classes.song.Song;
import com.reklamar.testpandora.classes.song.SongResponse;
import com.reklamar.testpandora.classes.stations.Station;
import com.reklamar.testpandora.classes.stations.StationResponse;
import com.reklamar.testpandora.classes.workclasses.RootApplication;
import com.reklamar.testpandora.classes.workclasses.SupportClass;
import com.singh.daman.proprogressviews.CircleLineProgress;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Developer on 21.05.18.
 */

public class PlayerActivity extends AppCompatActivity implements OnTouchListener, OnCompletionListener, OnBufferingUpdateListener {

    @BindView(R.id.tool_bar) Toolbar tool_bar;

    @BindView(R.id.textView) TextView textView;

    @BindView(R.id.progress) CircleLineProgress progress;

    @BindView(R.id.song_image) ImageView song_image;
    @BindView(R.id.song_title) TextView song_title;
    @BindView(R.id.song_description) TextView song_description;

    @BindView(R.id.button_rewind) ImageButton button_rewind;
    @BindView(R.id.button_playpause) ImageButton button_playpause;
    @BindView(R.id.button_forward) ImageButton button_forward;
    @BindView(R.id.seekbar_play) SeekBar seekbar_play;

    private PandoraApi pandoraApi;

    private MediaPlayer mediaPlayer;
    private int mediaFileLengthInMilliseconds;

    private final Handler handler = new Handler();

    private Station station;

    private String playSongUrl = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        ButterKnife.bind(this);

        setSupportActionBar(tool_bar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        station = ((RootApplication) this.getApplication()).getStation();

        tool_bar.setTitle(station.getStationName());

        initView();

        checkPlayList();
    }

    private void initView() {

        textView.setText("Station: " + station.getStationName() + " Token: " + station.getStationToken() + " Id: " + station.getStationId());

        seekbar_play.setMax(99); // It means 100% .0-99
        seekbar_play.setOnTouchListener(this);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);
    }

    private void checkPlayList(){

        progressEnabled(true);

        String userId = ((RootApplication) this.getApplication()).getUser().getUserId();
        String partnerId = ((RootApplication) this.getApplication()).getPartner().getPartnerId();

        String userAuthToken = ((RootApplication) this.getApplication()).getUser().getUserAuthToken();

        int timeOffset = ((RootApplication) this.getApplication()).getTimeOffset();
        int timeSync = (int) (System.currentTimeMillis() / 1000L) + timeOffset;

        pandoraApi = Controller.getApiSecure();

        String additionalAudioUrl = "HTTP_32_AACPLUS_ADTS,HTTP_64_AACPLUS_ADTS,HTTP_128_MP3";

        SongPlaylistRequest songPlaylistRequest = new SongPlaylistRequest(userAuthToken, additionalAudioUrl, timeSync, station.getStationToken());

        pandoraApi.songPlaylistQuery(userId, partnerId, userAuthToken, songPlaylistRequest).enqueue(new Callback<SongResponse>() {
            @Override
            public void onResponse(@NonNull Call<SongResponse> call, @NonNull Response<SongResponse> response) {
                progressEnabled(false);

                if (response.code() == 200) {
                    if(response.body() != null && response.body().getStat() != null && response.body().getStat().equals("ok")){

                        Log.w("wrapped in gson",new Gson().toJson(response));
//                        refreshRecyclerViewStations(response.body().getStationResult().getStations());

                        List<Song> list = response.body().getSongResult().getItems();
                        if (list == null){
                            SupportClass.ToastMessage(PlayerActivity.this, "Плейлиста нет");
                        }else{
                            Song song = list.get(2);
                            playSongUrl = song.getAudioUrlMap().getLowQuality().getAudioUrl();
                        }

                    }else if(response.body().getCode().equals("12")){
                        SupportClass.ToastMessage(PlayerActivity.this, "Сервис не доступен в Вашей стране, проверьте VPN");
                    }else if(response.body().getCode().equals("1003")){
                        SupportClass.ToastMessage(PlayerActivity.this, "Необходимо приобрести подписку");
                    }else{
                        SupportClass.ToastMessage(PlayerActivity.this, "Что-то пошло не так=( " + response.body().getMessage() + " - Код: " + response.body().getCode());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SongResponse> call, @NonNull Throwable t) {
                progressEnabled(false);
                SupportClass.ToastMessage(PlayerActivity.this, t.toString());
            }
        });
    }


    /**
     * Method which updates the SeekBar primary progress by current song playing position
     */
    private void primarySeekBarProgressUpdater() {
        seekbar_play.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaFileLengthInMilliseconds) * 100)); // This math construction give a percentage of "was playing"/"song length"
        if (mediaPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    primarySeekBarProgressUpdater();
                }
            };
            handler.postDelayed(notification, 1000);
        }
    }

    @OnClick(R.id.button_playpause)
    public void loginClick(Button button) {
        /** ImageButton onClick event handler. Method which start/pause mediaplayer playing */
        try {
            mediaPlayer.setDataSource(playSongUrl); // setup song from http://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3 URL to mediaplayer data source
            mediaPlayer.prepare(); // you must call this method after setup the datasource in setDataSource method. After calling prepare() the instance of MediaPlayer starts load data from URL to internal buffer.
        } catch (Exception e) {
            e.printStackTrace();
        }

        mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            button_playpause.setImageResource(R.drawable.pause);
        } else {
            mediaPlayer.pause();
            button_playpause.setImageResource(R.drawable.play);
        }

        primarySeekBarProgressUpdater();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.button_playpause) {
            /** Seekbar onTouch event handler. Method which seeks MediaPlayer to seekBar primary progress position*/
            if (mediaPlayer.isPlaying()) {
                SeekBar sb = (SeekBar) v;
                int playPositionInMillisecconds = (mediaFileLengthInMilliseconds / 100) * sb.getProgress();
                mediaPlayer.seekTo(playPositionInMillisecconds);
            }
        }
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        /** MediaPlayer onCompletion event handler. Method which calls then song playing is complete*/
        button_playpause.setImageResource(R.drawable.play);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        /** Method which updates the SeekBar secondary progress by current song loading from URL position*/
        seekbar_play.setSecondaryProgress(percent);
    }

    private void progressEnabled(Boolean value){
        if (value) {
            progress.setVisibility(View.VISIBLE);
        }else{
            progress.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
package com.reklamar.testpandora.classes.api;

/**
 * Created by Developer on 17.05.18.
 */

import com.reklamar.testpandora.classes.song.SongResponse;
import com.reklamar.testpandora.classes.stations.StationResponse;
import com.reklamar.testpandora.classes.userauth.UserResponse;
import com.reklamar.testpandora.classes.partnerauth.PartnerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PandoraApi {

    //Partner Login
    @POST("/services/json/?method=auth.partnerLogin")
    Call<PartnerResponse> partnerLogin(@Body PartnerLoginRequest partnerLogin);

    //User Login
    @POST("/services/json/?method=auth.userLogin")
    Call<UserResponse> userLogin(@Query("partner_id")String partner_id, @Query("auth_token")String auth_token, @Body UserLoginRequest userLogin);

    //Get station List
    @POST("/services/json/?method=user.getStationList")
    Call<StationResponse> stationQuery(@Query("user_id")String user_id, @Query("partner_id")String partner_id, @Query("auth_token")String auth_token, @Body StationRequest stationRequest);

    //Get station List
    @POST("/services/json/?method=station.getPlaylist")
    Call<SongResponse> songPlaylistQuery(@Query("user_id")String user_id, @Query("partner_id")String partner_id, @Query("auth_token")String auth_token, @Body SongPlaylistRequest songPlaylistRequest);
}
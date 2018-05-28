package com.reklamar.testpandora;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.reklamar.testpandora.classes.api.Controller;
import com.reklamar.testpandora.classes.api.PandoraApi;
import com.reklamar.testpandora.classes.api.UserLoginRequest;
import com.reklamar.testpandora.classes.partnerauth.PartnerResponse;
import com.reklamar.testpandora.classes.api.PartnerLoginRequest;
import com.reklamar.testpandora.classes.partnerauth.Partner;
import com.reklamar.testpandora.classes.userauth.User;
import com.reklamar.testpandora.classes.userauth.UserResponse;
import com.reklamar.testpandora.classes.workclasses.RootApplication;
import com.reklamar.testpandora.classes.workclasses.SupportClass;
import com.singh.daman.proprogressviews.CircleLineProgress;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {

    private PandoraApi pandoraApi;

    @BindView(R.id.tool_bar) Toolbar tool_bar;
    @BindView(R.id.progress) CircleLineProgress progress;
    @BindView(R.id.loginET) EditText loginET;
    @BindView(R.id.parolET) EditText parolET;
    @BindView(R.id.button) Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        setSupportActionBar(tool_bar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        progressEnabled(false);
    }

    @OnClick(R.id.button)
    public void loginClick(Button button) {
        if(!SupportClass.checkStringNullAndTrim(loginET.getText().toString()).equals("") && !SupportClass.checkStringNullAndTrim(loginET.getText().toString()).equals("")){
            getPartner();
        }else{
            SupportClass.ToastMessage(SigninActivity.this, "Введите логин и пароль");
        }
    }

    private void getPartner(){
        progressEnabled(true);

        pandoraApi = Controller.getApiUnsecure();
        pandoraApi.partnerLogin(new PartnerLoginRequest()).enqueue(new Callback<PartnerResponse>() {
            @Override
            public void onResponse(@NonNull Call<PartnerResponse> call, @NonNull Response<PartnerResponse> response) {

                progressEnabled(false);
                if (response.code() == 200) {
                    if(response.body() != null && response.body().getStat() != null && response.body().getStat().equals("ok")){
                        Partner partner = response.body().getPartner();
                    RootApplication.getInstance().setPartner(partner);
                        getUser(partner.getPartnerAuthToken(), partner.getPartnerId(), partner.getSyncTime());
                    }else if(response.body().getCode().equals("12")){
                        SupportClass.ToastMessage(SigninActivity.this, "Сервис не доступен в Вашей стране, проверьте VPN");
                    }else if(response.body().getCode().equals("1002")){
                        SupportClass.ToastMessage(SigninActivity.this, "Проверьте логин и пароль");
                    }else{
                        SupportClass.ToastMessage(SigninActivity.this, "Что-то пошло не так=( " + response.body().getMessage() + " - Код: " + response.body().getCode());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PartnerResponse> call, @NonNull Throwable t) {
                progressEnabled(false);
                SupportClass.ToastMessage(SigninActivity.this,  t.toString());
            }
        });
    }

    private void getUser(String partnerAuthToken, String partnerId, String syncTime){

        int syncTimeU = getSyncTime(syncTime);

        String suthToken = "";
        try {
            suthToken = "auth_token=" + URLEncoder.encode(partnerAuthToken, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            SupportClass.ToastMessage(SigninActivity.this, "Что-то пошло не так=(");
            return;
        }

        UserLoginRequest userLogin = new UserLoginRequest("rmkonstantin@gmail.com", "SuperKostik1", partnerAuthToken, partnerId, syncTimeU);

        progressEnabled(true);

        pandoraApi = Controller.getApiSecure();
        pandoraApi.userLogin(partnerId, suthToken, userLogin).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                progressEnabled(false);
                if (response.code() == 200) {
                    if(response.body() != null && response.body().getStat() != null && response.body().getStat().equals("ok")){
                        User user = response.body().getUser();

                        RootApplication.getInstance().setUser(user);

                        startActivity(new Intent(SigninActivity.this, MainActivity.class));

                        SigninActivity.this.finish();

                    }else if(response.body().getCode().equals("12")){
                        SupportClass.ToastMessage(SigninActivity.this, "Сервис не доступен в Вашей стране, проверьте VPN");
                    }else{
                        SupportClass.ToastMessage(SigninActivity.this, "Что-то пошло не так=( " + response.body().getMessage() + " - Код: " + response.body().getCode());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                progressEnabled(false);
                SupportClass.ToastMessage(SigninActivity.this, t.toString());
            }
        });
    }

    private void progressEnabled(Boolean value){
        if (value) {
            loginET.setEnabled(false);
            parolET.setEnabled(false);
            button.setEnabled(false);
            progress.setVisibility(View.VISIBLE);
        }else{
            loginET.setEnabled(true);
            parolET.setEnabled(true);
            button.setEnabled(true);
            progress.setVisibility(View.GONE);
        }
    }

    private int getSyncTime(String syncTime){
        int pandoraTime = 0;
        try {
            pandoraTime = Integer.parseInt(SupportClass.decrypt(syncTime));
        } catch (NumberFormatException e1) {
            SupportClass.ToastMessage(SigninActivity.this, "Что-то пошло не так=(");
        } catch (Exception e1) {
            SupportClass.ToastMessage(SigninActivity.this, "Что-то пошло не так=(");
        }

        int timeOffset = pandoraTime - (int) (System.currentTimeMillis() / 1000L);

        ((RootApplication) this.getApplication()).setTimeOffset(timeOffset);

        return (int) (System.currentTimeMillis() / 1000L) + timeOffset;
    }
}
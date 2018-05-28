package com.reklamar.testpandora.classes.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.reklamar.testpandora.classes.workclasses.SupportClass;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Developer on 18.05.18.
 */

public class Controller {

    public static PandoraApi getApiUnsecure() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SupportClass.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(PandoraApi.class);
    }

    public static PandoraApi getApiSecure() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new EncryptionInterceptor()).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SupportClass.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(PandoraApi.class);
    }
}
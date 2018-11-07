package com.abisayuti.imaaplikasi.network;

import com.abisayuti.imaaplikasi.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstanceRetrodit {
    public static Retrofit setInit(){

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ApiService getInstance() {
        return setInit().create(ApiService.class);
    }
}

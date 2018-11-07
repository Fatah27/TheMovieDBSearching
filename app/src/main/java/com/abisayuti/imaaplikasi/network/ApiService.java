package com.abisayuti.imaaplikasi.network;

import com.abisayuti.imaaplikasi.model.ResponseSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/movie")
    Call<ResponseSearch> searchFilm(@Query("api_key") String apikey,
                                    @Query("language") String language,
                                    @Query("query") String query);

//    Call<ResponseSearch> searchFilm(String apiKey, String language, String query);
}

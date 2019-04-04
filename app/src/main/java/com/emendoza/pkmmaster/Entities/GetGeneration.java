package com.emendoza.pkmmaster.Entities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetGeneration {
    String API_GEN = "pokedex/";

    @GET(API_GEN)
    Call<eGeneration> getGeneration(@Url String url);
}

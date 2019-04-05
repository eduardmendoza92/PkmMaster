package com.emendoza.pkmmaster.Entities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetPokemon {
    @GET()
    Call<ePoke> getPokemon(@Url String url);
}

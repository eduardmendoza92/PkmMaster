package com.emendoza.pkmmaster.Entities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetRegions {
    @GET()
    Call<eRegion> getRegions(@Url String url);
}

package com.emendoza.pkmmaster.Entities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  PostService {
    String API_ROUTE = "region/";

    @GET(API_ROUTE)
    Call<APIResponse> getPost();
}

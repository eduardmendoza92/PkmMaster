package com.emendoza.pkmmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.emendoza.pkmmaster.Entities.NamedAPIResource;
import com.emendoza.pkmmaster.Entities.APIResponse;
import com.emendoza.pkmmaster.Entities.PostService;
import com.emendoza.pkmmaster.Entities.GetRegions;
import com.emendoza.pkmmaster.Entities.eRegion;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PkmRegionActivity extends AppCompatActivity {

    String baseUrl = "https://pokeapi.co/api/v2/";
    ArrayList<String> regiones = new ArrayList<>();
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkm_region);

        list = findViewById(R.id.listRegion);

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,regiones);

        list.setAdapter(arrayAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService postService = retrofit.create(PostService.class);
        Call<APIResponse> call = postService.getPost();

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse apiResponse = response.body();
                for (NamedAPIResource namedAPIResource : apiResponse.getResults()){
                    regiones.add(namedAPIResource.getName());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.d("RETROFIT",t.getMessage(),t);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                int idRegion = (position + 1);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                GetRegions getRegions = retrofit.create(GetRegions.class);
                Call<eRegion> call = getRegions.getRegions(baseUrl + "region/" + String.valueOf(idRegion));

                call.enqueue(new Callback<eRegion>() {
                    @Override
                    public void onResponse(Call<eRegion> call, Response<eRegion> response) {
                        eRegion region = response.body();
                        int totalPokedexes = region.getPokedexes().length;
                        int index = 1;
                        String pokedexName = "";
                        for (NamedAPIResource namedAPIResource : region.getPokedexes()){
                            if (totalPokedexes == index)
                            {
                                pokedexName = namedAPIResource.getName();
                                Intent intent = new Intent(PkmRegionActivity.this, StrategicActivity.class);
                                intent.putExtra("PokedexName", pokedexName);
                                startActivity(intent);
                            }
                            index++;
                        }

                    }

                    @Override
                    public void onFailure(Call<eRegion> call, Throwable t) {
                        Log.d("RETROFIT",t.getMessage(),t);
                    }
                });


            }
        });

    }
}

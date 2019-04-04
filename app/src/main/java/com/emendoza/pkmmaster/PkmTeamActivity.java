package com.emendoza.pkmmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.emendoza.pkmmaster.Entities.GetGeneration;
import com.emendoza.pkmmaster.Entities.NamedAPIResource;
import com.emendoza.pkmmaster.Entities.eGeneration;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PkmTeamActivity extends AppCompatActivity {

    String baseUrl = "https://pokeapi.co/api/v2/";
    ArrayList<String> pokemon_species = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkm_team);

        Spinner spinner = (Spinner) findViewById(R.id.pokemonList);
        final Integer regionId = getIntent().getExtras().getInt("regionId");
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, pokemon_species);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetGeneration gestService = retrofit.create(GetGeneration.class);
        Call<eGeneration> call = gestService.getGeneration(regionId.toString());

        call.enqueue(new Callback<eGeneration>() {
            @Override
            public void onResponse(Call<eGeneration> call, Response<eGeneration> response) {
                eGeneration apiResponse = response.body();
                for (NamedAPIResource namedAPIResource : apiResponse.getPokemon_species()){
                    pokemon_species.add(namedAPIResource.getName());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<eGeneration> call, Throwable t) {
                Log.d("RETROFIT",t.getMessage(),t);
            }
        });

    }
}

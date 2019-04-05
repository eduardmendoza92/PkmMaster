package com.emendoza.pkmmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.emendoza.pkmmaster.Entities.GetPokedex;
import com.emendoza.pkmmaster.Entities.NamedAPIResource;
import com.emendoza.pkmmaster.Entities.eGeneration;
import com.emendoza.pkmmaster.Entities.ePokedex;
import com.emendoza.pkmmaster.Entities.ePokemonEntry;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PkmTeamActivity extends AppCompatActivity {

    String baseUrl = "https://pokeapi.co/api/v2/";
    ArrayList<String> pokemon_species = new ArrayList<>();

    Spinner spinner = (Spinner) findViewById(R.id.pokemonList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkm_team);

        final String PokedexName = getIntent().getExtras().getString("PokedexName");
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, pokemon_species);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetPokedex getPokedex = retrofit.create(GetPokedex.class);
        Call<ePokedex> call = getPokedex.getPokedex("pokedex/" + PokedexName);

        call.enqueue(new Callback<ePokedex>() {
            @Override
            public void onResponse(Call<ePokedex> call, Response<ePokedex> response) {
                ePokedex apiResponse = response.body();
                for (ePokemonEntry entry : apiResponse.getPokemon_entries()){
                    pokemon_species.add(entry.getPokemon_species().getName());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ePokedex> call, Throwable t) {
                Log.d("RETROFIT",t.getMessage(),t);
            }
        });

    }

    public void onAgregarClick(View view){
        String text = spinner.getSelectedItem().toString();
    }
}

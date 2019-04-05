package com.emendoza.pkmmaster;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.emendoza.pkmmaster.Entities.GetPokedex;
import com.emendoza.pkmmaster.Entities.GetPokemon;
import com.emendoza.pkmmaster.Entities.NamedAPIResource;
import com.emendoza.pkmmaster.Entities.PokemonSprites;
import com.emendoza.pkmmaster.Entities.eGeneration;
import com.emendoza.pkmmaster.Entities.ePoke;
import com.emendoza.pkmmaster.Entities.ePokeTeam;
import com.emendoza.pkmmaster.Entities.ePokedex;
import com.emendoza.pkmmaster.Entities.ePokemon;
import com.emendoza.pkmmaster.Entities.ePokemonEntry;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PkmTeamActivity extends AppCompatActivity {

    private static String PokedexName = "";
    String baseUrl = "https://pokeapi.co/api/v2/";
    ArrayList<String> pokemon_species = new ArrayList<>();
    private ArrayList<ePoke> listaPokemon = new ArrayList<>();
    private Context mContext;
    private int index = 0;
    private RecyclerView recyclerView;
    private EditText editText;
    private listaPokemonAdapter _listaPokemonAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkm_team);

        editText = (EditText) findViewById(R.id.tvPokeTeamName);

        recyclerView = (RecyclerView) findViewById(R.id.gvPokeTeam);
        _listaPokemonAdapter = new listaPokemonAdapter(this);
        recyclerView.setAdapter(_listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        Spinner spinner = (Spinner) findViewById(R.id.pokemonList);
        PokedexName = getIntent().getExtras().getString("PokedexName");
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, pokemon_species);
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
                for (ePokemonEntry entry : apiResponse.getPokemon_entries()) {
                    pokemon_species.add(entry.getPokemon_species().getName());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ePokedex> call, Throwable t) {
                Log.d("RETROFIT", t.getMessage(), t);
            }
        });

    }


    public void onAgregarClick(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.pokemonList);
        String pokeName = spinner.getSelectedItem().toString();

        if (listaPokemon.size() == 6) {
            Snackbar.make(view, "El maximo de pokemon que puede agregar son 6", 5);
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            GetPokemon getPokemon = retrofit.create(GetPokemon.class);
            Call<ePoke> call = getPokemon.getPokemon("pokemon/" + pokeName);

            call.enqueue(new Callback<ePoke>() {
                @Override
                public void onResponse(Call<ePoke> call, Response<ePoke> response) {

                    ePoke apiResponse = response.body();
                    listaPokemon.add(index, apiResponse);

                    _listaPokemonAdapter.adicionarListaPokemon(listaPokemon);

                    index++;

                }

                @Override
                public void onFailure(Call<ePoke> call, Throwable t) {
                    Log.d("RETROFIT", t.getMessage(), t);
                }
            });
        }
    }

    public void onGuardarClick(View view) {
        if (listaPokemon.size() < 3) {
            Snackbar.make(view, "El minimo de pokemon que puede agregar son 3", Snackbar.LENGTH_LONG).show();
        } else {
            // Write a message to the database
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("PokeTeam");

            ePokeTeam pokeTeams = new ePokeTeam(1, editText.getText().toString(), listaPokemon, PokedexName);

            mDatabase.setValue(pokeTeams);
        }
    }
}

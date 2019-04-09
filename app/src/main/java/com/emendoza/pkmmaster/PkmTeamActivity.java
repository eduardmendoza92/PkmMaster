package com.emendoza.pkmmaster;

import android.content.Context;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.emendoza.pkmmaster.Entities.GetPokedex;
import com.emendoza.pkmmaster.Entities.GetPokemon;
import com.emendoza.pkmmaster.Entities.ePoke;
import com.emendoza.pkmmaster.Entities.ePokeTeam;
import com.emendoza.pkmmaster.Entities.ePokedex;
import com.emendoza.pkmmaster.Entities.ePokemon;
import com.emendoza.pkmmaster.Entities.ePokemonEntry;
import com.google.android.gms.auth.api.credentials.IdToken;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<ePokeTeam> listPokeTeam = new ArrayList<>();

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

        String android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        DatabaseReference refPokeTeam = FirebaseDatabase.getInstance().getReference(android_id);
        refPokeTeam.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                int index = 0;
                for(DataSnapshot dsp : dataSnapshot.getChildren())
                {
                    ePokeTeam value = dsp.getValue(ePokeTeam.class);
                    listPokeTeam.add(index, new ePokeTeam(value.getId(),value.getName(), value.getPokemon(), value.getRegion()));
                    index++;
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
            try {
                String android_id = Settings.Secure.getString(view.getContext().getContentResolver(),
                        Settings.Secure.ANDROID_ID);

                // Write a message to the database
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference(android_id.toString());

                ArrayList<ePokemon> pokemon = new ArrayList<ePokemon>();

                int index = 0;
                for(ePoke poke : listaPokemon){
                    pokemon.add(index, new ePokemon(poke.getId(), poke.getName()));
                    index++;
                }
                ePokeTeam setLista = new ePokeTeam();
                setLista.setId(listPokeTeam.size() + 1);
                setLista.setName(editText.getText().toString());
                setLista.setPokemon(pokemon);
                setLista.setRegion(PokedexName);
                listPokeTeam.add(listPokeTeam.size(), setLista);

                mDatabase.child("PokeTeam").setValue("");
                mDatabase.child("PokeTeam").setValue(listPokeTeam);

                onBackPressed();

            } catch (Exception e) {
                Log.d("Error database", e.getMessage());
            }

        }
    }
}

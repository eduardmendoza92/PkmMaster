package com.emendoza.pkmmaster;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.emendoza.pkmmaster.Entities.ePokeTeam;
import com.emendoza.pkmmaster.Entities.ePokemon;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StrategicActivity extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String PokedexName = getIntent().getExtras().getString("PokedexName");

        String android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(android_id);

        listView = (ListView) findViewById(R.id.listTeams);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ArrayList<String> teamsPokemon = new ArrayList<>();
                for(DataSnapshot dsp : dataSnapshot.getChildren())
                {
                    ePokeTeam value = dsp.getValue(ePokeTeam.class);
                    String nameRegion = value.getRegion();
                    if (nameRegion.equals(PokedexName))
                    {
                        teamsPokemon.add(value.getName());
                    }
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(StrategicActivity.this,android.R.layout.simple_list_item_1,teamsPokemon);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ArrayList<String> teamsPokemon = new ArrayList<>();
                for(DataSnapshot dsp : dataSnapshot.getChildren())
                {
                    ePokeTeam value = dsp.getValue(ePokeTeam.class);
                    String nameRegion = value.getRegion();
                    if (nameRegion.equals(PokedexName))
                    {
                        teamsPokemon.add(value.getName());
                    }
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(StrategicActivity.this,android.R.layout.simple_list_item_1,teamsPokemon);
                listView.setAdapter(arrayAdapter);
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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StrategicActivity.this, PkmTeamActivity.class);
                intent.putExtra("PokedexName", PokedexName);
                startActivity(intent);

            }
        });
    }

}

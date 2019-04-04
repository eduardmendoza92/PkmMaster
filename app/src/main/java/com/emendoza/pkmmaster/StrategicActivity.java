package com.emendoza.pkmmaster;

import android.content.Intent;
import android.os.Bundle;
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
    ArrayList<String> teamsPokemon = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Integer regionId = getIntent().getExtras().getInt("regionId");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("PokeTeam");

        listView = (ListView) findViewById(R.id.listTeams);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ePokeTeam value = dataSnapshot.getValue(ePokeTeam.class);
                teamsPokemon.add(value.getName());
                ArrayAdapter arrayAdapter = new ArrayAdapter(StrategicActivity.this,android.R.layout.simple_list_item_1,teamsPokemon);
                listView.setAdapter(arrayAdapter);
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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StrategicActivity.this, PkmTeamActivity.class);
                intent.putExtra("regionId", regionId);
                startActivity(intent);
                // Write a message to the database
                /*DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("PokeTeam");

                ArrayList<ePokeTeam> pokeTeams = new ArrayList<ePokeTeam>();
                ArrayList<ePokemon> pokeList = new ArrayList<ePokemon>();

                pokeList.add(0,new ePokemon(1,"bulbasaur"));
                pokeList.add(1,new ePokemon(4,"charmander"));

                ePokeTeam pokeTeam = new ePokeTeam(2, "Kanto Initials", pokeList);
                pokeList.clear();

                pokeTeams.add(0, pokeTeam);

                pokeList.add(0,new ePokemon(46,"paras"));
                pokeList.add(1,new ePokemon(4,"venomoth"));

                pokeTeam = new ePokeTeam(3, "Poison", pokeList);

                pokeTeams.add(1, pokeTeam);

                mDatabase.setValue(pokeTeams);*/
            }
        });
    }

}

package com.emendoza.pkmmaster;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.emendoza.pkmmaster.Entities.ePokeTeam;
import com.emendoza.pkmmaster.Entities.ePokemon;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StrategicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Write a message to the database
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("PokeTeam");

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

                mDatabase.setValue(pokeTeams);
            }
        });
    }

}

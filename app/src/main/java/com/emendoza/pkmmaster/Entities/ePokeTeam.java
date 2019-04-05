package com.emendoza.pkmmaster.Entities;

import java.util.ArrayList;
import java.util.List;

public class ePokeTeam {

    public ePokeTeam() {
    }

    private int id;
    private String name;
    private String region;
    private ArrayList<ePoke> pokemons;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ePoke> getPokemon() {
        return pokemons;
    }

    public void setPokemon(ArrayList<ePoke> pokemon) {
        this.pokemons = pokemon;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ePokeTeam(int id, String name, ArrayList<ePoke> pokemons, String region) {
        this.id = id;
        this.name = name;
        this.pokemons = pokemons;
        this.region = region;
    }
}

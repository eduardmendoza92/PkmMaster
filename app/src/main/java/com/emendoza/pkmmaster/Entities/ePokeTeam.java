package com.emendoza.pkmmaster.Entities;

import java.util.ArrayList;
import java.util.List;

public class ePokeTeam {

    private int id;
    private String name;
    private ArrayList<ePokemon> pokemon;

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

    public ArrayList<ePokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(ArrayList<ePokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public ePokeTeam(int id, String name, ArrayList<ePokemon> pokemon) {
        this.id = id;
        this.name = name;
        this.pokemon = pokemon;
    }
}

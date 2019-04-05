package com.emendoza.pkmmaster.Entities;

public class ePokemonEntry {
    private int entry_number;
    private NamedAPIResource pokemon_species;

    public int getEntry_number() {
        return entry_number;
    }

    public void setEntry_number(int entry_number) {
        this.entry_number = entry_number;
    }

    public NamedAPIResource getPokemon_species() {
        return pokemon_species;
    }

    public void setPokemon_species(NamedAPIResource pokemon_species) {
        this.pokemon_species = pokemon_species;
    }
}

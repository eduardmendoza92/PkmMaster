package com.emendoza.pkmmaster.Entities;

public class eGeneration {
    private int id;
    private String name;
    private NamedAPIResource[] abilities;
    private eName[] names;
    private NamedAPIResource main_region;
    private NamedAPIResource[] moves;
    private NamedAPIResource[] pokemon_species;
    private NamedAPIResource[] types;
    private NamedAPIResource[] version_groups;

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

    public NamedAPIResource[] getAbilities() {
        return abilities;
    }

    public void setAbilities(NamedAPIResource[] abilities) {
        this.abilities = abilities;
    }

    public eName[] getNames() {
        return names;
    }

    public void setNames(eName[] names) {
        this.names = names;
    }

    public NamedAPIResource getMain_region() {
        return main_region;
    }

    public void setMain_region(NamedAPIResource main_region) {
        this.main_region = main_region;
    }

    public NamedAPIResource[] getMoves() {
        return moves;
    }

    public void setMoves(NamedAPIResource[] moves) {
        this.moves = moves;
    }

    public NamedAPIResource[] getPokemon_species() {
        return pokemon_species;
    }

    public void setPokemon_species(NamedAPIResource[] pokemon_species) {
        this.pokemon_species = pokemon_species;
    }

    public NamedAPIResource[] getTypes() {
        return types;
    }

    public void setTypes(NamedAPIResource[] types) {
        this.types = types;
    }

    public NamedAPIResource[] getVersion_groups() {
        return version_groups;
    }

    public void setVersion_groups(NamedAPIResource[] version_groups) {
        this.version_groups = version_groups;
    }
}

package com.emendoza.pkmmaster.Entities;

public class ePokedex {
    private int id;
    private String name;
    private Boolean is_main_series;
    private eDescription[] descriptions;
    private eName[] names;
    private ePokemonEntry[] pokemon_entries;
    private NamedAPIResource region;
    private NamedAPIResource[]  version_groups;

    public ePokemonEntry[] getPokemon_entries() {
        return pokemon_entries;
    }

    public void setPokemon_entries(ePokemonEntry[] pokemon_entries) {
        this.pokemon_entries = pokemon_entries;
    }

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

    public Boolean getIs_main_series() {
        return is_main_series;
    }

    public void setIs_main_series(Boolean is_main_series) {
        this.is_main_series = is_main_series;
    }

    public eDescription[] getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(eDescription[] descriptions) {
        this.descriptions = descriptions;
    }

    public eName[] getNames() {
        return names;
    }

    public void setNames(eName[] names) {
        this.names = names;
    }

    public NamedAPIResource getRegion() {
        return region;
    }

    public void setRegion(NamedAPIResource region) {
        this.region = region;
    }

    public NamedAPIResource[] getVersion_groups() {
        return version_groups;
    }

    public void setVersion_groups(NamedAPIResource[] version_groups) {
        this.version_groups = version_groups;
    }
}

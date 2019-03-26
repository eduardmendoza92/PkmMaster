package com.emendoza.pkmmaster.Entities;

public class eRegion {
    private int id;
    private NamedAPIResource[] locations;
    private String name;
    private eName[] names;
    private NamedAPIResource main_generation;
    private NamedAPIResource[] pokedexes;
    private NamedAPIResource[] version_groups;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NamedAPIResource[] getLocations() {
        return locations;
    }

    public void setLocations(NamedAPIResource[] locations) {
        this.locations = locations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public eName[] getNames() {
        return names;
    }

    public void setNames(eName[] names) {
        this.names = names;
    }

    public NamedAPIResource getMain_generation() {
        return main_generation;
    }

    public void setMain_generation(NamedAPIResource main_generation) {
        this.main_generation = main_generation;
    }

    public NamedAPIResource[] getPokedexes() {
        return pokedexes;
    }

    public void setPokedexes(NamedAPIResource[] pokedexes) {
        this.pokedexes = pokedexes;
    }

    public NamedAPIResource[] getVersion_groups() {
        return version_groups;
    }

    public void setVersion_groups(NamedAPIResource[] version_groups) {
        this.version_groups = version_groups;
    }
}

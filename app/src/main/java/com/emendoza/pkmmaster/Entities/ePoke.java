package com.emendoza.pkmmaster.Entities;

public class ePoke {
    private int id;
    private String name;
    private int base_experience;
    private int height;
    private Boolean is_default;
    private int order;
    private int weight;
    private PokemonAbility[] abilities;
    private NamedAPIResource[] forms;
    private VersionGameIndex[] game_indices;
    private PokemonHeldItem[] held_items;
    private String location_area_encounters;
    private PokemonMove[] moves;
    private PokemonSprites[] sprites;
    private NamedAPIResource species;
    private PokemonStat[] stats;
    private PokemonType types;

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

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Boolean getIs_default() {
        return is_default;
    }

    public void setIs_default(Boolean is_default) {
        this.is_default = is_default;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public PokemonAbility[] getAbilities() {
        return abilities;
    }

    public void setAbilities(PokemonAbility[] abilities) {
        this.abilities = abilities;
    }

    public NamedAPIResource[] getForms() {
        return forms;
    }

    public void setForms(NamedAPIResource[] forms) {
        this.forms = forms;
    }

    public VersionGameIndex[] getGame_indices() {
        return game_indices;
    }

    public void setGame_indices(VersionGameIndex[] game_indices) {
        this.game_indices = game_indices;
    }

    public PokemonHeldItem[] getHeld_items() {
        return held_items;
    }

    public void setHeld_items(PokemonHeldItem[] held_items) {
        this.held_items = held_items;
    }

    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    public void setLocation_area_encounters(String location_area_encounters) {
        this.location_area_encounters = location_area_encounters;
    }

    public PokemonMove[] getMoves() {
        return moves;
    }

    public void setMoves(PokemonMove[] moves) {
        this.moves = moves;
    }

    public PokemonSprites[] getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSprites[] sprites) {
        this.sprites = sprites;
    }

    public NamedAPIResource getSpecies() {
        return species;
    }

    public void setSpecies(NamedAPIResource species) {
        this.species = species;
    }

    public PokemonStat[] getStats() {
        return stats;
    }

    public void setStats(PokemonStat[] stats) {
        this.stats = stats;
    }

    public PokemonType getTypes() {
        return types;
    }

    public void setTypes(PokemonType types) {
        this.types = types;
    }
}

class PokemonAbility {
    boolean is_hidden;
    int slot;
    NamedAPIResource ability;
}

class VersionGameIndex {
    int game_index;
    NamedAPIResource version;
}

class PokemonHeldItem {
    NamedAPIResource item;
    PokemonHeldItemVersion version_details;
}

class PokemonHeldItemVersion {
    NamedAPIResource version;
    int rarity;
}

class PokemonMove {
    NamedAPIResource move;
    PokemonMoveVersion version_group_detail;
}

class PokemonMoveVersion {
    NamedAPIResource move_learn_method;
    NamedAPIResource version_group;
    int level_learned_at;
}

class PokemonSprites {
    private String front_default;
    String front_shiny;
    String front_female;
    String front_shiny_female;
    String back_default;
    String back_shiny;
    String back_female;
    String back_shiny_female;

    public String getFront_default() {
        return front_default;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }
}

class PokemonStat {
    NamedAPIResource stat;
    int effort;
    int base_stat;
}

class PokemonType {
    int slot;
    NamedAPIResource type;
}
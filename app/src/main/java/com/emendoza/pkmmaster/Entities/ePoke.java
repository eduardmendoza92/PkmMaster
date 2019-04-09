package com.emendoza.pkmmaster.Entities;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;


public @IgnoreExtraProperties class ePoke {
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
    private PokemonSprites sprites;
    private NamedAPIResource species;
    private PokemonStat[] stats;
    private PokemonType[] types;

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

    @Exclude public int getBase_experience() {
        return base_experience;
    }

    @Exclude public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    @Exclude public int getHeight() {
        return height;
    }

    @Exclude public void setHeight(int height) {
        this.height = height;
    }

    @Exclude public Boolean getIs_default() {
        return is_default;
    }

    @Exclude public void setIs_default(Boolean is_default) {
        this.is_default = is_default;
    }

    @Exclude public int getOrder() {
        return order;
    }

    @Exclude public void setOrder(int order) {
        this.order = order;
    }

    @Exclude public int getWeight() {
        return weight;
    }

    @Exclude public void setWeight(int weight) {
        this.weight = weight;
    }

    @Exclude public PokemonAbility[] getAbilities() {
        return abilities;
    }

    @Exclude public void setAbilities(PokemonAbility[] abilities) {
        this.abilities = abilities;
    }

    @Exclude public NamedAPIResource[] getForms() {
        return forms;
    }

    @Exclude public void setForms(NamedAPIResource[] forms) {
        this.forms = forms;
    }

    @Exclude public VersionGameIndex[] getGame_indices() {
        return game_indices;
    }

    @Exclude public void setGame_indices(VersionGameIndex[] game_indices) {
        this.game_indices = game_indices;
    }

    @Exclude public PokemonHeldItem[] getHeld_items() {
        return held_items;
    }

    @Exclude public void setHeld_items(PokemonHeldItem[] held_items) {
        this.held_items = held_items;
    }

    @Exclude public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    @Exclude public void setLocation_area_encounters(String location_area_encounters) {
        this.location_area_encounters = location_area_encounters;
    }

    @Exclude public PokemonMove[] getMoves() {
        return moves;
    }

    @Exclude public void setMoves(PokemonMove[] moves) {
        this.moves = moves;
    }

    @Exclude public PokemonSprites getSprites() {
        return sprites;
    }

    @Exclude public void setSprites(PokemonSprites sprites) {
        this.sprites = sprites;
    }

    @Exclude public NamedAPIResource getSpecies() {
        return species;
    }

    @Exclude public void setSpecies(NamedAPIResource species) {
        this.species = species;
    }

    @Exclude public PokemonStat[] getStats() {
        return stats;
    }

    @Exclude public void setStats(PokemonStat[] stats) {
        this.stats = stats;
    }

    @Exclude public PokemonType[] getTypes() {
        return types;
    }

    @Exclude public void setTypes(PokemonType[] types) {
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
    PokemonHeldItemVersion[] version_details;
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



class PokemonStat {
    NamedAPIResource stat;
    int effort;
    int base_stat;
}

class PokemonType {
    int slot;
    NamedAPIResource type;
}
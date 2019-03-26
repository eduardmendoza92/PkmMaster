package com.emendoza.pkmmaster.Entities;

public class ePokemon {
    public int id;
    public String name;

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

    public ePokemon(int id, String name) {
        this.id = id;
        this.name = name;
    }

}

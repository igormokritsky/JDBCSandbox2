package org.example.entity;

public class Sponsor extends Entity {

    private String name;

    public Sponsor() {

    }

    public Sponsor(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

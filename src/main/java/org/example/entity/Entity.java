package org.example.entity;

import java.io.Serializable;

public class Entity implements Serializable {

    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package org.example.entity;

public class Coach {
    private Integer id;
    private String name;
    private String awards;
    private Integer country_id;
    private Integer user_id;

    public Coach() {

    }

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Coach(Integer id, String name, String awards, Integer country_id, Integer user_id) {
        this.id = id;
        this.name = name;
        this.awards = awards;
        this.country_id = country_id;
        this.user_id = user_id;
    }

    public Coach(Integer id, String name, String awards) {
        this.id = id;
        this.name = name;
        this.awards = awards;
    }


    public Coach(String name, String awards) {
        this.name = name;
        this.awards = awards;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }
}

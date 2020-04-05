package org.example.entity;

public class Country {
    private Integer id;
    private String country_name;

    public Country() {

    }

    public Country(Integer id, String country_name) {
        this.id = id;
        this.country_name = country_name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}

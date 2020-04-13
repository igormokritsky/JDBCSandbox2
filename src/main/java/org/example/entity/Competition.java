package org.example.entity;

public class Competition extends Entity {

    private Integer country_id;
    private Integer style_id;
    private Integer distance;

    public Competition() {

    }

    public Competition(Integer id, Integer country_id, Integer style_id, Integer distance) {
        this.id = id;
        this.country_id = country_id;
        this.style_id = style_id;
        this.distance = distance;
    }



    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public Integer getStyle_id() {
        return style_id;
    }

    public void setStyle_id(Integer style_id) {
        this.style_id = style_id;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}

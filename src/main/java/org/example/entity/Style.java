package org.example.entity;

public class Style {

    private Integer id;
    private String style_name;

    public Style() {

    }

    public Style(Integer id, String style_name) {
        this.id = id;
        this.style_name = style_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStyle_name() {
        return style_name;
    }

    public void setStyle_name(String style_name) {
        this.style_name = style_name;
    }
}

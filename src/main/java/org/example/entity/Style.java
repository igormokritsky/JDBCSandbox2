package org.example.entity;

public class Style extends Entity{

    private String style_name;

    public Style() {

    }

    public Style(Integer id, String style_name) {
        this.id = id;
        this.style_name = style_name;
    }



    public String getStyle_name() {
        return style_name;
    }

    public void setStyle_name(String style_name) {
        this.style_name = style_name;
    }
}

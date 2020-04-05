package org.example.entity;

public class User {
    private Integer id;
    private String username;
    private String email;
    private Integer phone;
    private String password;

    public User() {

    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }


    public User(Integer id, String username, String email, Integer phone, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public User(String username, String email, Integer phone, String password) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

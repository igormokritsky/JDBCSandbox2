package org.example.swimmerdao;

public class Swimmer {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private Integer weight;
    private Integer height;
    private Integer user_id;
    private Integer country_id;
    private Integer coach_id;

    public Swimmer(){

    }

    public Swimmer(String name, Integer age, String sex, Integer weight,
                   Integer height, Integer user_id, Integer country_id, Integer coach_id) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.user_id = user_id;
        this.country_id = country_id;
        this.coach_id = coach_id;
    }

    public Swimmer(Integer id, String name, Integer age, String sex,
                   Integer weight, Integer height, Integer user_id, Integer country_id, Integer coach_id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.user_id = user_id;
        this.country_id = country_id;
        this.coach_id = coach_id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public Integer getCoach_id() {
        return coach_id;
    }

    public void setCoach_id(Integer coach_id) {
        this.coach_id = coach_id;
    }
}

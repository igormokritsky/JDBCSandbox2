package org.example.entity;

public class SwimCompet extends Entity {

    private Integer competition_id;
    private Integer swimmer_id;
    private Integer time;

    public SwimCompet() {

    }

    public SwimCompet(Integer id, Integer competition_id, Integer swimmer_id, Integer time) {
        this.id = id;
        this.competition_id = competition_id;
        this.swimmer_id = swimmer_id;
        this.time = time;
    }




    public Integer getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(Integer competition_id) {
        this.competition_id = competition_id;
    }

    public Integer getSwimmer_id() {
        return swimmer_id;
    }

    public void setSwimmer_id(Integer swimmer_id) {
        this.swimmer_id = swimmer_id;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}

package org.example.entity;

public class SwimmersSponsor extends Entity {

    private Integer swimmer_id;
    private Integer sponsor_id;
    private Integer contract_amount;

    public SwimmersSponsor(){

    }

    public SwimmersSponsor(Integer id, Integer swimmer_id, Integer sponsor_id, Integer contract_amount) {
        this.id = id;
        this.swimmer_id = swimmer_id;
        this.sponsor_id = sponsor_id;
        this.contract_amount = contract_amount;
    }


    public Integer getSwimmer_id() {
        return swimmer_id;
    }

    public void setSwimmer_id(Integer swimmer_id) {
        this.swimmer_id = swimmer_id;
    }

    public Integer getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(Integer sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public Integer getContract_amount() {
        return contract_amount;
    }

    public void setContract_amount(Integer contract_amount) {
        this.contract_amount = contract_amount;
    }
}

package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

public class Egg_Production {
    private Integer id;
    private Integer egg_breeder_inv_id;
    private String date;
    private Integer intact;
    private Float weight;
    private Integer broken;
    private Integer rejects;
    private String remarks;
    private String deleted_at;

    Egg_Production(){

    }
    Egg_Production(Integer id, Integer egg_breeder_inv_id, String date, Integer intact, Float weight, Integer broken, Integer rejects, String remarks, String deleted_at){
        this.setId(id);
        this.setEgg_breeder_inv_id(egg_breeder_inv_id);
        this.setDate(date);
        this.setIntact(intact);
        this.setWeight(weight);
        this.setBroken(broken);
        this.setRejects(rejects);
        this.setRemarks(remarks);
        this.setDeleted_at(deleted_at);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEgg_breeder_inv_id() {
        return egg_breeder_inv_id;
    }

    public void setEgg_breeder_inv_id(Integer egg_breeder_inv_id) {
        this.egg_breeder_inv_id = egg_breeder_inv_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getIntact() {
        return intact;
    }

    public void setIntact(Integer intact) {
        this.intact = intact;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getBroken() {
        return broken;
    }

    public void setBroken(Integer broken) {
        this.broken = broken;
    }

    public Integer getRejects() {
        return rejects;
    }

    public void setRejects(Integer rejects) {
        this.rejects = rejects;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }
}


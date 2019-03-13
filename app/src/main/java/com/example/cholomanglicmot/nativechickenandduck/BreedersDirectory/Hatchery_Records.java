package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

public class Hatchery_Records {
    private Integer id;
    private Integer breeder_inv_id;
    private String date;
    private String batching_date;
    private Integer eggs_set;
    private Integer week_lay;
    private Integer fertile;
    private Integer hatched;
    private String date_hatched;
    private String deleted_at;

    Hatchery_Records(){

    }
    Hatchery_Records( Integer id, Integer breeder_inv_id,String date,String batching_date,Integer eggs_set, Integer week_lay, Integer fertile,  Integer hatched, String date_hatched, String deleted_at){
        this.setId(id);
        this.setBreeder_inv_id(breeder_inv_id);
        this.setDate(date);
        this.setBatching_date(batching_date);
        this.setEggs_set(eggs_set);
        this.setWeek_lay(week_lay);
        this.setFertile(fertile);
        this.setHatched(hatched);
        this.setDate_hatched(date_hatched);
        this.setDeleted_at(deleted_at);
    }


    public String getBatching_date() {
        return batching_date;
    }

    public void setBatching_date(String batching_date) {
        this.batching_date = batching_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBreeder_inv_id() {
        return breeder_inv_id;
    }

    public void setBreeder_inv_id(Integer breeder_inv_id) {
        this.breeder_inv_id = breeder_inv_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getEggs_set() {
        return eggs_set;
    }

    public void setEggs_set(Integer eggs_set) {
        this.eggs_set = eggs_set;
    }

    public Integer getWeek_lay() {
        return week_lay;
    }

    public void setWeek_lay(Integer week_lay) {
        this.week_lay = week_lay;
    }

    public Integer getFertile() {
        return fertile;
    }

    public void setFertile(Integer fertile) {
        this.fertile = fertile;
    }

    public Integer getHatched() {
        return hatched;
    }

    public void setHatched(Integer hatched) {
        this.hatched = hatched;
    }

    public String getDate_hatched() {
        return date_hatched;
    }

    public void setDate_hatched(String date_hatched) {
        this.date_hatched = date_hatched;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }
}

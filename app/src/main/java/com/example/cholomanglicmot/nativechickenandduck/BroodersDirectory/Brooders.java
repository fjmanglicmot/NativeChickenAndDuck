package com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory;

public class Brooders {
    private Integer id;
    private String brooder_generation_number;
    private String brooder_line_number;
    private String brooder_family_number;
    private String brooder_date_added;
    private String brooder_deleted_at;


    public Brooders(){

    }

    public Brooders(Integer id, String brooder_family_number, String brooder_line_number, String brooder_generation_number, String brooder_date_added, String brooder_deleted_at){
        this.setId(id);
        this.setBrooder_family_number(brooder_family_number);
        this.setBrooder_line_number(brooder_line_number);
        this.setBrooder_generation_number(brooder_generation_number);
        this.setBrooder_date_added(brooder_date_added);
        this.setBrooder_deleted_at(brooder_deleted_at);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrooder_generation_number() {
        return brooder_generation_number;
    }

    public void setBrooder_generation_number(String brooder_generation_number) {
        this.brooder_generation_number = brooder_generation_number;
    }

    public String getBrooder_line_number() {
        return brooder_line_number;
    }

    public void setBrooder_line_number(String brooder_line_number) {
        this.brooder_line_number = brooder_line_number;
    }

    public String getBrooder_family_number() {
        return brooder_family_number;
    }

    public void setBrooder_family_number(String brooder_family_number) {
        this.brooder_family_number = brooder_family_number;
    }

    public String getBrooder_date_added() {
        return brooder_date_added;
    }

    public void setBrooder_date_added(String brooder_date_added) {
        this.brooder_date_added = brooder_date_added;
    }

    public String getBrooder_deleted_at() {
        return brooder_deleted_at;
    }

    public void setBrooder_deleted_at(String brooder_deleted_at) {
        this.brooder_deleted_at = brooder_deleted_at;
    }

}

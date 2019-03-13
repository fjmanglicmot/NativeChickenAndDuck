package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

public class Breeders {
    private Integer id;
    private String family_number;
    private String female_family_number;
    private String line_number;
    private String generation_number;
    private String date_added;
    private String deleted_at;


    public Breeders(){
    }

    public Breeders(Integer id, String family_number, String female_family_number,String line_number, String generation_number, String date_added, String deleted_at) {
        this.setId(id);
        this.setFamily_number(family_number);
        this.setFemale_family_number(female_family_number);
        this.setLine_number(line_number);
        this.setGeneration_number(generation_number);
        this.setDate_added(date_added);
        this.setDeleted_at(deleted_at);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFemale_family_number() {
        return female_family_number;
    }

    public void setFemale_family_number(String female_family_number) {
        this.female_family_number = female_family_number;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getFamily_number() {
        return family_number;
    }

    public void setFamily_number(String family_number) {
        this.family_number = family_number;
    }

    public String getLine_number() {
        return line_number;
    }

    public void setLine_number(String line_number) {
        this.line_number = line_number;
    }

    public String getGeneration_number() {
        return generation_number;
    }

    public void setGeneration_number(String generation_number) {
        this.generation_number = generation_number;
    }
}

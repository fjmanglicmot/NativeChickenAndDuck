package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;
/*                              inv_id, pheno_date, pheno_sex, pheno_tag, pheno_record, morphos);*/
public class Breeder_PhenoMorphoView {
    private Integer id;
    private Integer inv_id;
    private String date;
    private String gender;
    private String tag;

    private String pheno_record;
    private String morpho_record;


    Breeder_PhenoMorphoView(){


    }

    Breeder_PhenoMorphoView(Integer id,
                            Integer inv_id,
                            String date,
                            String gender,
                            String tag,

                            String pheno_record,
                            String morpho_record
           ){

        this.setId(id);
        this.setGender(gender);
        this.setTag(tag);
        this.setPheno_record(pheno_record);
        this.setMorpho_record(morpho_record);
        this.setDate(date);


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getInv_id() {
        return inv_id;
    }

    public void setInv_id(Integer inv_id) {
        this.inv_id = inv_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPheno_record() {
        return pheno_record;
    }

    public void setPheno_record(String pheno_record) {
        this.pheno_record = pheno_record;
    }

    public String getMorpho_record() {
        return morpho_record;
    }

    public void setMorpho_record(String morpho_record) {
        this.morpho_record = morpho_record;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

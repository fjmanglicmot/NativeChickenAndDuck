package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

public class Mortality_Sales {
    private Integer id;
    private String date;
    private String tag;
    private Integer breeder_id;
    private Integer replaement_id;
    private Integer brooder_id;
    private String type;
    private String category;
    private Float price;
    private Integer male_count;
    private Integer female_count;
    private Integer total;
    private String reason;
    private String remarks;
    private String deleted_at;

    Mortality_Sales(){

    }

    Mortality_Sales(Integer id, String date, String tag,Integer breeder_id, Integer replaement_id, Integer brooder_id, String type, String category, Float price, Integer male_count, Integer female_count, Integer total, String reason, String remarks, String deleted_at){
        this.setId(id);
        this.setDate(date);
        this.setTag(tag);
        this.setBreeder_id(breeder_id);
        this.setReplaement_id(replaement_id);
        this.setBrooder_id(brooder_id);
        this.setType(type);
        this.setCategory(category);
        this.setPrice(price);
        this.setMale_count(male_count);
        this.setFemale_count(female_count);
        this.setTotal(total);
        this.setReason(reason);
        this.setRemarks(remarks);
        this.setDeleted_at(deleted_at);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getBreeder_id() {
        return breeder_id;
    }

    public void setBreeder_id(Integer breeder_id) {
        this.breeder_id = breeder_id;
    }

    public Integer getReplaement_id() {
        return replaement_id;
    }

    public void setReplaement_id(Integer replaement_id) {
        this.replaement_id = replaement_id;
    }

    public Integer getBrooder_id() {
        return brooder_id;
    }

    public void setBrooder_id(Integer brooder_id) {
        this.brooder_id = brooder_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getMale_count() {
        return male_count;
    }

    public void setMale_count(Integer male_count) {
        this.male_count = male_count;
    }

    public Integer getFemale_count() {
        return female_count;
    }

    public void setFemale_count(Integer female_count) {
        this.female_count = female_count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

public class Egg_Quality {
    private Integer id;
    private Integer egg_breeder_inv_id;
    private Integer week;
    private String date;
    private Float weight;
    private String color;
    private String shape;
    private Float length;
    private Float width;
    private Float albument_height;
    private Float albument_weight;
    private Float yolk_weight;
    private String yolk_color;
    private Float shell_weight;
    private Float shell_thickness_top;
    private Float shell_thickness_middle;
    private Float shell_thickness_bottom;

    Egg_Quality(){

    }

    Egg_Quality(   Integer id,Integer egg_breeder_inv_id,
                   String date,
                   Integer week,
                   Float weight,
             String color,
             String shape,
                   Float length,
                   Float width,
                   Float albument_height,
                   Float albument_weight,
                   Float yolk_weight,
             String yolk_color,
                   Float shell_weight,
                   Float shell_thickness_top,
                   Float shell_thickness_middle,
                   Float shell_thickness_bottom
    ){
        this.setId(id);
        this.setEgg_breeder_inv_id(egg_breeder_inv_id);
        this.setDate(date);
        this.setWeek(week);
        this.setWeight(weight);
        this.setColor(color);
        this.setShape(shape);
        this.setLength(length);
        this.setWidth(width);
        this.setAlbument_height(albument_height);
        this.setAlbument_weight(albument_weight);
        this.setYolk_weight(yolk_weight);
        this.setYolk_color(yolk_color);
        this.setShell_weight(shell_weight);
        this.setShell_thickness_top(shell_thickness_top);
        this.setShell_thickness_middle(shell_thickness_middle);
        this.setShell_thickness_bottom(shell_thickness_bottom);
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

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getAlbument_height() {
        return albument_height;
    }

    public void setAlbument_height(Float albument_height) {
        this.albument_height = albument_height;
    }

    public Float getAlbument_weight() {
        return albument_weight;
    }

    public void setAlbument_weight(Float albument_weight) {
        this.albument_weight = albument_weight;
    }

    public Float getYolk_weight() {
        return yolk_weight;
    }

    public void setYolk_weight(Float yolk_weight) {
        this.yolk_weight = yolk_weight;
    }

    public String getYolk_color() {
        return yolk_color;
    }

    public void setYolk_color(String yolk_color) {
        this.yolk_color = yolk_color;
    }

    public Float getShell_weight() {
        return shell_weight;
    }

    public void setShell_weight(Float shell_weight) {
        this.shell_weight = shell_weight;
    }

    public Float getShell_thickness_top() {
        return shell_thickness_top;
    }

    public void setShell_thickness_top(Float shell_thickness_top) {
        this.shell_thickness_top = shell_thickness_top;
    }

    public Float getShell_thickness_middle() {
        return shell_thickness_middle;
    }

    public void setShell_thickness_middle(Float shell_thickness_middle) {
        this.shell_thickness_middle = shell_thickness_middle;
    }

    public Float getShell_thickness_bottom() {
        return shell_thickness_bottom;
    }

    public void setShell_thickness_bottom(Float shell_thickness_bottom) {
        this.shell_thickness_bottom = shell_thickness_bottom;
    }
}

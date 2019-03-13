package com.example.cholomanglicmot.nativechickenandduck.PensDirectory;

public class Pen {
    public String pen_number;
    public String pen_type;
    public Integer pen_inventory;
    public Integer pen_capacity;

    public Pen (String pen_number,String pen_type,Integer pen_inventory,Integer pen_capacity){
        this.setPen_number(pen_number);
        this.setPen_type(pen_type);
        this.setPen_inventory(pen_inventory);
        this.setPen_capacity(pen_capacity);
    }



    public String getPen_number() {
        return pen_number;
    }

    public void setPen_number(String pen_number) {
        this.pen_number = pen_number;
    }

    public String getPen_type() {
        return pen_type;
    }

    public void setPen_type(String pen_type) {
        this.pen_type = pen_type;
    }

    public Integer getPen_capacity() {
        return pen_capacity;
    }

    public void setPen_capacity(Integer pen_capacity) {
        this.pen_capacity = pen_capacity;
    }

    public Integer getPen_inventory() {
        return pen_inventory;
    }

    public void setPen_inventory(Integer pen_inventory) {
        this.pen_inventory = pen_inventory;
    }
}

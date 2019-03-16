package com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory;
/*    public static final String GENERATION_COL_0 = "ID";
public static final String GENERATION_COL_1 = "farm_id";
public static final String GENERATION_COL_2 = "GENERATION_NUMBER";
public static final String GENERATION_COL_3 = "numerical_generation";
public static final String GENERATION_COL_4 = "GENERATION_STATUS";
public static final String GENERATION_COL_5 = "GENERATION_CULL";*/
public class Generation {
    private String generation_number;
    private String generation_status;


    public Generation(){

    }

    public Generation(String generation_number, String generation_status) {
        this.setGeneration_number(generation_number);
        this.setGeneration_status(generation_status);
    }

    public String getGeneration_number() {
        return generation_number;
    }

    public void setGeneration_number(String generation_number) {
        this.generation_number = generation_number;
    }

    public String getGeneration_status() {
        return generation_status;
    }

    public void setGeneration_status(String generation_status) {
        this.generation_status = generation_status;
    }
}



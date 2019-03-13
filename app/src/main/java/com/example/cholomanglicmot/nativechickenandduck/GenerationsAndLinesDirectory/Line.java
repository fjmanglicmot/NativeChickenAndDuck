package com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory;

public class Line {
    private String line_number;
    private String generation_number;
    public Line(){

    }
    public Line(String line_number,String generation_number) {
        this.setGeneration_number(generation_number);
        this.setLine_number(line_number);
    }

    public String getGeneration_number() {
        return generation_number;
    }

    public void setGeneration_number(String generation_number) {
        this.generation_number = generation_number;
    }
    public String getLine_number() {
        return line_number;
    }

    public void setLine_number(String line_number) {
        this.line_number = line_number;
    }

}



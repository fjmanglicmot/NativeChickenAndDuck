package com.example.cholomanglicmot.nativechickenandduck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DataProvider {

    public static LinkedHashMap<String, List<String>> getInfo(){
        LinkedHashMap<String, List<String>> ProjectDetails = new LinkedHashMap<>();
        List<String> Dashboard = new ArrayList<String>();
        //no child

        List<String> Create_Pens = new ArrayList<String>();
        //no child

        List<String> Breeder = new ArrayList<String>();
        Breeder.add("Generation");
        Breeder.add("Family Records");
        Breeder.add("Daily Records");
        Breeder.add("Hatchery Records");
        Breeder.add("Egg Quality Records");

        List<String> Replacement = new ArrayList<String>();
        Replacement.add("Add Replacement Stocks");
        Replacement.add("Phenotypic and Morphometric");
        Replacement.add("Feeding Record");

        List<String> Brooders = new ArrayList<String>();
        Brooders.add("Growth Performance");
        Brooders.add("Feeding Records");

        List<String> Mortality_Culling_and_Sales = new ArrayList<String>();
        //no child

        List<String> Reports = new ArrayList<String>();

        List<String> Farm_Settings = new ArrayList<String>();


        ProjectDetails.put("Dashboard", Dashboard);
        ProjectDetails.put("Create Pens", Create_Pens);
        ProjectDetails.put("Breeder", Breeder);
        ProjectDetails.put("Replacement", Replacement);
        ProjectDetails.put("Brooders", Brooders);
        ProjectDetails.put("Mortality, Culling, and Sales",Mortality_Culling_and_Sales);
        ProjectDetails.put("Reports", Reports);
        ProjectDetails.put("Farm Settings", Farm_Settings);



        return ProjectDetails;

    }

}

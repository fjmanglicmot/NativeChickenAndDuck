package com.example.cholomanglicmot.nativechickenandduck;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;


public class ReplacementPhenotypicMorphometricAddPheno extends AppCompatActivity {

    private Toolbar toolbar;
    private Button pheno_next;
    private Spinner spinner_plumage_color,spinner_plumage_pattern;
    private SpinnerAdapter spinnerAdapter,spinnerAdapter2;
    private String[] plumage_color = {"White", "Orange", "Black", "Brown", "Red", "Yellow"};
    private String[] plumage_pattern = {"Plain", "Wild Type", "Molted", "Barred", "Laced"};
    private int[] plumage_pattern_images = {R.drawable.plumage_pattern_plain,R.drawable.plumage_pattern_wildtype,R.drawable.plumage_pattern_mottled, R.drawable.plumage_pattern_barred,R.drawable.plumage_pattern_laced };
    private int[] plumage_color_images = {R.drawable.plumage_white,R.drawable.plumage_orange,R.drawable.plumage_black,R.drawable.plumage_brown,R.drawable.plumage_red,R.drawable.plumage_yellow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_phenotypic_morphometric_add_pheno);

        toolbar = findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //ito yung backbutton
        getSupportActionBar().setTitle("Replacement");

        spinner_plumage_color = (Spinner) findViewById(R.id.spinner_plumage_color);
        spinner_plumage_pattern = findViewById(R.id.spinner_plumage_pattern);
        spinnerAdapter = new SpinnerAdapter(this,plumage_color, plumage_color_images);
        spinnerAdapter2 = new SpinnerAdapter(this,plumage_pattern, plumage_pattern_images);
        spinner_plumage_pattern.setAdapter(spinnerAdapter2);
        spinner_plumage_color.setAdapter(spinnerAdapter);

        pheno_next = findViewById(R.id.pheno_next);
        pheno_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_replacement_phenomorpho_next = new Intent(ReplacementPhenotypicMorphometricAddPheno.this, ReplacementPhenotypicMorphometricAddPheno2.class);
                startActivity(intent_replacement_phenomorpho_next);
            }
        });






    }

}

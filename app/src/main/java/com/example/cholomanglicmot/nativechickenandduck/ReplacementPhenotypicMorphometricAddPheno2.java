package com.example.cholomanglicmot.nativechickenandduck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class ReplacementPhenotypicMorphometricAddPheno2 extends AppCompatActivity {
    private Toolbar toolbar;
    private Button pheno_next2;
    private Spinner spinner_hackle_color,spinner_hackle_pattern,spinner_body_carriage;
    private SpinnerAdapter spinnerAdapter,spinnerAdapter2, spinnerAdapter3;
    private String[] hackle_color = {"Yellow", "Brown", "Black", "Orange", "Red"};
    private String[] hackle_pattern = {"Plain", "Barred", "Laced"};
    private String[] body_carriage = {"Upright", "Slight Upright"};
    private int[] hackle_color_images = {R.drawable.hackle_yellow,R.drawable.hackle_brown,R.drawable.hackle_black, R.drawable.hackle_orange,R.drawable.hackle_red };
    private int[] hackle_pattern_images = {R.drawable.plumage_pattern_plain,R.drawable.plumage_pattern_barred,R.drawable.plumage_pattern_laced};
    private int[] body_carriage_images = {R.drawable.body_carriage_upright, R.drawable.body_carriage__slightly_upright};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_phenotypic_morphometric_add_pheno2);
        toolbar = findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //ito yung backbutton
        getSupportActionBar().setTitle("Replacement");

        spinner_hackle_color = findViewById(R.id.spinner_hackle_color);
        spinner_hackle_pattern = findViewById(R.id.spinner_hackle_pattern);
        spinner_body_carriage = findViewById(R.id.spinner_body_carriage);
        spinnerAdapter = new SpinnerAdapter(this,hackle_color, hackle_color_images);
        spinnerAdapter2 = new SpinnerAdapter(this,hackle_pattern, hackle_pattern_images);
        spinnerAdapter3 = new SpinnerAdapter(this, body_carriage, body_carriage_images);
        spinner_body_carriage.setAdapter(spinnerAdapter3);
        spinner_hackle_pattern.setAdapter(spinnerAdapter2);
        spinner_hackle_color.setAdapter(spinnerAdapter);

        pheno_next2 = findViewById(R.id.pheno_next2);
        pheno_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_replacement_phenomorpho_next = new Intent(ReplacementPhenotypicMorphometricAddPheno2.this, ReplacementPhenotypicMorphometricAddPheno3.class);
                startActivity(intent_replacement_phenomorpho_next);
            }
        });
    }
}

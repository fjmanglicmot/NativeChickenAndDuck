package com.example.cholomanglicmot.nativechickenandduck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;


public class ReplacementPhenotypicMorphometricAddPheno3 extends AppCompatActivity {
    private Toolbar toolbar;
    private Button pheno_next3;
    private Spinner spinner_comb_type,spinner_comb_color;
    private SpinnerAdapter spinnerAdapter,spinnerAdapter2;
    private String[] comb_type = {"Single", "Pea", "Rose"};
    private String[] comb_color = {"Red", "Pink", "Black"};
    private int[] comb_type_images = {R.drawable.comb_single,R.drawable.comb_pea,R.drawable.comb_rose};
    private int[] comb_color_images = {R.drawable.comb_red,R.drawable.comb_pink,R.drawable.comb_black};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_phenotypic_morphometric_add_pheno3);
        toolbar = findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Replacement");
        spinner_comb_type = findViewById(R.id.spinner_comb_type);
        spinner_comb_color = findViewById(R.id.spinner_comb_color);
        spinnerAdapter = new SpinnerAdapter(this,comb_type, comb_type_images);
        spinnerAdapter2 = new SpinnerAdapter(this,comb_color, comb_color_images);

        spinner_comb_type.setAdapter(spinnerAdapter);
        spinner_comb_color.setAdapter(spinnerAdapter2);

        pheno_next3 = findViewById(R.id.pheno_next3);
        pheno_next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_replacement_phenomorpho_next = new Intent(ReplacementPhenotypicMorphometricAddPheno3.this, ReplacementPhenotypicMorphometricAddPheno4.class);
                startActivity(intent_replacement_phenomorpho_next);
            }
        });
    }
}

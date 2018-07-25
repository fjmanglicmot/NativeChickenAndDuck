package com.example.cholomanglicmot.nativechickenandduck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;


public class ReplacementPhenotypicMorphometricAddPheno4 extends AppCompatActivity {
    private Toolbar toolbar;
    private Button pheno_next4;
    private Spinner spinner_earlobe_color,spinner_iris_color, spinner_beak_color;
    private SpinnerAdapter spinnerAdapter,spinnerAdapter2, spinnerAdapter3;
    private String[] earlobe_color = {"White", "Red-White", "Red"};
    private String[] iris_color = {"Red", "Brown", "Orange", "Yellow"};
    private String[] beak_color = {"White", "Black","Brown", "Yellow"};
    private int[] earlobe_color_images = {R.drawable.earlobe_red,R.drawable.earlobe_redwhite,R.drawable.earlobe_white};
    private int[] iris_color_images = {R.drawable.iris_red,R.drawable.iris_brown,R.drawable.iris_orange, R.drawable.iris_yellow};
    private int[] beak_color_images = {R.drawable.beak_white,R.drawable.beak_black,R.drawable.beak_brown,R.drawable.beak_yellow};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_phenotypic_morphometric_add_pheno4);
        toolbar = findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Replacement");
        spinner_earlobe_color = findViewById(R.id.spinner_earlobe_color);
        spinner_iris_color = findViewById(R.id.spinner_iris_color);
        spinner_beak_color = findViewById(R.id.spinner_beak_color);
        spinnerAdapter = new SpinnerAdapter(this,earlobe_color, earlobe_color_images);
        spinnerAdapter2 = new SpinnerAdapter(this,iris_color, iris_color_images);
        spinnerAdapter3 = new SpinnerAdapter(this,beak_color, beak_color_images);

        spinner_earlobe_color.setAdapter(spinnerAdapter);
        spinner_iris_color.setAdapter(spinnerAdapter2);
        spinner_beak_color.setAdapter(spinnerAdapter3);

        pheno_next4 = findViewById(R.id.pheno_next4);
        pheno_next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_replacement_phenomorpho_next = new Intent(ReplacementPhenotypicMorphometricAddPheno4.this, ReplacementPhenotypicMorphometricAddPheno5.class);
                startActivity(intent_replacement_phenomorpho_next);
            }
        });
    }
}

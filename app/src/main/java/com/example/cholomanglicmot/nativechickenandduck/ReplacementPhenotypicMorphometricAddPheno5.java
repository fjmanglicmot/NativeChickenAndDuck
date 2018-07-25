package com.example.cholomanglicmot.nativechickenandduck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class ReplacementPhenotypicMorphometricAddPheno5 extends AppCompatActivity {
    private Toolbar toolbar;
    private Button pheno_next5;
    private Spinner spinner_shank_color,spinner_skin_color;
    private SpinnerAdapter spinnerAdapter,spinnerAdapter2;
    private String[] shank_color = {"White", "Green", "Black","Grey","Yellow"};
    private String[] skin_color = {"White","Yellow"};
    private int[] shank_color_images = {R.drawable.shank_white,R.drawable.shank_green,R.drawable.shank_black, R.drawable.shank_grey, R.drawable.shank_yellow};
    private int[] skin_color_images = {R.drawable.skin_white,R.drawable.skin_yellow};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_phenotypic_morphometric_add_pheno5);
        toolbar = findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Replacement");
        spinner_shank_color = findViewById(R.id.spinner_shank_color);
        spinner_skin_color = findViewById(R.id.spinner_skin_color);
        spinnerAdapter = new SpinnerAdapter(this,shank_color, shank_color_images);
        spinnerAdapter2 = new SpinnerAdapter(this,skin_color, skin_color_images);

        spinner_shank_color.setAdapter(spinnerAdapter);
        spinner_skin_color.setAdapter(spinnerAdapter2);

        pheno_next5 = findViewById(R.id.pheno_next5);
        pheno_next5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(ReplacementPhenotypicMorphometricAddPheno5.this,"Added to database", Toast.LENGTH_SHORT).show();
                Intent intent_replacement_phenomorpho_next = new Intent(ReplacementPhenotypicMorphometricAddPheno5.this, ReplacementPhenotypicMorphometricAddMorpho.class);
                startActivity(intent_replacement_phenomorpho_next);
            }
        });
    }
}

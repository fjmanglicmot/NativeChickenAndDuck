package com.example.cholomanglicmot.nativechickenandduck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ReplacementPhenotypicMorphometricAddMorpho extends AppCompatActivity {
    private Toolbar toolbar;
    private Button submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_phenotypic_morphometric_add_morpho);
        toolbar = findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //ito yung backbutton
        getSupportActionBar().setTitle("Replacement");

        submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReplacementPhenotypicMorphometricAddMorpho.this, "Added to database", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReplacementPhenotypicMorphometricAddMorpho.this, ReplacementPhenotypicMorphometric.class);
                startActivity(intent);

            }
        });
    }
}

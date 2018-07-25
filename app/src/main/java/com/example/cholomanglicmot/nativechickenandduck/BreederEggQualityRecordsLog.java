package com.example.cholomanglicmot.nativechickenandduck;

import android.content.Intent;
import android.media.Image;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BreederEggQualityRecordsLog extends AppCompatActivity implements BreederEggQualityRecordsDialog1.DialogListener,BreederEggQualityRecordsDialog2.DialogListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private ImageButton create_new,add_property, edit_property, delete_property;
    private TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8;
    private TableRow tableRow;

    String breeder_egg_quality_records_noEggs_text,breeder_egg_quality_records_date_eggset_text,breeder_egg_quality_nofertile_text;
    LinkedHashMap<String, List<String>> Project_category;
    List<String> Project_list;
    ExpandableListView Exp_list;
    ProjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_egg_quality_records_log);
        create_new = findViewById(R.id.create_new);



        Exp_list = findViewById(R.id.exp_list);
        Project_category = DataProvider.getInfo();
        Project_list =  new ArrayList<String>(Project_category.keySet());
        adapter = new ProjectAdapter(this, Project_category, Project_list);
        Exp_list.setAdapter(adapter);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.closed);
        mToolbar = (Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Breeder");

        mDrawerLayout.addDrawerListener(mToggle);
        setSupportActionBar(mToolbar);
        mToolbar = (Toolbar)findViewById(R.id.nav_action);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Breeder");
        mToggle.syncState();

        final Intent intent = getIntent();
        breeder_egg_quality_records_noEggs_text = intent.getStringExtra("breeder_egg_quality_records_noEggs");
        breeder_egg_quality_records_date_eggset_text = intent.getStringExtra("breeder_egg_quality_records_date_eggset");

        textView1 = findViewById(R.id.breeder_egg_quality_number_set);
        textView2 = findViewById(R.id.breeder_egg_quality_date);
        textView3 = findViewById(R.id.breeder_egg_quality_layweek);
        textView4 = findViewById(R.id.breeder_egg_quality_records_nofertile);
        textView5 = findViewById(R.id.breeder_egg_quality_hatchedeggs);
        textView6 = findViewById(R.id.breeder_egg_quality_layweek_datehatched);
        textView7 = findViewById(R.id.breeder_egg_quality_movedtopen);
        textView8 = findViewById(R.id.breeder_egg_quality_remarks);
        add_property = findViewById(R.id.add_property);


        tableRow = findViewById(R.id.table_row2);

        try {
            if (!breeder_egg_quality_records_noEggs_text.isEmpty()){
                tableRow.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                textView1.setText(breeder_egg_quality_records_noEggs_text);
                textView2.setText(breeder_egg_quality_records_date_eggset_text);
                textView3.setText("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        add_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textView4.getText().toString().isEmpty()){
                    BreederEggQualityRecordsDialog1 newFragment = new BreederEggQualityRecordsDialog1();
                    newFragment.show(getSupportFragmentManager(), "BreederEggQualityRecordsLogDialog1");
                }else if (!textView4.getText().toString().isEmpty()){
                    BreederEggQualityRecordsDialog2 newFragment = new BreederEggQualityRecordsDialog2();
                    newFragment.show(getSupportFragmentManager(), "BreederEggQualityRecordsDialog2");
                }
            }
        });

        create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_breeder_egg_quality_reocrds_log_new = new Intent(BreederEggQualityRecordsLog.this, BreederEggQualityRecordsLogCreate1.class);
                startActivity(intent_breeder_egg_quality_reocrds_log_new);
            }
        });

        Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //  Toast.makeText(getBaseContext(),Project_category.get(Project_list.get(groupPosition)).get(childPosition) + " from category" + Project_list.get(groupPosition) + "is selected", Toast.LENGTH_SHORT).show();

                String string = Project_category.get(Project_list.get(groupPosition)).get(childPosition);
                switch (string){
                    case "Generation":
                        Intent intent_breeder_generation = new Intent(BreederEggQualityRecordsLog.this, BreederGeneration.class);
                        startActivity(intent_breeder_generation);
                        break;

                    case "Family Records":
                        Intent intent_breeder_family_records = new Intent(BreederEggQualityRecordsLog.this, BreederFamilyRecords.class);
                        startActivity(intent_breeder_family_records);
                        break;
                    case "Daily Records":
                        Intent intent_breeder_daily_records = new Intent(BreederEggQualityRecordsLog.this, BreederDailyRecords.class);
                        startActivity(intent_breeder_daily_records);
                        break;
                    case "Hatchery Records":
                        finish();
                        startActivity(getIntent());
                        break;
                    case "Egg Quality Records":
                        Intent intent_breeder_egg_quality_records = new Intent(BreederEggQualityRecordsLog.this, BreederEggQualityRecords.class);
                        startActivity(intent_breeder_egg_quality_records);
                        break;
                    case "Add Replacement Stocks":
                        Intent intent_replacement_individual_record_add = new Intent(BreederEggQualityRecordsLog.this, ReplacementIndividualRecordAdd.class);
                        startActivity(intent_replacement_individual_record_add);
                        break;
                    case "Phenotypic and Morphometric":
                        Intent intent_replacement_phenotypic_morphometric = new Intent(BreederEggQualityRecordsLog.this, ReplacementPhenotypicMorphometric.class);
                        startActivity(intent_replacement_phenotypic_morphometric);
                        break;
                    case "Feeding Record":
                        Intent intent_replacement_feeding_record = new Intent(BreederEggQualityRecordsLog.this, ReplacementFeedingRecord.class);
                        startActivity(intent_replacement_feeding_record);
                        break;
                    case "Growth Performance":
                        Intent intent_brooder_growth_performance = new Intent(BreederEggQualityRecordsLog.this, BroodersGrowthPerformance.class);
                        startActivity(intent_brooder_growth_performance);
                        break;
                    case "Feeding Records":
                        Intent intent_brooder_feeding_records = new Intent(BreederEggQualityRecordsLog.this, BrooderFeedingRecords.class);
                        startActivity(intent_brooder_feeding_records);
                        break;


                }
                return false;
            }


        });

        Exp_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String string2 = Project_list.get(groupPosition);

                switch(string2){
                    case "Dashboard":
                        Intent intent_main = new Intent(BreederEggQualityRecordsLog.this, MainActivity.class);
                        startActivity(intent_main);
                        break;

                    case "Create Pens":
                        Intent intent_create_pen = new Intent(BreederEggQualityRecordsLog.this, CreatePen.class);
                        startActivity(intent_create_pen);
                        break;

                    case "Mortality, Culling, and Sales":
                        break;

                    case "Reports":
                        break;

                    case "Farm Settings":

                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void applyTexts(String number_of_fertile_eggs) {
        textView4.setText(number_of_fertile_eggs);
    }

    @Override
    public void applyTexts(String number_hatched, String date_hatched, String moved_to_pen, String remarks) {
        textView5.setText(number_hatched);
        textView6.setText(date_hatched);
        textView7.setText(moved_to_pen);
        textView8.setText(remarks);
    }
}

package com.example.cholomanglicmot.nativechickenandduck.FamilyDirectory;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory.CreateBreeders;
import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.CreateBrooders;
import com.example.cholomanglicmot.nativechickenandduck.DashboardDirectory.MainActivity;
import com.example.cholomanglicmot.nativechickenandduck.DataProvider;
import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory.CreateGenerationsAndLines;
import com.example.cholomanglicmot.nativechickenandduck.PensDirectory.CreatePen;
import com.example.cholomanglicmot.nativechickenandduck.ProjectAdapter;
import com.example.cholomanglicmot.nativechickenandduck.R;
import com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory.CreateReplacements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.github.yavski.fabspeeddial.FabSpeedDial;


public class CreateFamilies extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private ImageButton create_family;
    //private Button create_family;


    LinkedHashMap<String, List<String>> Project_category;
    List<String> Project_list;
    ExpandableListView Exp_list;
    ProjectAdapter adapter;
    DatabaseHelper myDb;

    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Family> arrayList = new ArrayList<>();
    String[] lines;
    Map<String, ArrayList<String>> line_dictionary = new HashMap<String, ArrayList<String>>();
    ArrayList<String> list = new ArrayList<String>();
    StringBuffer buffer = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_families);
        Exp_list = findViewById(R.id.exp_list);
        Project_category = DataProvider.getInfo();
        Project_list =  new ArrayList<String>(Project_category.keySet());
        adapter = new ProjectAdapter(this, Project_category, Project_list);
        Exp_list.setAdapter(adapter);
        create_family = findViewById(R.id.open_dialog);
        FabSpeedDial fabSpeedDial = findViewById(R.id.fabSpeedDial);

        //delete_pen_table = findViewById(R.id.delete_pen_table);
        myDb = new DatabaseHelper(this);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        create_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateFamilyDialog newFragment = new CreateFamilyDialog();
                newFragment.show(getSupportFragmentManager(), "CreateFamilyDialog");

            }
        });




    /*    Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //  Toast.makeText(getBaseContext(),Project_category.get(Project_list.get(groupPosition)).get(childPosition) + " from category" + Project_list.get(groupPosition) + "is selected", Toast.LENGTH_SHORT).show();

                String string = Project_category.get(Project_list.get(groupPosition)).get(childPosition);
                switch (string){
                    case "Generation":
                        Intent intent_breeder_generation = new Intent(CreateFamilies.this, BreederGeneration.class);
                        startActivity(intent_breeder_generation);
                        break;
                    case "Family Records":
                        Intent intent_breeder_family_records = new Intent(CreateFamilies.this, BreederFamilyRecords.class);
                        startActivity(intent_breeder_family_records);
                        break;
                    case "Daily Records":
                        Intent intent_breeder_daily_records = new Intent(CreateFamilies.this, BreederDailyRecords.class);
                        startActivity(intent_breeder_daily_records);
                        break;
                    case "Hatchery Records":
                        Intent intent_breeder_hatchery_records = new Intent(CreateFamilies.this, BreederHatcheryRecords.class);
                        startActivity(intent_breeder_hatchery_records);
                        break;
                    case "Egg Quality Records":
                        Intent intent_breeder_egg_quality_records = new Intent(CreateFamilies.this, BreederEggQualityRecords.class);
                        startActivity(intent_breeder_egg_quality_records);
                        break;
                    case "Add Replacement Stocks":
                        Intent intent_replacement_individual_record_add = new Intent(CreateFamilies.this, ReplacementIndividualRecordAdd.class);
                        startActivity(intent_replacement_individual_record_add);
                        break;
                    case "Phenotypic and Morphometric":
                        Intent intent_replacement_phenotypic_morphometric = new Intent(CreateFamilies.this, ReplacementPhenotypicMorphometric.class);
                        startActivity(intent_replacement_phenotypic_morphometric);
                        break;
                    case "Feeding Record":
                        Intent intent_replacement_feeding_record = new Intent(CreateFamilies.this, ReplacementFeedingRecord.class);
                        startActivity(intent_replacement_feeding_record);
                        break;
                    case "Growth Performance":
                        Intent intent_brooder_growth_performance = new Intent(CreateFamilies.this, BroodersGrowthPerformance.class);
                        startActivity(intent_brooder_growth_performance);
                        break;
                    case "Feeding Records":
                        Intent intent_brooder_feeding_records = new Intent(CreateFamilies.this, BrooderFeedingRecords.class);
                        startActivity(intent_brooder_feeding_records);
                        break;
                }
                return false;
            }


        });*/

        Exp_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String string2 = Project_list.get(groupPosition);

                switch(string2){
                    case "Dashboard":
                        Intent intent_main = new Intent(CreateFamilies.this, MainActivity.class);
                        startActivity(intent_main);
                        break;

                    case "Pens":
                        Intent intent_create_pens = new Intent(CreateFamilies.this, CreatePen.class);
                        startActivity(intent_create_pens);
                        break;
                    case "Generations and Lines":
                        Intent intent_create_generation_and_lines = new Intent(CreateFamilies.this, CreateGenerationsAndLines.class);
                        startActivity(intent_create_generation_and_lines);
                        break;
                    case "Families":
                        finish();
                        startActivity(getIntent());
                        break;
                    case "Breeders":
                        Intent intent_breeders = new Intent(CreateFamilies.this, CreateBreeders.class);
                        startActivity(intent_breeders);
                        break;
                    case "Brooders":
                        Intent intent_brooders = new Intent(CreateFamilies.this, CreateBrooders.class);
                        startActivity(intent_brooders);
                        break;
                    case "Replacements":
                        Intent intent_replacements = new Intent(CreateFamilies.this, CreateReplacements.class);
                        startActivity(intent_replacements);
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
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.closed);
        mToolbar = (Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Families");


        Cursor cursor = myDb.getAllDataFromFamily();
        cursor.moveToFirst();

//-----DATABASE
        if(cursor.getCount() == 0){
            //show message
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
            return;
        }else{




            do {
                String line_number = null;
                Integer generation_id = null;
                String generation_number = null;
                Cursor cursor1 = myDb.getDataFromLineWhereID(cursor.getInt(2));

                cursor1.moveToFirst();

                if(cursor1.getCount() != 0){
                    line_number = cursor1.getString(1);
                    generation_id = cursor1.getInt(2);

                    Cursor cursor2 = myDb.getDataFromGenerationWhereID(generation_id);
                    cursor2.moveToFirst();
                    generation_number = cursor2.getString(0);
                }
                Family family = new Family(cursor.getString(1), line_number, generation_number);
                arrayList.add(family);
            }while (cursor.moveToNext());

            recycler_adapter = new RecyclerAdapter_Family(arrayList);
            recyclerView.setAdapter(recycler_adapter);
            recycler_adapter.notifyDataSetChanged();

        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    @Override
    public void onBackPressed() {


        finish();
        startActivity(getIntent());

    }


}
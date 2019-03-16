package com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory.CreateBreeders;
import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.CreateBrooders;
import com.example.cholomanglicmot.nativechickenandduck.DashboardDirectory.MainActivity;
import com.example.cholomanglicmot.nativechickenandduck.DataProvider;
import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.FamilyDirectory.CreateFamilies;
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

public class CreateGenerationsAndLines extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private ImageButton create_generation;
    private Button show_data_button;


    LinkedHashMap<String, List<String>> Project_category;
    List<String> Project_list;
    ExpandableListView Exp_list;
    ProjectAdapter adapter;
    DatabaseHelper myDb;

    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Generation> arrayList = new ArrayList<>();

    Map<String, ArrayList<String>> line_dictionary = new HashMap<String, ArrayList<String>>();
    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generations_and_lines);
        Exp_list = findViewById(R.id.exp_list);
        Project_category = DataProvider.getInfo();
        Project_list =  new ArrayList<String>(Project_category.keySet());
        adapter = new ProjectAdapter(this, Project_category, Project_list);
        Exp_list.setAdapter(adapter);
        create_generation = findViewById(R.id.open_dialog);
        FabSpeedDial fabSpeedDial = findViewById(R.id.fabSpeedDial);
        //show_data_button = findViewById(R.id.show_data_button);
        //delete_pen_table = findViewById(R.id.delete_pen_table);
        myDb = new DatabaseHelper(this);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);




        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.create_generation_btn:

                        CreateGenerationDialog newFragment = new CreateGenerationDialog();
                        newFragment.show(getSupportFragmentManager(), "CreateGenerationDialog");
                        return true;


                    case R.id.create_line_btn:

                        CreateLineDialog newFragment1 = new CreateLineDialog();
                        newFragment1.show(getSupportFragmentManager(), "CreateFamilyDialog");
                        return true;


                }
                return true;


            }

            @Override
            public void onMenuClosed() {

            }
        });

     /*   Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //  Toast.makeText(getBaseContext(),Project_category.get(Project_list.get(groupPosition)).get(childPosition) + " from category" + Project_list.get(groupPosition) + "is selected", Toast.LENGTH_SHORT).show();

                String string = Project_category.get(Project_list.get(groupPosition)).get(childPosition);
                switch (string){
                    case "Generation":
                        Intent intent_breeder_generation = new Intent(CreateGenerationsAndLines.this, BreederGeneration.class);
                        startActivity(intent_breeder_generation);
                        break;
                    case "Family Records":
                        Intent intent_breeder_family_records = new Intent(CreateGenerationsAndLines.this, BreederFamilyRecords.class);
                        startActivity(intent_breeder_family_records);
                        break;
                    case "Daily Records":
                        Intent intent_breeder_daily_records = new Intent(CreateGenerationsAndLines.this, BreederDailyRecords.class);
                        startActivity(intent_breeder_daily_records);
                        break;
                    case "Hatchery Records":
                        Intent intent_breeder_hatchery_records = new Intent(CreateGenerationsAndLines.this, BreederHatcheryRecords.class);
                        startActivity(intent_breeder_hatchery_records);
                        break;
                    case "Egg Quality Records":
                        Intent intent_breeder_egg_quality_records = new Intent(CreateGenerationsAndLines.this, BreederEggQualityRecords.class);
                        startActivity(intent_breeder_egg_quality_records);
                        break;
                    case "Add Replacement Stocks":
                        Intent intent_replacement_individual_record_add = new Intent(CreateGenerationsAndLines.this, ReplacementIndividualRecordAdd.class);
                        startActivity(intent_replacement_individual_record_add);
                        break;
                    case "Phenotypic and Morphometric":
                        Intent intent_replacement_phenotypic_morphometric = new Intent(CreateGenerationsAndLines.this, ReplacementPhenotypicMorphometric.class);
                        startActivity(intent_replacement_phenotypic_morphometric);
                        break;
                    case "Feeding Record":
                        Intent intent_replacement_feeding_record = new Intent(CreateGenerationsAndLines.this, ReplacementFeedingRecord.class);
                        startActivity(intent_replacement_feeding_record);
                        break;
                    case "Growth Performance":
                        Intent intent_brooder_growth_performance = new Intent(CreateGenerationsAndLines.this, BroodersGrowthPerformance.class);
                        startActivity(intent_brooder_growth_performance);
                        break;
                    case "Feeding Records":
                        Intent intent_brooder_feeding_records = new Intent(CreateGenerationsAndLines.this, BrooderFeedingRecords.class);
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
                        Intent intent_main = new Intent(CreateGenerationsAndLines.this, MainActivity.class);
                        startActivity(intent_main);
                        break;

                    case "Pens":
                        Intent intent_create_pens = new Intent(CreateGenerationsAndLines.this, CreatePen.class);
                        startActivity(intent_create_pens);
                        break;
                    case "Generations and Lines":
                        finish();
                        startActivity(getIntent());
                        break;
                    case "Families":
                        Intent intent_families = new Intent(CreateGenerationsAndLines.this, CreateFamilies.class);
                        startActivity(intent_families);
                        break;
                    case "Breeders":
                        Intent intent_breeders = new Intent(CreateGenerationsAndLines.this, CreateBreeders.class);
                        startActivity(intent_breeders);
                        break;
                    case "Brooders":
                        Intent intent_brooders = new Intent(CreateGenerationsAndLines.this, CreateBrooders.class);
                        startActivity(intent_brooders);
                        break;
                    case "Replacements":
                        Intent intent_replacements = new Intent(CreateGenerationsAndLines.this, CreateReplacements.class);
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
        getSupportActionBar().setTitle("Generations and Lines");
        Cursor cursor = myDb.getAllDataFromGeneration();
        Cursor line_cursor = myDb.getAllDataFromLine();

//-----DATABASE
        if(cursor.getCount() == 0){
            //show message
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
            return;
        }else{

            cursor.moveToFirst();
            do {
                Generation generation = new Generation(cursor.getString(0));

                arrayList.add(generation);

            }while (cursor.moveToNext());


            line_cursor.moveToFirst();
            if(line_cursor.getCount() == 0){
                //show message
                Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();

            }else {
                do {

                    if (line_dictionary.containsKey(line_cursor.getString(2))) {
                        list = line_dictionary.get(line_cursor.getString(2));
                        list.add(line_cursor.getString(1));
                    } else {
                        ArrayList<String> list1 = new ArrayList<String>();
                        list1.add(line_cursor.getString(1));
                        line_dictionary.put(line_cursor.getString(2), list1);
                    }


                } while (line_cursor.moveToNext());
            }




            recycler_adapter = new RecyclerAdapter_Generation(arrayList,line_dictionary);
            recyclerView.setAdapter(recycler_adapter);
            recycler_adapter.notifyDataSetChanged(); //does not work, kailangan may way ka para maupdate yung adapter mo

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

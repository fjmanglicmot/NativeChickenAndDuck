package com.example.cholomanglicmot.nativechickenandduck.PensDirectory;

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
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory.CreateBreeders;
import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.CreateBrooders;
import com.example.cholomanglicmot.nativechickenandduck.DashboardDirectory.DashBoardActivity;
import com.example.cholomanglicmot.nativechickenandduck.DataProvider;
import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.FamilyDirectory.CreateFamilies;
import com.example.cholomanglicmot.nativechickenandduck.FarmSettingsDirectory.MainActivity;
import com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory.CreateGenerationsAndLines;
import com.example.cholomanglicmot.nativechickenandduck.ProjectAdapter;
import com.example.cholomanglicmot.nativechickenandduck.R;
import com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory.CreateReplacements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CreatePen extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private ImageButton create_pen;
    private Button show_data_button;
    private Button delete_pen_table;

    LinkedHashMap<String, List<String>> Project_category;
    List<String> Project_list;
    ExpandableListView Exp_list;
    ProjectAdapter adapter;
    DatabaseHelper myDb;

    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Pen> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pen);
        Exp_list = findViewById(R.id.exp_list);
        Project_category = DataProvider.getInfo();
        Project_list =  new ArrayList<String>(Project_category.keySet());
        adapter = new ProjectAdapter(this, Project_category, Project_list);
        Exp_list.setAdapter(adapter);
        create_pen = findViewById(R.id.open_dialog);


        myDb = new DatabaseHelper(this);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);




        create_pen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreatePenDialog newFragment = new CreatePenDialog();
                newFragment.show(getSupportFragmentManager(), "CreatePenDialog");

            }
        });


      /*  Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //  Toast.makeText(getBaseContext(),Project_category.get(Project_list.get(groupPosition)).get(childPosition) + " from category" + Project_list.get(groupPosition) + "is selected", Toast.LENGTH_SHORT).show();

                String string = Project_category.get(Project_list.get(groupPosition)).get(childPosition);
                switch (string){
                    case "Generation":
                        Intent intent_breeder_generation = new Intent(CreatePen.this, BreederGeneration.class);
                        startActivity(intent_breeder_generation);
                        break;
                    case "Family Records":
                        Intent intent_breeder_family_records = new Intent(CreatePen.this, BreederFamilyRecords.class);
                        startActivity(intent_breeder_family_records);
                        break;
                    case "Daily Records":
                        Intent intent_breeder_daily_records = new Intent(CreatePen.this, BreederDailyRecords.class);
                        startActivity(intent_breeder_daily_records);
                        break;
                    case "Hatchery Records":
                        Intent intent_breeder_hatchery_records = new Intent(CreatePen.this, BreederHatcheryRecords.class);
                        startActivity(intent_breeder_hatchery_records);
                        break;
                    case "Egg Quality Records":
                        Intent intent_breeder_egg_quality_records = new Intent(CreatePen.this, BreederEggQualityRecords.class);
                        startActivity(intent_breeder_egg_quality_records);
                        break;
                    case "Add Replacement Stocks":
                        Intent intent_replacement_individual_record_add = new Intent(CreatePen.this, ReplacementIndividualRecordAdd.class);
                        startActivity(intent_replacement_individual_record_add);
                        break;
                    case "Phenotypic and Morphometric":
                        Intent intent_replacement_phenotypic_morphometric = new Intent(CreatePen.this, ReplacementPhenotypicMorphometric.class);
                        startActivity(intent_replacement_phenotypic_morphometric);
                        break;
                    case "Feeding Record":
                        Intent intent_replacement_feeding_record = new Intent(CreatePen.this, ReplacementFeedingRecord.class);
                        startActivity(intent_replacement_feeding_record);
                        break;
                    case "Growth Performance":
                        Intent intent_brooder_growth_performance = new Intent(CreatePen.this, BroodersGrowthPerformance.class);
                        startActivity(intent_brooder_growth_performance);
                        break;
                    case "Feeding Records":
                        Intent intent_brooder_feeding_records = new Intent(CreatePen.this, BrooderFeedingRecords.class);
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
                        Intent intent_main = new Intent(CreatePen.this, DashBoardActivity.class);
                        startActivity(intent_main);
                        break;
                    case "Pens":
                        finish();
                        startActivity(getIntent());
                        break;
                    case "Generations and Lines":
                        Intent intent_generations_and_lines = new Intent(CreatePen.this, CreateGenerationsAndLines.class);
                        startActivity(intent_generations_and_lines);
                        break;
                    case "Families":
                        Intent intent_families = new Intent(CreatePen.this, CreateFamilies.class);
                        startActivity(intent_families);
                        break;
                    case "Breeders":
                        Intent intent_breeders = new Intent(CreatePen.this, CreateBreeders.class);
                        startActivity(intent_breeders);
                        break;
                    case "Brooders":
                        Intent intent_brooders = new Intent(CreatePen.this, CreateBrooders.class);
                        startActivity(intent_brooders);
                        break;
                    case "Replacements":
                        Intent intent_replacements = new Intent(CreatePen.this, CreateReplacements.class);
                        startActivity(intent_replacements);
                        break;
                      case "Reports":
                        break;

                    case "Farm Settings":
                        Intent intent = new Intent(CreatePen.this, MainActivity.class);
                        startActivity(intent);
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
        getSupportActionBar().setTitle("Create Pens");
        Cursor cursor = myDb.getAllDataFromPen();

//-----DATABASE
        if(cursor.getCount() == 0){
            //show message
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
            return;
        }else{

            cursor.moveToFirst();
            do {
                Pen pen = new Pen(cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4));
                arrayList.add(pen);
            }while (cursor.moveToNext());

            recycler_adapter = new RecyclerAdapter_Pen(arrayList);
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

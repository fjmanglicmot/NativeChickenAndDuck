package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

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

import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.CreateBrooders;
import com.example.cholomanglicmot.nativechickenandduck.DashboardDirectory.MainActivity;
import com.example.cholomanglicmot.nativechickenandduck.DataProvider;
import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.FamilyDirectory.CreateFamilies;
import com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory.CreateGenerationsAndLines;
import com.example.cholomanglicmot.nativechickenandduck.PensDirectory.CreatePen;
import com.example.cholomanglicmot.nativechickenandduck.ProjectAdapter;
import com.example.cholomanglicmot.nativechickenandduck.R;
import com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory.CreateReplacements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CreateBreeders extends AppCompatActivity {
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
    ArrayList<Breeders> arrayListBreeders = new ArrayList<>();
    ArrayList<Breeder_Inventory> arrayListBreederInventory = new ArrayList<>();
    ArrayList<Breeder_Inventory> arrayListBreederInventory2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_breeders);
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

                CreateBreederDialog newFragment = new CreateBreederDialog();
                newFragment.show(getSupportFragmentManager(), "CreateBreederDialog");

            }
        });
    /*    delete_pen_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb.deleteAll();
            }
        });
*/
      /*  Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //  Toast.makeText(getBaseContext(),Project_category.get(Project_list.get(groupPosition)).get(childPosition) + " from category" + Project_list.get(groupPosition) + "is selected", Toast.LENGTH_SHORT).show();

                String string = Project_category.get(Project_list.get(groupPosition)).get(childPosition);
                switch (string){
                    case "Generation":
                        Intent intent_breeder_generation = new Intent(CreateBreeders.this, BreederGeneration.class);
                        startActivity(intent_breeder_generation);
                        break;
                    case "Family Records":
                        Intent intent_breeder_family_records = new Intent(CreateBreeders.this, BreederFamilyRecords.class);
                        startActivity(intent_breeder_family_records);
                        break;
                    case "Daily Records":
                        Intent intent_breeder_daily_records = new Intent(CreateBreeders.this, BreederDailyRecords.class);
                        startActivity(intent_breeder_daily_records);
                        break;
                    case "Hatchery Records":
                        Intent intent_breeder_hatchery_records = new Intent(CreateBreeders.this, BreederHatcheryRecords.class);
                        startActivity(intent_breeder_hatchery_records);
                        break;
                    case "Egg Quality Records":
                        Intent intent_breeder_egg_quality_records = new Intent(CreateBreeders.this, BreederEggQualityRecords.class);
                        startActivity(intent_breeder_egg_quality_records);
                        break;
                    case "Add Replacement Stocks":
                        Intent intent_replacement_individual_record_add = new Intent(CreateBreeders.this, ReplacementIndividualRecordAdd.class);
                        startActivity(intent_replacement_individual_record_add);
                        break;
                    case "Phenotypic and Morphometric":
                        Intent intent_replacement_phenotypic_morphometric = new Intent(CreateBreeders.this, ReplacementPhenotypicMorphometric.class);
                        startActivity(intent_replacement_phenotypic_morphometric);
                        break;
                    case "Feeding Record":
                        Intent intent_replacement_feeding_record = new Intent(CreateBreeders.this, ReplacementFeedingRecord.class);
                        startActivity(intent_replacement_feeding_record);
                        break;
                    case "Growth Performance":
                        Intent intent_brooder_growth_performance = new Intent(CreateBreeders.this, BroodersGrowthPerformance.class);
                        startActivity(intent_brooder_growth_performance);
                        break;
                    case "Feeding Records":
                        Intent intent_brooder_feeding_records = new Intent(CreateBreeders.this, BrooderFeedingRecords.class);
                        startActivity(intent_brooder_feeding_records);
                        break;
                }
                return false;
            }


        });
*/
        Exp_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String string2 = Project_list.get(groupPosition);

                switch(string2){
                    case "Dashboard":
                        Intent intent_main = new Intent(CreateBreeders.this, MainActivity.class);
                        startActivity(intent_main);
                        break;
                    case "Pens":
                        Intent intent_pens = new Intent(CreateBreeders.this, CreatePen.class);
                        startActivity(intent_pens);
                        break;
                    case "Generations and Lines":
                        Intent intent_generations_and_lines = new Intent(CreateBreeders.this, CreateGenerationsAndLines.class);
                        startActivity(intent_generations_and_lines);
                        break;
                    case "Families":
                        Intent intent_families = new Intent(CreateBreeders.this, CreateFamilies.class);
                        startActivity(intent_families);
                        break;
                    case "Breeders":
                        finish();
                        startActivity(getIntent());
                        break;
                    case "Brooders":
                        Intent intent_brooders = new Intent(CreateBreeders.this, CreateBrooders.class);
                        startActivity(intent_brooders);
                        break;
                    case "Replacements":
                        Intent intent_replacements = new Intent(CreateBreeders.this, CreateReplacements.class);
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
        getSupportActionBar().setTitle("Create Breeders");





        ///////////////////////-----DATABASE


        Cursor cursorBreederInv = myDb.getAllDataFromBreederInventory();
        cursorBreederInv.moveToFirst();
        if(cursorBreederInv.getCount()==0){
            Toast.makeText(this, "No breeder inventory data", Toast.LENGTH_SHORT).show();

        }else{
            //for getting breeders
            Cursor cursor = myDb.getAllDataFromBreeders();
            cursor.moveToFirst();

            if(cursor.getCount() == 0){
                //show message

            }else{

                do {
                    Breeders breeders = new Breeders(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                    arrayListBreeders.add(breeders);
                }while (cursor.moveToNext());
                /*else {

            do {
                //                                                                        Integer id,                 Integer brooder_growth_inventory_id,String , Integer brooder_growth_collection_day,      String brooder_growth_date_collected,       Integer brooder_growth_male_quantity,           Float brooder_growth_male_weight,                  Integer brooder_growth_female_quantity, Float brooder_growth_female_weight,          Integer brooder_growth_total_quantity,      Float brooder_growth_total_weight,              String brooder_growth_deleted_at){
                for(int k=0;k<arrayList_temp.size();k++){
                    if(arrayList_temp.get(k).getBrooder_inv_brooder_id().equals(cursor_brooder_growth_records.getInt(1))){
                        Brooder_GrowthRecords brooderGrowthRecords = new Brooder_GrowthRecords(cursor_brooder_growth_records.getInt(0),cursor_brooder_growth_records.getInt(1),arrayList_temp.get(k).getBrooder_inv_brooder_tag(),cursor_brooder_growth_records.getInt(2), cursor_brooder_growth_records.getString(3),cursor_brooder_growth_records.getInt(4), cursor_brooder_growth_records.getFloat(5), cursor_brooder_growth_records.getInt(6), cursor_brooder_growth_records.getFloat(7),cursor_brooder_growth_records.getInt(8), cursor_brooder_growth_records.getFloat(9), cursor_brooder_growth_records.getString(10));
                        arrayListBrooderGrowthRecords.add(brooderGrowthRecords);
                        buffer.append("Browth brooder_inv_id"+cursor_brooder_growth_records.getInt(1) + "\n");

                    }
                }


            } while (cursor_brooder_growth_records.moveToNext());
        }*/
                do{
                    for(int i=0;i<arrayListBreeders.size();i++){
                        if(arrayListBreeders.get(i).getId().equals(cursorBreederInv.getInt(1))){
                                                                            /*              Integer id,             Integer brooder_inv_brooder_id, S   tring brooder_inv_pen,      String brooder_inv_brooder_tag,      String brooder_inv_batching_date, Integer brooder_male_quantity, Integer brooder_female_quantity   brooder_total_quantity, String brooder_inv_last_update, String brooder_inv_deleted_at, String family, String line, String generation) {
                             */
                            Breeder_Inventory breeder_inventory = new Breeder_Inventory(cursorBreederInv.getInt(0), cursorBreederInv.getInt(1), cursorBreederInv.getString(2), cursorBreederInv.getString(3), cursorBreederInv.getString(4), cursorBreederInv.getInt(5), cursorBreederInv.getInt(6), cursorBreederInv.getInt(7),cursorBreederInv.getString(8), cursorBreederInv.getString(9), arrayListBreeders.get(i).getFamily_number(), arrayListBreeders.get(i).getLine_number(), arrayListBreeders.get(i).getGeneration_number());
                            arrayListBreederInventory2.add(breeder_inventory);
                            /*
                                                                                     BREEDER_INV_COL_0 = "ID";          BREEDER_INV_BREEDER_ID";     "BREEDER_INV_PEN_NUMBER";       "BREEDER_INV_BREEDER_TAG";              "BREEDER_INV_BATCHING_DATE";    = "BREEDER_INV_NUMBER_MALE";     BREEDER_INV_NUMBER_FEMALE";    BREEDER_INV_TOTAL";    "BREEDER_INV_LAST_UPDATE";              "BREEDER_INV_DELETED_AT";*/                        }
                    }

                }while(cursorBreederInv.moveToNext());
            }


        }


        recycler_adapter = new RecyclerAdapter_Breeder(arrayListBreederInventory2);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();
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


}

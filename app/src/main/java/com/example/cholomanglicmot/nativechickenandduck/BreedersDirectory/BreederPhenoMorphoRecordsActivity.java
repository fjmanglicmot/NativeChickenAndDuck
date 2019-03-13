package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;
import com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory.Replacement_PhenoMorphoRecords;

import java.util.ArrayList;

public class BreederPhenoMorphoRecordsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    private TextView text_pen;
    DatabaseHelper myDb;
    private Toolbar mToolbar;
    ArrayList<Replacement_PhenoMorphoRecords> arrayListGrowth = new ArrayList<>();
    ArrayList<Breeder_Inventory>arrayListReplacementInventory = new ArrayList<>();
    ArrayList<Breeder_Inventory>arrayListReplacementInventory1 = new ArrayList<>();
    ArrayList<Breeder_PhenoMorphoRecords>arrayList_temp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_pheno_morpho_records);

        String replacement_pen;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                replacement_pen= null;
            } else {
                replacement_pen= extras.getString("Replacement Pen");
            }
        } else {
            replacement_pen= (String) savedInstanceState.getSerializable("Replacement Pen");
        }


        text_pen = findViewById(R.id.pheno_title);
        text_pen.setText("Phenotypic & Morphometric Data | Pen "+replacement_pen);

        myDb = new DatabaseHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewReplacementInventory);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Replacement Inventory");
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //////DATABASE
        ///////////////////////////////DATABASE


        ////inventory
        Cursor cursor_inventory = myDb.getAllDataFromBreederInventory();
        cursor_inventory.moveToFirst();

        if (cursor_inventory.getCount() == 0) {

        } else {
            do {
               /*    public static final String TABLE_BREEDER_INVENTORIES = "breeder_inventory_table";
    public static final String BREEDER_INV_COL_0 = "ID";
    public static final String BREEDER_INV_COL_1 = "BREEDER_INV_BREEDER_ID";
    public static final String BREEDER_INV_COL_2 = "BREEDER_INV_PEN_NUMBER";
    public static final String BREEDER_INV_COL_3 = "BREEDER_INV_BREEDER_TAG";
    public static final String BREEDER_INV_COL_4 = "BREEDER_INV_BATCHING_DATE";
    public static final String BREEDER_INV_COL_5 = "BREEDER_INV_NUMBER_MALE";
    public static final String BREEDER_INV_COL_6 = "BREEDER_INV_NUMBER_FEMALE";
    public static final String BREEDER_INV_COL_7 = "BREEDER_INV_TOTAL";
    public static final String BREEDER_INV_COL_8 = "BREEDER_INV_LAST_UPDATE";
    public static final String BREEDER_INV_COL_9 = "BREEDER_INV_DELETED_AT";*/

                                                                                  /*    private Integer id;brooder_inv_brooder_id           ;brooder_inv_pen;               brooder_inv_brooder_tag;        brooder_inv_batching_date;  brooder_male_quantity           ;brooder_female_quantity;       brooder_total_quantity;      brooder_inv_last_update;         brooder_inv_deleted_at;f            amily;line;generation;*/
                Breeder_Inventory breeder_inventory = new Breeder_Inventory(cursor_inventory.getInt(0), cursor_inventory.getInt(1), cursor_inventory.getString(2),cursor_inventory.getString(3),cursor_inventory.getString(4),cursor_inventory.getInt(5), cursor_inventory.getInt(6), cursor_inventory.getInt(7), cursor_inventory.getString(8), cursor_inventory.getString(9),  null,null,null);
                arrayListReplacementInventory.add(breeder_inventory);
            } while (cursor_inventory.moveToNext());
        }
        ///GROWTH RECORDS
        Cursor cursor_growth = myDb.getAllDataFromBreederPhenoMorphoRecords();
        cursor_growth.moveToFirst();

        if(cursor_growth.getCount()==0){
            Toast.makeText(this,"No data from pheno and morpho records", Toast.LENGTH_SHORT).show();
        }else{
            do{


                // Replacement_PhenoMorphoRecords replacement_phenoMorphoRecords = new Replacement_PhenoMorphoRecords(cursor_growth.getInt(0),cursor_growth.getInt(1), cursor_growth.getString(2), cursor_growth.getString(3), cursor_growth.getString(4), cursor_growth.getString(5), null,null);
                //add constrcutor for pheno and morpho
               // arrayList_temp.add(replacement_phenoMorphoRecords);
            }while (cursor_growth.moveToNext());

        }

        //kunin mo naman yung replacement inventories ng given PEN NUMBER
        for (int i = 0; i<arrayListReplacementInventory.size();i++){
            if (arrayListReplacementInventory.get(i).getBrooder_inv_pen().equals(replacement_pen)){
                arrayListReplacementInventory1.add(arrayListReplacementInventory.get(i));


            }
        }

        //kunin mo naman yung feeding records based sa id ng brooder inventories sa arrayListReplacementInventory1
/*
        for (int i=0; i<arrayList_temp.size();i++){
            for(int j=0;j<arrayListReplacementInventory1.size();j++){
                if (arrayList_temp.get(i).getPheno_morpho_inv_id().equals(arrayListReplacementInventory1.get(j).getId())){
                    arrayListGrowth.add(arrayList_temp.get(i));

                }
            }
        }*/
        recycler_adapter = new RecyclerAdapter_Breeder_PhenoMorpho(arrayListReplacementInventory1);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

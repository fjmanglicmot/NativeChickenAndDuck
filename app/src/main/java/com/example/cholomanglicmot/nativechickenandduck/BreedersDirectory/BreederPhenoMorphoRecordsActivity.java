package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

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

        String replacement_pen,breeder_tag;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                replacement_pen= null;
                breeder_tag = null;
            } else {
                replacement_pen= extras.getString("Replacement Pen");
                breeder_tag = extras.getString("Breeder Tag");
            }
        } else {
            replacement_pen= (String) savedInstanceState.getSerializable("Replacement Pen");
            breeder_tag = (String) savedInstanceState.getSerializable("Breeder Tag");
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
        getSupportActionBar().setTitle("Breeder Inventory");
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //////DATABASE
        ///////////////////////////////DATABASE


        ////inventory
        Cursor cursor_inventory = myDb.getDataFromBreederInvWhereTag(breeder_tag);
        cursor_inventory.moveToFirst();

        if (cursor_inventory.getCount() == 0) {

        } else {
            do {


                                                                                  /*    private Integer id;brooder_inv_brooder_id           ;brooder_inv_pen;               brooder_inv_brooder_tag;        brooder_inv_batching_date;  brooder_male_quantity           ;brooder_female_quantity;       brooder_total_quantity;      brooder_inv_last_update;         brooder_inv_deleted_at;f            amily;line;generation;*/
                Breeder_Inventory breeder_inventory = new Breeder_Inventory(cursor_inventory.getInt(0), cursor_inventory.getInt(1), cursor_inventory.getString(2),cursor_inventory.getString(3),cursor_inventory.getString(4),cursor_inventory.getInt(5), cursor_inventory.getInt(6), cursor_inventory.getInt(7), cursor_inventory.getString(8), cursor_inventory.getString(9),  null,null,null);
                arrayListReplacementInventory.add(breeder_inventory);
            } while (cursor_inventory.moveToNext());
        }


        //kunin mo naman yung replacement inventories ng given PEN NUMBER
   /*     for (int i = 0; i<arrayListReplacementInventory.size();i++){
            if (arrayListReplacementInventory.get(i).getBrooder_inv_pen().equals(replacement_pen)){
                arrayListReplacementInventory1.add(arrayListReplacementInventory.get(i));


            }
        }
*/
        //kunin mo naman yung feeding records based sa id ng brooder inventories sa arrayListReplacementInventory1
/*
        for (int i=0; i<arrayList_temp.size();i++){
            for(int j=0;j<arrayListReplacementInventory1.size();j++){
                if (arrayList_temp.get(i).getPheno_morpho_inv_id().equals(arrayListReplacementInventory1.get(j).getId())){
                    arrayListGrowth.add(arrayList_temp.get(i));

                }
            }
        }*/
        recycler_adapter = new RecyclerAdapter_Breeder_PhenoMorpho(arrayListReplacementInventory);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();



    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent_brooders = new Intent(BreederPhenoMorphoRecordsActivity.this, CreateBreeders.class);
        startActivity(intent_brooders);
        return true;
    }

    @Override
    public void onBackPressed() {


        Intent intent_brooders = new Intent(BreederPhenoMorphoRecordsActivity.this, CreateBreeders.class);
        startActivity(intent_brooders);

    }
}

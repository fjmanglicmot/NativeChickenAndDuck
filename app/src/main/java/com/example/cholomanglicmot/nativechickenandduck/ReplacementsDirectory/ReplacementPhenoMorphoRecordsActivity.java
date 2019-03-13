package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

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

import java.util.ArrayList;

public class ReplacementPhenoMorphoRecordsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    private TextView text_pen;
    DatabaseHelper myDb;
    private Toolbar mToolbar;
    ArrayList<Replacement_PhenoMorphoRecords> arrayListGrowth = new ArrayList<>();
    ArrayList<Replacement_Inventory>arrayListReplacementInventory = new ArrayList<>();
    ArrayList<Replacement_Inventory>arrayListReplacementInventory1 = new ArrayList<>();
    ArrayList<Replacement_PhenoMorphoRecords>arrayList_temp = new ArrayList<>();

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
        Cursor cursor_inventory = myDb.getAllDataFromReplacementInventory();
        cursor_inventory.moveToFirst();

        if (cursor_inventory.getCount() == 0) {

        } else {
            do {
                Replacement_Inventory replacement_inventory = new Replacement_Inventory(cursor_inventory.getInt(0), cursor_inventory.getInt(1), null,null,null,cursor_inventory.getString(2), cursor_inventory.getString(3), cursor_inventory.getString(4), cursor_inventory.getInt(5), cursor_inventory.getInt(6), cursor_inventory.getInt(7), null, cursor_inventory.getString(8), cursor_inventory.getString(9));
                arrayListReplacementInventory.add(replacement_inventory);
            } while (cursor_inventory.moveToNext());
        }
        ///GROWTH RECORDS
        Cursor cursor_growth = myDb.getAllDataFromReplacementPhenoMorphoRecords();
        cursor_growth.moveToFirst();

        if(cursor_growth.getCount()==0){
            Toast.makeText(this,"No data from pheno and morpho records", Toast.LENGTH_SHORT).show();
        }else{
            do{


                 Replacement_PhenoMorphoRecords replacement_phenoMorphoRecords = new Replacement_PhenoMorphoRecords(cursor_growth.getInt(0),cursor_growth.getInt(1), cursor_growth.getString(2), cursor_growth.getString(3), cursor_growth.getString(4), cursor_growth.getString(5), null,null);
                //add constrcutor for pheno and morpho
                arrayList_temp.add(replacement_phenoMorphoRecords);
            }while (cursor_growth.moveToNext());

        }

        //kunin mo naman yung replacement inventories ng given PEN NUMBER
        for (int i = 0; i<arrayListReplacementInventory.size();i++){
            if (arrayListReplacementInventory.get(i).getReplacement_inv_pen().equals(replacement_pen)){
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
        recycler_adapter = new RecyclerAdapter_Replacement_PhenoMorpho(arrayListReplacementInventory1);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

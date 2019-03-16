package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

import android.app.AlertDialog;
import android.content.Intent;
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

public class BreederPhenoMorphoViewActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView replacement_pheno_inv_id;

    DatabaseHelper myDb;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    String replacement_inv_tag, replacement_pen;
    ArrayList<Breeder_Inventory> arrayListReplacementInventory = new ArrayList<>();
    ArrayList<Breeder_Inventory> arrayListReplacementInventory1 = new ArrayList<>();
    ArrayList<Breeders> arrayListReplacements = new ArrayList<>();
    ArrayList<Breeder_PhenoMorphoView>arrayList_temp = new ArrayList<>();
    ArrayList<Breeder_PhenoMorphoView>arrayListPhenoMorpho = new ArrayList<>();
    ArrayList<Integer>arrayListValuesID = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_pheno_morpho_view);
StringBuffer buffer = new StringBuffer();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                replacement_inv_tag = null;
                replacement_pen = null;
            } else {
                replacement_inv_tag = extras.getString("Replacement Tag");
                replacement_pen = extras.getString("Replacement Pen");

            }
        } else {
            replacement_inv_tag = (String) savedInstanceState.getSerializable("Replacement Tag");
            replacement_pen = (String) savedInstanceState.getSerializable("Replacement Pen");
        }




        final Bundle args = new Bundle();
        args.putString("Breeder Pen", replacement_inv_tag);
        replacement_pheno_inv_id = findViewById(R.id.replacement_pheno_inv_id);
        replacement_pheno_inv_id.setText("Phenotypic & Morphometric | " + replacement_inv_tag);
        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Breeder Phenotypic & Morpometric Records");
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        myDb = new DatabaseHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewReplacementViewPhenoMorphoRecords);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);



        //////////////////////////DATABASE

        ////inventory
       /* Cursor cursor_inventory = myDb.getDataFromBreederInvWhereTag(replacement_inv_tag);
        cursor_inventory.moveToFirst();

        if (cursor_inventory.getCount() == 0) {

        } else {
            do {


                *//*    private Integer id;brooder_inv_brooder_id           ;brooder_inv_pen;               brooder_inv_brooder_tag;        brooder_inv_batching_date;  brooder_male_quantity           ;brooder_female_quantity;       brooder_total_quantity;      brooder_inv_last_update;         brooder_inv_deleted_at;f            amily;line;generation;*//*
                Breeder_Inventory breeder_inventory = new Breeder_Inventory(cursor_inventory.getInt(0), cursor_inventory.getInt(1), cursor_inventory.getString(2),cursor_inventory.getString(3),cursor_inventory.getString(4),cursor_inventory.getInt(5), cursor_inventory.getInt(6), cursor_inventory.getInt(7), cursor_inventory.getString(8), cursor_inventory.getString(9),  null,null,null);
                arrayListReplacementInventory.add(breeder_inventory);
            } while (cursor_inventory.moveToNext());
        }


        ///PHENO MORPHO RECORDS

        ///DITO
        Cursor cursor_pheno_morpho = myDb.getAllDataFromPhenoMorphoRecords();
        cursor_pheno_morpho.moveToFirst();

        if(cursor_pheno_morpho.getCount()==0){
            Toast.makeText(this,"No data from pheno morpho records", Toast.LENGTH_SHORT).show();
        }else{
            do{
                //  private Integer id;    private Integer inv_id;    private String date;    private String gender;    private String tag;    private String pheno_record;    private String morpho_record;


                //Breeder_PhenoMorphoView replacement_phenoMorphoView = new Breeder_PhenoMorphoView(cursor_pheno_morpho.getInt(0), cursor_pheno_morpho.getInt(1), cursor_pheno_morpho.getString(2),cursor_pheno_morpho.getString(3),cursor_pheno_morpho.getString(4),cursor_pheno_morpho.getString(5),cursor_pheno_morpho.getString(6) );
                Breeder_PhenoMorphoView replacement_phenoMorphoView = new Breeder_PhenoMorphoView(cursor_pheno_morpho.getInt(0), cursor_pheno_morpho.getString(1), cursor_pheno_morpho.getString(2),cursor_pheno_morpho.getString(3),cursor_pheno_morpho.getString(4),cursor_pheno_morpho.getString(5),cursor_pheno_morpho.getString(6) );

                arrayList_temp.add(replacement_phenoMorphoView);

            }while (cursor_pheno_morpho.moveToNext());

        }



        //kunin mo naman yung replacement inventories ng given inventory tag
        for (int j=0; j<arrayList_temp.size();j++){
            for (int i = 0; i<arrayListReplacementInventory.size();i++){
                if (arrayListReplacementInventory.get(i).getId().equals(arrayList_temp.get(j).getId())) {
                    arrayListPhenoMorpho.add(arrayList_temp.get(j));

                }
            }
        }*/
//HANGGANG DITO

        Cursor cursor_inv = myDb.getIDFromBreederInventoyWhereTag(replacement_inv_tag);
        cursor_inv.moveToFirst();
     /*   buffer.append(cursor_inv.getInt(0)+ "\n");
        showMessage("YOYO", buffer.toString());
*/
        Cursor cursor_view = myDb.getDataFromBreederPhenoMorphosWhere(cursor_inv.getInt(0));
        cursor_view.moveToFirst();

        if(cursor_view.getCount() == 0){
            Toast.makeText(this, "No records yet", Toast.LENGTH_SHORT).show();
        }else{
            do{
                arrayListValuesID.add(cursor_view.getInt(0));

            }while(cursor_view.moveToNext());
        }


        for(int i=0; i<arrayListValuesID.size();i++){
            Cursor cursor_values = myDb.getDataFromPhenoMorphoValuesWhereValuesID(arrayListValuesID.get(i));
            cursor_values.moveToFirst();

            Breeder_PhenoMorphoView breeder_phenoMorphoView = new Breeder_PhenoMorphoView(cursor_values.getInt(0), cursor_values.getString(1), cursor_values.getString(2),cursor_values.getString(3),cursor_values.getString(4),cursor_values.getString(5),cursor_values.getString(6) );
            arrayListPhenoMorpho.add(breeder_phenoMorphoView);
        }



/*
        for (int i=0;i<arrayList_temp.size();i++){
            buffer.append(arrayList_temp.get(i).getPheno_morpho_inv_id());
        }
        buffer.append("\n\n\n\n");

        for (int i=0;i<arrayListReplacementInventory.size();i++){
            buffer.append(arrayListReplacementInventory.get(i).getId());
        }
        buffer.append("\n\n\n\n");

        for (int i=0;i<arrayListPhenoMorpho.size();i++){
            buffer.append(arrayListPhenoMorpho.get(i).getPheno_morpho_inv_id());
        }
*/

       // showMessage("arrayListPhenoMorpho", buffer.toString() );

        //kunin mo naman yung phenomorpho records based sa id ng brooder inventories sa arrayListReplacementInventory1

/*        for (int i=0; i<arrayList_temp.size();i++){
            for(int j=0;j<arrayListReplacementInventory1.size();j++){
                if (arrayList_temp.get(i).getPheno_morpho_inv_id().equals(arrayListReplacementInventory1.get(j).getId())){
                    arrayListPhenoMorpho.add(arrayList_temp.get(i));

                }
            }
        }*/

//hindi mo pa finifilter, binago mo kanina. dapat arrayListPhenoMorpho yung ipapasa sa recyclerview kaso di gumagana
        recycler_adapter = new RecyclerAdapter_Breeder_PhenoMorphoView(arrayListPhenoMorpho);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();














    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent_replacement_pheno_morpho_records = new Intent(BreederPhenoMorphoViewActivity.this, BreederPhenoMorphoRecordsActivity.class);
        intent_replacement_pheno_morpho_records.putExtra("Replacement Pen",replacement_pen);
        intent_replacement_pheno_morpho_records.putExtra("Breeder Tag",replacement_inv_tag);
        startActivity(intent_replacement_pheno_morpho_records);
        return true;
    }

    @Override
    public void onBackPressed() {


        Intent intent_replacement_pheno_morpho_records = new Intent(BreederPhenoMorphoViewActivity.this, BreederPhenoMorphoRecordsActivity.class);
        intent_replacement_pheno_morpho_records.putExtra("Replacement Pen",replacement_pen);
        intent_replacement_pheno_morpho_records.putExtra("Breeder Tag",replacement_inv_tag);
        startActivity(intent_replacement_pheno_morpho_records);

    }
}

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
    String replacement_inv_tag;
    Integer replacement_pen;
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
                replacement_pen = extras.getInt("Replacement Pen");

            }
        } else {
            replacement_inv_tag = (String) savedInstanceState.getSerializable("Replacement Tag");
            replacement_pen = (Integer) savedInstanceState.getSerializable("Replacement Pen");
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


        Cursor cursor_inv = myDb.getIDFromBreederInventoyWhereTag(replacement_inv_tag);
        cursor_inv.moveToFirst();

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

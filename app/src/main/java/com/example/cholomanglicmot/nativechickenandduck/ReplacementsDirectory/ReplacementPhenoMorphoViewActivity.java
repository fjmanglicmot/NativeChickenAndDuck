package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

import android.app.AlertDialog;
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

public class ReplacementPhenoMorphoViewActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView replacement_pheno_inv_id;

    DatabaseHelper myDb;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Replacement_Inventory> arrayListReplacementInventory = new ArrayList<>();
    ArrayList<Replacement_Inventory> arrayListReplacementInventory1 = new ArrayList<>();
    ArrayList<Replacements> arrayListReplacements = new ArrayList<>();
    ArrayList<Replacement_PhenoMorphoView>arrayList_temp = new ArrayList<>();
    ArrayList<Replacement_PhenoMorphoView>arrayListPhenoMorpho = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_pheno_morpho_view);
StringBuffer buffer = new StringBuffer();
        final String replacement_inv_tag, replacement_pen;
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
        args.putString("Replacement Pen", replacement_inv_tag);
        replacement_pheno_inv_id = findViewById(R.id.replacement_pheno_inv_id);
        replacement_pheno_inv_id.setText("Phenotypic & Morphometric | " + replacement_inv_tag);
        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Replacement Phenotypic & Morpometric Records");
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        myDb = new DatabaseHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewReplacementViewPhenoMorphoRecords);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);



        //////////////////////////DATABASE

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


        ///PHENO MORPHO RECORDS
        Cursor cursor_pheno_morpho = myDb.getAllDataFromPhenoMorphoRecords();
        cursor_pheno_morpho.moveToFirst();

        if(cursor_pheno_morpho.getCount()==0){
            Toast.makeText(this,"No data from pheno morpho records", Toast.LENGTH_SHORT).show();
        }else{
            do{
                /*  private Integer id;    private Integer inv_id;    private String date;    private String gender;    private String tag;    private String pheno_record;    private String morpho_record;

                 */
                Replacement_PhenoMorphoView replacement_phenoMorphoView = new Replacement_PhenoMorphoView(cursor_pheno_morpho.getInt(0), cursor_pheno_morpho.getInt(1), cursor_pheno_morpho.getString(2),cursor_pheno_morpho.getString(3),cursor_pheno_morpho.getString(4),cursor_pheno_morpho.getString(5),cursor_pheno_morpho.getString(6) );
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
        recycler_adapter = new RecyclerAdapter_Replacement_PhenoMorphoView(arrayListPhenoMorpho);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();














    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

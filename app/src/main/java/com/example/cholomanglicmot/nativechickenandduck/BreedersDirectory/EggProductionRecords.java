package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;

public class EggProductionRecords extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private ImageButton create_pen;
    private Button show_data_button;
    private Button delete_pen_table;


    DatabaseHelper myDb;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Egg_Production> arrayListBrooderGrowthRecords = new ArrayList<>();
    ArrayList<Breeder_Inventory>arrayListBrooderInventory = new ArrayList<>();
    ArrayList<Breeder_Inventory>arrayList_temp = new ArrayList<>();
    ImageButton create_egg_prod;
    TextView replacement_pheno_inv_id;
    String breeder_tag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_production_records);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                breeder_tag= null;
            } else {
                breeder_tag= extras.getString("Breeder Tag");
            }
        } else {
            breeder_tag = (String) savedInstanceState.getSerializable("Breeder Tag");
        }
        final Bundle args = new Bundle();
        args.putString("Breeder Tag",breeder_tag);

        replacement_pheno_inv_id = findViewById(R.id.replacement_pheno_inv_id);
        create_egg_prod = findViewById(R.id.open_dialog);
        replacement_pheno_inv_id.setText("Egg Production | "+ breeder_tag);
        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Breeder Egg Productions");
        getSupportActionBar().setDisplayShowHomeEnabled(true);





        create_egg_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateEggProductionDialog newFragment = new CreateEggProductionDialog();
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "CreateEggProductionDialog");
            }
        });


        myDb = new DatabaseHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewReplacementEggProductionRecords);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ///////////////////////////////DATABASE


        ////feeding records
        Cursor cursor_brooder_feeding_records = myDb.getAllDataFromEggProduction();
        cursor_brooder_feeding_records.moveToFirst();
        if(cursor_brooder_feeding_records.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {
            do {

                Egg_Production egg_production = new Egg_Production(cursor_brooder_feeding_records.getInt(0),cursor_brooder_feeding_records.getInt(1), cursor_brooder_feeding_records.getString(2), breeder_tag,cursor_brooder_feeding_records.getInt(3), cursor_brooder_feeding_records.getFloat(4), cursor_brooder_feeding_records.getInt(5), cursor_brooder_feeding_records.getInt(6), cursor_brooder_feeding_records.getString(7), cursor_brooder_feeding_records.getString(8));

                arrayListBrooderGrowthRecords.add(egg_production);

            } while (cursor_brooder_feeding_records.moveToNext());
        }



        recycler_adapter = new RecyclerAdapter_Egg_Production(arrayListBrooderGrowthRecords);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();





    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent_brooders = new Intent(EggProductionRecords.this, CreateBreeders.class);
        startActivity(intent_brooders);
        return true;
    }

    @Override
    public void onBackPressed() {


        Intent intent_brooders = new Intent(EggProductionRecords.this, CreateBreeders.class);
        startActivity(intent_brooders);

    }
}

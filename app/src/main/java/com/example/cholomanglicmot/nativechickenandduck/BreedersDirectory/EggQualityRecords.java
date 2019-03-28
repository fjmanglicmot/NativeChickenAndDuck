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

public class EggQualityRecords extends AppCompatActivity {
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
    ArrayList<Egg_Quality> arrayListBrooderGrowthRecords = new ArrayList<>();
    ArrayList<Breeder_Inventory>arrayListBrooderInventory = new ArrayList<>();
    ArrayList<Breeder_Inventory>arrayList_temp = new ArrayList<>();
    ImageButton create_egg_prod;
    TextView replacement_pheno_inv_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_quality_records);
        final String breeder_tag;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                breeder_tag= null;
            } else {
                breeder_tag= extras.getString("Breeder Tag");
            }
        } else {
            breeder_tag= (String) savedInstanceState.getSerializable("Breeder Tag");
        }
        final Bundle args = new Bundle();
        args.putString("Breeder Tag",breeder_tag);
        replacement_pheno_inv_id = findViewById(R.id.replacement_pheno_inv_id);
        replacement_pheno_inv_id.setText("Egg Quality Records | "+ breeder_tag);
        mToolbar = findViewById(R.id.nav_action);
        create_egg_prod = findViewById(R.id.open_dialog);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Breeder Egg Quality");
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        create_egg_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateEggQualityDialog newFragment = new CreateEggQualityDialog();
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "CreateEggQualityDialog");
            }
        });

        myDb = new DatabaseHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewEggQualityRecords);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ///////////////////////////////DATABASE




        ////feeding records
        Cursor cursor = myDb.getAllDataFromEggQuality();
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {
            do {
                                                            /*Integer id,Integer egg_breeder_inv_id,  String date,          Float weight,           String color,       String shape,                Float length,          Float width,    Float albument_height,  Float albument_weight,   Float yolk_weight,        String yolk_color, Float shell_weight,   Float shell_thickness_top, Float shell_thickness_middle,  Float shell_thickness_bottom*/
                Egg_Quality egg_quality = new Egg_Quality(cursor.getInt(0),breeder_tag,cursor.getInt(1), cursor.getString(2),cursor.getInt(3), cursor.getFloat(4), cursor.getString(5), cursor.getString(6), cursor.getFloat(7), cursor.getFloat(8), cursor.getFloat(9), cursor.getFloat(10), cursor.getFloat(11), cursor.getString(12), cursor.getFloat(13), cursor.getFloat(14), cursor.getFloat(15), cursor.getFloat(16));

                arrayListBrooderGrowthRecords.add(egg_quality);

            } while (cursor.moveToNext());
        }



        recycler_adapter = new RecyclerAdapter_Egg_Quality(arrayListBrooderGrowthRecords);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();




    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent_brooders = new Intent(EggQualityRecords.this, CreateBreeders.class);
        startActivity(intent_brooders);
        return true;
    }

    @Override
    public void onBackPressed() {


        Intent intent_brooders = new Intent(EggQualityRecords.this, CreateBreeders.class);
        startActivity(intent_brooders);

    }
}

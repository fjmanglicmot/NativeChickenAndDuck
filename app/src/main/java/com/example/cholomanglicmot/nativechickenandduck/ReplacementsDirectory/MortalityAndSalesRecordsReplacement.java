package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory.Mortality_Sales;
import com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory.RecyclerAdapter_Mortality_and_Sales;
import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;

public class MortalityAndSalesRecordsReplacement extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;


    DatabaseHelper myDb;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Mortality_Sales>arrayList_temp = new ArrayList<>();
    ImageButton create_egg_prod;
    TextView replacement_pheno_inv_id;
    Integer brooder_pen;
    Integer breeder_id;
    String brooder_pen_number, breeder_tag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortality_and_sales);

        final Integer breeder_id;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                breeder_tag= null;
                breeder_id=null;
                brooder_pen = null;
            } else {
                breeder_tag= extras.getString("Brooder Tag");
                breeder_id=extras.getInt("Brooder ID");
                brooder_pen = extras.getInt("Brooder Pen ID");
            }
        } else {
            breeder_tag= (String) savedInstanceState.getSerializable("Brooder Tag");
            breeder_id = (Integer) savedInstanceState.getSerializable("Brooder ID");
            brooder_pen = (Integer) savedInstanceState.getSerializable("Brooder Pen ID");
        }
        final Bundle args = new Bundle();
        args.putString("Brooder Tag",breeder_tag);
        args.putInt("Brooder ID", breeder_id);
        args.putInt("Brooder Pen ID", brooder_pen);
        replacement_pheno_inv_id = findViewById(R.id.replacement_pheno_inv_id);
        replacement_pheno_inv_id.setText("Mortality and Sales | " + breeder_tag);
        mToolbar = findViewById(R.id.nav_action);
        create_egg_prod = findViewById(R.id.open_dialog);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Mortality and Sales");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewReplacementViewPhenoMorphoRecords);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        create_egg_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateMortalityAndSalesReplacementDialog newFragment = new CreateMortalityAndSalesReplacementDialog();
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "CreateMortalityAndSalesReplacementDialog");
            }
        });



        myDb = new DatabaseHelper(this);
        ///////////////////DATABASE
        Cursor cursor = myDb.getAllDataFromMortandSalesRecords();
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data in Mortality and Sales", Toast.LENGTH_SHORT).show();
        }else{
            do{
                Integer replacement_inv_id = cursor.getInt(3);
                if(replacement_inv_id == breeder_id){
                    Mortality_Sales mortalityAndSalesRecords = new Mortality_Sales(cursor.getInt(0), cursor.getString(1),breeder_tag, cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5),cursor.getString(6), cursor.getFloat(7), cursor.getInt(8), cursor.getInt(9),cursor.getInt(10),cursor.getString(11),cursor.getString(12), cursor.getString(12));
                    arrayList_temp.add(mortalityAndSalesRecords);
                }


            }while (cursor.moveToNext());
        }

        Cursor cursor1 = myDb.getAllDataFromPenWhereID(brooder_pen);
        cursor1.moveToFirst();
        if(cursor1.getCount() != 0){
            brooder_pen_number = cursor1.getString(2);
        }

        //dapat may filter pa sa arraylist temp na dapat tung mortality and sales lang ng given breeder tag yung lalabas
        recycler_adapter = new RecyclerAdapter_Mortality_and_Sales(arrayList_temp);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();
        Toast.makeText(this, brooder_pen_number, Toast.LENGTH_SHORT).show();



    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent_brooders = new Intent(MortalityAndSalesRecordsReplacement.this, ReplacementInventoryActivity.class);
        intent_brooders.putExtra("Replacement Pen",brooder_pen_number);

        intent_brooders.putExtra("Replacement Tag",breeder_tag);
        intent_brooders.putExtra("Replacement ID",breeder_id);
        startActivity(intent_brooders);
        return true;
    }

    @Override
    public void onBackPressed() {


        Intent intent_brooders = new Intent(MortalityAndSalesRecordsReplacement.this, ReplacementInventoryActivity.class);
        intent_brooders.putExtra("Replacement Pen",brooder_pen_number);
        intent_brooders.putExtra("Replacement Tag",breeder_tag);
        intent_brooders.putExtra("Replacement ID",breeder_id);
        startActivity(intent_brooders);

    }
}

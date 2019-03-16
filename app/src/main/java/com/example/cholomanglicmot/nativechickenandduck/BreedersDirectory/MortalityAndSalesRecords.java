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

public class MortalityAndSalesRecords extends AppCompatActivity {
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
    ArrayList<Mortality_Sales>arrayList_temp = new ArrayList<>();
    ImageButton create_egg_prod;
    TextView replacement_pheno_inv_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortality_and_sales);
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
                CreateMortalityAndSalesDialog newFragment = new CreateMortalityAndSalesDialog();
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "CreateMortalityAndSalesDialog");
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

                                                                                            /*nteger id, String date, Integer breeder_id, Integer replaement_id, Integer brooder_id,         String type, S       tring category,       Integer price,      Integer male_count, Integer female_count, Integer total, String reason, String remarks, String deleted_at){
                 */
                Mortality_Sales mortalityAndSalesRecords = new Mortality_Sales(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5),cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9),cursor.getInt(10),cursor.getString(11),cursor.getString(12), cursor.getString(12));
                arrayList_temp.add(mortalityAndSalesRecords);
            }while (cursor.moveToNext());
        }

        recycler_adapter = new RecyclerAdapter_Mortality_and_Sales(arrayList_temp);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();



    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent_brooders = new Intent(MortalityAndSalesRecords.this, CreateBreeders.class);
        startActivity(intent_brooders);
        return true;
    }

    @Override
    public void onBackPressed() {


        Intent intent_brooders = new Intent(MortalityAndSalesRecords.this, CreateBreeders.class);
        startActivity(intent_brooders);

    }
}

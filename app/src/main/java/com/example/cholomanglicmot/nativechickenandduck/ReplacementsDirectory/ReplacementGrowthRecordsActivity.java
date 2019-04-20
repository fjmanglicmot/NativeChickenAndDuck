package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.APIHelper;
import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;
import com.google.gson.Gson;
import com.loopj.android.http.BaseJsonHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class ReplacementGrowthRecordsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView brooder_pen_textView;


    DatabaseHelper myDb;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Replacement_GrowthRecords> arrayListReplacementGrowthRecords = new ArrayList<>();
    ArrayList<Replacement_Inventory>arrayListReplacementInventory = new ArrayList<>();
    ArrayList<Replacement_Inventory>arrayListReplacementInventory1 = new ArrayList<>();
    ArrayList<Replacement_Inventory>arrayList_temp = new ArrayList<>();
    ImageButton create_brooder_feeding_records;
    Integer replacement_pen_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_growth_records);
        final String replacement_pen;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                replacement_pen = null;
            } else {
                replacement_pen = extras.getString("Replacement Pen");
            }
        } else {
            replacement_pen = (String) savedInstanceState.getSerializable("Replacement Pen");
        }

        final Bundle args = new Bundle();
        args.putString("Replacement Pen", replacement_pen);
        brooder_pen_textView = findViewById(R.id.brooder_pen);
        brooder_pen_textView.setText("Replacement Pen " + replacement_pen);
        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Replacement Growth Records");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        create_brooder_feeding_records = findViewById(R.id.open_dialog);

        create_brooder_feeding_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateReplacementGrowthRecordDialog newFragment = new CreateReplacementGrowthRecordDialog();
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "CreateReplacementGrowthRecordDialog");
            }
        });

        myDb = new DatabaseHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewReplacementGrowthRecords);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ///////////////////////////////DATABASE

        Cursor cursor =myDb.getAllDataFromPenWhere(replacement_pen);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            replacement_pen_id = cursor.getInt(0);
        }


        boolean isNetworkAvailable = isNetworkAvailable();
        if (isNetworkAvailable) {
            //if internet is available, load data from web database


            //HARDCODED KASI WALA KA PANG DATABASE NA NANDUN EMAIL MO

            API_getReplacementGrowth();


        }
        Cursor cursor_brooder_inventory = myDb.getAllDataFromReplacementInventory(); //para sa pagstore ng data sa arraylist
        cursor_brooder_inventory.moveToFirst();
        if(cursor_brooder_inventory.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {
            do {

                Replacement_Inventory replacement_inventory = new Replacement_Inventory(cursor_brooder_inventory.getInt(0),cursor_brooder_inventory.getInt(1), cursor_brooder_inventory.getInt(2), cursor_brooder_inventory.getString(3),cursor_brooder_inventory.getString(4), cursor_brooder_inventory.getInt(5), cursor_brooder_inventory.getInt(6),cursor_brooder_inventory.getInt(7), cursor_brooder_inventory.getString(8), cursor_brooder_inventory.getString(9));
                arrayListReplacementInventory.add(replacement_inventory);


            } while (cursor_brooder_inventory.moveToNext());
        }



        for (int i=0;i<arrayListReplacementInventory.size();i++){
            if(arrayListReplacementInventory.get(i).getReplacement_inv_pen() == replacement_pen_id ){

                arrayList_temp.add(arrayListReplacementInventory.get(i)); //ito na yung list ng inventory na nasa pen

            }
        }




        ////growth records

        Cursor cursor_brooder_growth_records = myDb.getAllDataFromReplacementGrowthRecords();
        cursor_brooder_growth_records.moveToFirst();

        if(cursor_brooder_growth_records.getCount() == 0){
            //show message
            // Toast.makeText(this,"No dataKHBSDF.", Toast.LENGTH_LONG).show();

        }else {

            do {
                //                                                                        Integer id,                 Integer brooder_growth_inventory_id,String , Integer brooder_growth_collection_day,      String brooder_growth_date_collected,       Integer brooder_growth_male_quantity,           Float brooder_growth_male_weight,                  Integer brooder_growth_female_quantity, Float brooder_growth_female_weight,          Integer brooder_growth_total_quantity,      Float brooder_growth_total_weight,              String brooder_growth_deleted_at){
                for(int k=0;k<arrayList_temp.size();k++){
                    if(arrayList_temp.get(k).getReplacement_inv_replacement_id().equals(cursor_brooder_growth_records.getInt(1))){
                        Replacement_GrowthRecords replacement_growthRecords = new Replacement_GrowthRecords(cursor_brooder_growth_records.getInt(0),cursor_brooder_growth_records.getInt(1),arrayList_temp.get(k).getReplacement_inv_replacement_tag(),cursor_brooder_growth_records.getInt(2), cursor_brooder_growth_records.getString(3),cursor_brooder_growth_records.getInt(4), cursor_brooder_growth_records.getFloat(5), cursor_brooder_growth_records.getInt(6), cursor_brooder_growth_records.getFloat(7),cursor_brooder_growth_records.getInt(8), cursor_brooder_growth_records.getFloat(9), cursor_brooder_growth_records.getString(10));
                        arrayListReplacementGrowthRecords.add(replacement_growthRecords);


                    }
                }


            } while (cursor_brooder_growth_records.moveToNext());
        }

        recycler_adapter = new RecyclerAdapter_Replacement_Growth(arrayListReplacementGrowthRecords);
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
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void API_getReplacementGrowth(){
        APIHelper.getReplacementGrowth("getReplacementGrowth/", new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){


                Gson gson = new Gson();
                JSONReplacementGrowth jsonBrooderInventory = gson.fromJson(rawJsonResponse, JSONReplacementGrowth.class);
                ArrayList<Replacement_GrowthRecords> arrayList_brooderInventory = jsonBrooderInventory.getData();


                for (int i = 0; i < arrayList_brooderInventory.size(); i++) {
                    //check if generation to be inserted is already in the database
                    Cursor cursor = myDb.getAllDataFromReplacementGrowthRecordsWhereGrowthID(arrayList_brooderInventory.get(i).getId());
                    cursor.moveToFirst();

                    if (cursor.getCount() == 0) {


                        boolean isInserted = myDb.insertDataBrooderGrowthRecordsWithID(arrayList_brooderInventory.get(i).getId(), arrayList_brooderInventory.get(i).getReplacement_growth_inventory_id(), arrayList_brooderInventory.get(i).getReplacement_growth_collection_day(), arrayList_brooderInventory.get(i).getReplacement_growth_date_collected(),arrayList_brooderInventory.get(i).getReplacement_growth_male_quantity(),arrayList_brooderInventory.get(i).getReplacement_growth_male_weight(),arrayList_brooderInventory.get(i).getReplacement_growth_female_quantity(),arrayList_brooderInventory.get(i).getReplacement_growth_female_weight(),arrayList_brooderInventory.get(i).getReplacement_growth_total_quantity(),arrayList_brooderInventory.get(i).getReplacement_growth_total_weight(),arrayList_brooderInventory.get(i).getReplacement_growth_deleted_at());
                        //Toast.makeText(BrooderInventoryActivity.this, "oyoyooyoy", Toast.LENGTH_SHORT).show();
                    }

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonResponse, Object response){

                Toast.makeText(getApplicationContext(), "Failed to fetch Brooders Inventory from web database ", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable{
                return null;
            }
        });
    }

}

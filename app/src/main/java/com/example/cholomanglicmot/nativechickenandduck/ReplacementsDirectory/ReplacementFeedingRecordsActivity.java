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


public class ReplacementFeedingRecordsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView replacement_pen_textView;
    Integer replacement_pen_id;


    DatabaseHelper myDb;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Replacement_Inventory> arrayList_temp = new ArrayList<>();//create constructor first for brooder feeding records
    ArrayList<Replacement_FeedingRecords> arrayListReplacementFeedingRecords = new ArrayList<>();
    ArrayList<Replacement_Inventory> arrayListReplacementInventory = new ArrayList<>();
    ArrayList<Replacement_Inventory> arrayListReplacementInventory1 = new ArrayList<>();
    ArrayList<Replacements>arrayListReplacements = new ArrayList<>();
    ImageButton create_replacement_feeding_records;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_feeding_records);
        final String replacement_pen;
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

        final Bundle args = new Bundle();
        args.putString("Replacement Pen",replacement_pen);
        StringBuffer buffer = new StringBuffer();

        replacement_pen_textView = findViewById(R.id.replacement_pen);
        replacement_pen_textView.setText("Replacement Pen " +replacement_pen);
        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Replacement Feeding Records");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        create_replacement_feeding_records = findViewById(R.id.open_dialog);

        create_replacement_feeding_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateReplacementFeedingRecordDialog newFragment = new CreateReplacementFeedingRecordDialog();
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "CreateReplacementFeedingRecordDialog");
            }
        });

        myDb = new DatabaseHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewReplacementFeedingRecords);
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




            API_getReplacementFeeding();


        }



        Cursor cursor_replacement_inv = myDb.getAllDataFromReplacementInventory(); //para sa pagstore ng data sa arraylist
        cursor_replacement_inv.moveToFirst();
        if(cursor_replacement_inv.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {
            do {

                Replacement_Inventory replacement_inventory = new Replacement_Inventory(cursor_replacement_inv.getInt(0),cursor_replacement_inv.getInt(1), cursor_replacement_inv.getInt(2), cursor_replacement_inv.getString(3),cursor_replacement_inv.getString(4), cursor_replacement_inv.getInt(5), cursor_replacement_inv.getInt(6),cursor_replacement_inv.getInt(7), cursor_replacement_inv.getString(8), cursor_replacement_inv.getString(9));
                arrayListReplacementInventory.add(replacement_inventory);
            } while (cursor_replacement_inv.moveToNext());
        }



        for (int i=0;i<arrayListReplacementInventory.size();i++){
            if(arrayListReplacementInventory.get(i).getReplacement_inv_pen() == replacement_pen_id) {

                arrayList_temp.add(arrayListReplacementInventory.get(i)); //ito na yung list ng inventory na nasa pen

            }
        }


        Cursor cursor_feeding = myDb.getAllDataFromReplacementFeedingRecords();
        cursor_feeding.moveToFirst();

        if(cursor_feeding.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {

            do {
                //                                                                        Integer id,                 Integer brooder_growth_inventory_id,String , Integer brooder_growth_collection_day,      String brooder_growth_date_collected,       Integer brooder_growth_male_quantity,           Float brooder_growth_male_weight,                  Integer brooder_growth_female_quantity, Float brooder_growth_female_weight,          Integer brooder_growth_total_quantity,      Float brooder_growth_total_weight,              String brooder_growth_deleted_at){
                for(int k=0;k<arrayList_temp.size();k++){
                    if(arrayList_temp.get(k).getReplacement_inv_replacement_id() == cursor_feeding.getInt(1)){
                        Replacement_FeedingRecords replacement_feedingRecords = new Replacement_FeedingRecords(cursor_feeding.getInt(0),cursor_feeding.getInt(1), cursor_feeding.getString(2), arrayList_temp.get(k).getReplacement_inv_replacement_tag(),cursor_feeding.getFloat(3),cursor_feeding.getFloat(4), cursor_feeding.getString(5), cursor_feeding.getString(6));

                        arrayListReplacementFeedingRecords.add(replacement_feedingRecords);


                    }
                }


            } while (cursor_feeding.moveToNext());
        }

        recycler_adapter = new RecyclerAdapter_Replacement_Feeding(arrayListReplacementFeedingRecords);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();



    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void API_getReplacementFeeding(){
        APIHelper.getReplacementFeeding("getReplacementFeeding/", new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){


                Gson gson = new Gson();
                JSONReplacementFeeding jsonBrooderInventory = gson.fromJson(rawJsonResponse, JSONReplacementFeeding.class);
                ArrayList<Replacement_FeedingRecords> arrayList_brooderInventory = jsonBrooderInventory.getData();


                for (int i = 0; i < arrayList_brooderInventory.size(); i++) {
                    //check if generation to be inserted is already in the database
                    Cursor cursor = myDb.getAllDataFromReplacementFeedingRecordsWhereFeedingID(arrayList_brooderInventory.get(i).getId());
                    cursor.moveToFirst();

                    if (cursor.getCount() == 0) {


                        boolean isInserted = myDb.insertDataReplacementFeedingRecordsWithID(arrayList_brooderInventory.get(i).getId(), arrayList_brooderInventory.get(i).getReplacement_feeding_inventory_id(), arrayList_brooderInventory.get(i).getReplacement_feeding_date_collected(), arrayList_brooderInventory.get(i).getReplacement_feeding_offered(),arrayList_brooderInventory.get(i).getReplacement_feeding_refused(),arrayList_brooderInventory.get(i).getReplacement_feeding_remarks(),arrayList_brooderInventory.get(i).getReplacement_feeding_deleted_at());

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

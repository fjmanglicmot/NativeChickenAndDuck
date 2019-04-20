package com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;


public class BrooderFeedingRecordsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView brooder_pen_textView;


    DatabaseHelper myDb;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Brooder_FeedingRecords> arrayListBrooderFeedingRecords = new ArrayList<>();//create constructor first for brooder feeding records
    ArrayList<Brooder_FeedingRecords> arrayListBrooderFeedingRecords2 = new ArrayList<>();//create constructor first for brooder feeding records
    ArrayList<Brooder_Inventory>arrayListBrooderInventory = new ArrayList<>();
    ArrayList<Brooder_Inventory>arrayList_temp = new ArrayList<>();
    ArrayList<Brooder_Inventory>arrayList_temp2 = new ArrayList<>();
    ImageButton create_brooder_feeding_records;
    Integer pen_id , brooder_pen_id;
    String brooder_pen;

    Map<Integer, Integer> inventory_dictionary = new HashMap<Integer, Integer>();
    ArrayList<Integer> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brooder_feeding_records);

        final Integer brooder_inv_id;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                brooder_pen= null;
                brooder_inv_id = null;
            } else {
                brooder_pen= extras.getString("Brooder Pen");
                brooder_inv_id = extras.getInt("Brooder Inventory ID");
            }
        } else {
            brooder_pen= (String) savedInstanceState.getSerializable("Brooder Pen");
            brooder_inv_id =(Integer) savedInstanceState.getSerializable("Brooder Inventory ID");
        }

        final Bundle args = new Bundle();
        args.putString("brooder pen number",brooder_pen);
        brooder_pen_textView = findViewById(R.id.brooder_pen);
        brooder_pen_textView.setText("Brooder Pen " +brooder_pen);
        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Brooder Feeding Records");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        create_brooder_feeding_records = findViewById(R.id.open_dialog);

        create_brooder_feeding_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateBrooderFeedingRecordDialog newFragment = new CreateBrooderFeedingRecordDialog();
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "CreateBrooderFeedingRecordDialog");
            }
        });



        myDb = new DatabaseHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewBrooderFeedingRecords);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ///////////////////////////////DATABASE


        Cursor cursor = myDb.getAllDataFromPenWhere(brooder_pen);
        cursor.moveToFirst();
        if(cursor.getCount()!=0){
            pen_id = cursor.getInt(0);
        }

        boolean isNetworkAvailable = isNetworkAvailable();
        if (isNetworkAvailable) {
            //if internet is available, load data from web database




            API_getBrooderFeeding();


        }
            Cursor cursor_brooder_inventory = myDb.getAllDataFromBrooderInventory(); //para sa pagstore ng data sa arraylist
            cursor_brooder_inventory.moveToFirst();
            if(cursor_brooder_inventory.getCount() == 0){
                //show message
                Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

            }else {
                do {

                    Brooder_Inventory brooder_inventory = new Brooder_Inventory(cursor_brooder_inventory.getInt(0),cursor_brooder_inventory.getInt(1), cursor_brooder_inventory.getInt(2), cursor_brooder_inventory.getString(3),cursor_brooder_inventory.getString(4), cursor_brooder_inventory.getInt(5), cursor_brooder_inventory.getInt(6),cursor_brooder_inventory.getInt(7), cursor_brooder_inventory.getString(8), cursor_brooder_inventory.getString(9));
                    arrayListBrooderInventory.add(brooder_inventory);


                } while (cursor_brooder_inventory.moveToNext());
            }



            for (int i=0;i<arrayListBrooderInventory.size();i++){
                if(arrayListBrooderInventory.get(i).getBrooder_inv_pen() == pen_id) {

                    arrayList_temp.add(arrayListBrooderInventory.get(i)); //ito na yung list ng inventory na nasa pen

                }
            }


            Cursor cursor_feeding = myDb.getAllDataFromBrooderFeedingRecords();
            cursor_feeding.moveToFirst();

            if(cursor_feeding.getCount() == 0){
                //show message
                Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

            }else {

                do {
                    //                                                                        Integer id,                 Integer brooder_growth_inventory_id,String , Integer brooder_growth_collection_day,      String brooder_growth_date_collected,       Integer brooder_growth_male_quantity,           Float brooder_growth_male_weight,                  Integer brooder_growth_female_quantity, Float brooder_growth_female_weight,          Integer brooder_growth_total_quantity,      Float brooder_growth_total_weight,              String brooder_growth_deleted_at){
                    for(int k=0;k<arrayList_temp.size();k++){
                        if(arrayList_temp.get(k).getBrooder_inv_brooder_id() == cursor_feeding.getInt(1)){
                            Brooder_FeedingRecords brooderFeedingRecords = new Brooder_FeedingRecords(cursor_feeding.getInt(0),cursor_feeding.getInt(1), cursor_feeding.getString(2), arrayList_temp.get(k).getBrooder_inv_brooder_tag(),cursor_feeding.getFloat(3),cursor_feeding.getFloat(4), cursor_feeding.getString(5), cursor_feeding.getString(6));

                            arrayListBrooderFeedingRecords.add(brooderFeedingRecords);


                        }
                    }


                } while (cursor_feeding.moveToNext());
            }



            recycler_adapter = new RecyclerAdapter_Brooder_Feeding(arrayListBrooderFeedingRecords);
            recyclerView.setAdapter(recycler_adapter);
            recycler_adapter.notifyDataSetChanged();







    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void API_getBrooderFeeding(){
        APIHelper.getBrooderFeeding("getBrooderFeeding/", new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){

                Gson gson = new Gson();
                JSONBrooderFeeding jsonBrooderInventory = gson.fromJson(rawJsonResponse, JSONBrooderFeeding.class);
                ArrayList<Brooder_FeedingRecords> arrayList_brooderInventory = jsonBrooderInventory.getData();


                for (int i = 0; i < arrayList_brooderInventory.size(); i++) {
                    //check if generation to be inserted is already in the database
                    Cursor cursor = myDb.getAllDataFromBrooderFeedingRecordsWhereFeedingID(arrayList_brooderInventory.get(i).getId());
                    cursor.moveToFirst();

                    if (cursor.getCount() == 0) {


                        boolean isInserted = myDb.insertDataBrooderFeedingRecordsWithID(arrayList_brooderInventory.get(i).getId(), arrayList_brooderInventory.get(i).getBrooder_feeding_inventory_id(), arrayList_brooderInventory.get(i).getBrooder_feeding_date_collected(), arrayList_brooderInventory.get(i).getBrooder_feeding_offered(),arrayList_brooderInventory.get(i).getBrooder_feeding_refused(),arrayList_brooderInventory.get(i).getBrooder_feeding_remarks(),arrayList_brooderInventory.get(i).getBrooder_feeding_deleted_at());

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
    public void onBackPressed() {

        Intent intent_brooders = new Intent(this, CreateBrooders.class);
        startActivity(intent_brooders);

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

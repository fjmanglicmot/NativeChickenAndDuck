package com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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

    Map<Integer, Integer> inventory_dictionary = new HashMap<Integer, Integer>();
    ArrayList<Integer> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brooder_feeding_records);
        final String brooder_pen;
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

        Cursor cursor_brooder_inventory = myDb.getAllDataFromBrooderInventory(); //para sa pagstore ng data sa arraylist
        cursor_brooder_inventory.moveToFirst();
        if(cursor_brooder_inventory.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {
            do {

                Brooder_Inventory brooder_inventory = new Brooder_Inventory(cursor_brooder_inventory.getInt(0),cursor_brooder_inventory.getInt(1), cursor_brooder_inventory.getString(2), cursor_brooder_inventory.getString(3),cursor_brooder_inventory.getString(4), cursor_brooder_inventory.getInt(5), cursor_brooder_inventory.getInt(6),cursor_brooder_inventory.getInt(7), cursor_brooder_inventory.getString(8), cursor_brooder_inventory.getString(9));
                arrayListBrooderInventory.add(brooder_inventory);


            } while (cursor_brooder_inventory.moveToNext());
        }



        for (int i=0;i<arrayListBrooderInventory.size();i++){
            if(arrayListBrooderInventory.get(i).getBrooder_inv_pen().equals(brooder_pen) ){

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
                    if(arrayList_temp.get(k).getBrooder_inv_brooder_id().equals(cursor_feeding.getInt(1))){
                        Brooder_FeedingRecords brooderFeedingRecords = new Brooder_FeedingRecords(cursor_feeding.getInt(0),cursor_feeding.getInt(1), cursor_feeding.getString(2), arrayList_temp.get(k).getBrooder_inv_brooder_tag(),cursor_feeding.getFloat(3),cursor_feeding.getFloat(4), cursor_feeding.getString(5), cursor_feeding.getString(6));

                         arrayListBrooderFeedingRecords.add(brooderFeedingRecords);


                    }
                }


            } while (cursor_feeding.moveToNext());
        }


/////////////////////////////////////////////////////
/*
                    ////feeding records
        Cursor cursor_brooder_feeding_records = myDb.getAllDataFromBrooderFeedingRecords();
        cursor_brooder_feeding_records.moveToFirst();
        if(cursor_brooder_feeding_records.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {
            do {
                Cursor cursor = myDb.getTagFromBrooderInventory(cursor_brooder_feeding_records.getInt(1));
                cursor.moveToFirst();
                if(cursor.getCount() != 0){
                    Brooder_FeedingRecords brooderFeedingRecords = new Brooder_FeedingRecords(cursor_brooder_feeding_records.getInt(0),cursor_brooder_feeding_records.getInt(1), cursor_brooder_feeding_records.getString(2), cursor.getString(0),cursor_brooder_feeding_records.getFloat(3),cursor_brooder_feeding_records.getFloat(4), cursor_brooder_feeding_records.getString(5), cursor_brooder_feeding_records.getString(6));
                    arrayListBrooderFeedingRecords.add(brooderFeedingRecords);
                }


            } while (cursor_brooder_feeding_records.moveToNext());
        }



        ///////////////

        Cursor cursorInventory = myDb.getAllDataFromBrooderInventory();
        cursorInventory.moveToFirst();
        if(cursorInventory.getCount() != 0){
            do{

                Brooder_Inventory brooder_inventory= new Brooder_Inventory(cursorInventory.getInt(0), cursorInventory.getInt(1), cursorInventory.getString(2), cursorInventory.getString(3), cursorInventory.getString(4),cursorInventory.getInt(5), cursorInventory.getInt(6), cursorInventory.getInt(7),cursorInventory.getString(8), cursorInventory.getString(9));
                arrayList_temp.add(brooder_inventory);
            }while(cursorInventory.moveToNext());

        }

        ///kunin yung mga inventory na nasa specific pen

        for (int i=0;i<arrayList_temp.size();i++){
            if(arrayList_temp.get(i).getBrooder_inv_pen().equals(brooder_pen) ){

                arrayList_temp2.add(arrayList_temp.get(i)); //ito na yung list ng inventory na nasa pen

            }
        }


        for(int i = 0; i< arrayList_temp2.size();i++){
            for(int j =0; j<arrayListBrooderFeedingRecords.size();j++){
                if (arrayListBrooderFeedingRecords.get(j).getBrooder_feeding_inventory_id().equals(arrayList_temp2.get(i).getId())){
                    arrayListBrooderFeedingRecords2.add(arrayListBrooderFeedingRecords.get(j));
                }
            }
        }*/

/////////////////////////////////////////////////

/*       Cursor cursor_feeding = myDb.getAllDataFromBroodersFeedingWhereBrooderInv(brooder_inv_id);
       cursor_feeding.moveToFirst();
       if(cursor_feeding.getCount() != 0){
           do{
               Cursor cursor = myDb.getTagFromBrooderInventory(cursor_feeding.getInt(1));
               cursor.moveToFirst();
               if(cursor.getCount() != 0){
                   Brooder_FeedingRecords brooderFeedingRecords = new Brooder_FeedingRecords(cursor_feeding.getInt(0),cursor_feeding.getInt(1), cursor_feeding.getString(2), cursor.getString(0),cursor_feeding.getFloat(3),cursor_feeding.getFloat(4), cursor_feeding.getString(5), cursor_feeding.getString(6));
                   arrayListBrooderFeedingRecords.add(brooderFeedingRecords);
               }
           }while(cursor_feeding.moveToNext());

       }else{
           Toast.makeText(this, "WALA", Toast.LENGTH_SHORT).show();
       }*/

        recycler_adapter = new RecyclerAdapter_Brooder_Feeding(arrayListBrooderFeedingRecords);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();



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

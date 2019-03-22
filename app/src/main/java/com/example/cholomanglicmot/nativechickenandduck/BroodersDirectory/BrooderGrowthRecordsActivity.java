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


public class BrooderGrowthRecordsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView brooder_pen_textView;


    DatabaseHelper myDb;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Brooder_GrowthRecords> arrayListBrooderGrowthRecords = new ArrayList<>();
    ArrayList<Brooder_Inventory>arrayListBrooderInventory = new ArrayList<>();
    ArrayList<Brooder_Inventory>arrayList_temp = new ArrayList<>();
    ImageButton create_brooder_feeding_records;

    Map<Integer, Integer> inventory_dictionary = new HashMap<Integer, Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brooder_growth_records);
        final String brooder_pen;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                brooder_pen= null;
            } else {
                brooder_pen= extras.getString("Brooder Pen");
            }
        } else {
            brooder_pen= (String) savedInstanceState.getSerializable("Brooder Pen");
        }

        final Bundle args = new Bundle();
        args.putString("Brooder Pen",brooder_pen);
        brooder_pen_textView = findViewById(R.id.brooder_pen);
        brooder_pen_textView.setText("Brooder Pen " +brooder_pen);
        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Brooder Growth Records");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        create_brooder_feeding_records = findViewById(R.id.open_dialog);

        create_brooder_feeding_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateBrooderGrowthRecordDialog newFragment = new CreateBrooderGrowthRecordDialog();
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "CreateBrooderGrowthRecordDialog");
            }
        });

        myDb = new DatabaseHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewBrooderFeedingRecords);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ///////////////////////////////DATABASE







                ////inventory
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



/*
        int count_inventory =0;

        for(int i=0;i<arrayList_temp.size();i++){
            count_inventory = count_inventory+arrayList_temp.get(i).getBrooder_total_quantity();
        }

        ArrayList<Integer>arrayListBrooder = new ArrayList<>();
        for(int i = 0;i<arrayList_temp.size();i++){
            if(arrayListBrooder.contains(arrayList_temp.get(i).getBrooder_inv_brooder_id())){
                //do nothing
            }else{
                arrayListBrooder.add(arrayList_temp.get(i).getBrooder_inv_brooder_id());
            }
        }

        for(int i = 0;i<arrayListBrooder.size();i++){ //initialize to zero yung values
            inventory_dictionary.put(arrayListBrooder.get(i),0); //pero yung key ay ok na
        }

        //gets the total of each brooder inventory
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i=0;i<arrayList_temp.size();i++){
            if(inventory_dictionary.get(arrayList_temp.get(i).getBrooder_inv_brooder_id()) != null && !arrayList.contains(arrayList_temp.get(i).getBrooder_inv_brooder_id())){
                inventory_dictionary.put(arrayList_temp.get(i).getBrooder_inv_brooder_id(),arrayList_temp.get(i).getBrooder_total_quantity());
                arrayList.add(arrayList_temp.get(i).getBrooder_inv_brooder_id());
            }else if(arrayList.contains(arrayList_temp.get(i).getBrooder_inv_brooder_id())){
                inventory_dictionary.put(arrayList_temp.get(i).getBrooder_inv_brooder_id(),inventory_dictionary.get(arrayList_temp.get(i).getBrooder_inv_brooder_id())+arrayList_temp.get(i).getBrooder_total_quantity());
            }
        }*/

        ////growth records

        Cursor cursor_brooder_growth_records = myDb.getAllDataFromBrooderGrowthRecords();
        cursor_brooder_growth_records.moveToFirst();

        if(cursor_brooder_growth_records.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {

            do {
                //                                                                        Integer id,                 Integer brooder_growth_inventory_id,String , Integer brooder_growth_collection_day,      String brooder_growth_date_collected,       Integer brooder_growth_male_quantity,           Float brooder_growth_male_weight,                  Integer brooder_growth_female_quantity, Float brooder_growth_female_weight,          Integer brooder_growth_total_quantity,      Float brooder_growth_total_weight,              String brooder_growth_deleted_at){
                for(int k=0;k<arrayList_temp.size();k++){
                    if(arrayList_temp.get(k).getBrooder_inv_brooder_id().equals(cursor_brooder_growth_records.getInt(1))){
                        Brooder_GrowthRecords brooderGrowthRecords = new Brooder_GrowthRecords(cursor_brooder_growth_records.getInt(0),cursor_brooder_growth_records.getInt(1),arrayList_temp.get(k).getBrooder_inv_brooder_tag(),cursor_brooder_growth_records.getInt(2), cursor_brooder_growth_records.getString(3),cursor_brooder_growth_records.getInt(4), cursor_brooder_growth_records.getFloat(5), cursor_brooder_growth_records.getInt(6), cursor_brooder_growth_records.getFloat(7),cursor_brooder_growth_records.getInt(8), cursor_brooder_growth_records.getFloat(9), cursor_brooder_growth_records.getString(10));
                        arrayListBrooderGrowthRecords.add(brooderGrowthRecords);


                    }
                }


            } while (cursor_brooder_growth_records.moveToNext());
        }




             /*   for(int i = 0;i<arrayList_temp.size();i++){
                    buffer.append("arrayList_temp inv id "+arrayList_temp.get(i).getBrooder_inv_brooder_id().toString()+"\n\n");
                }*/

           // showMessage("count_inventory", buffer.toString());



                                                                //pass dictionary   total count ng brooders arraylistngfeedingrecords
        recycler_adapter = new RecyclerAdapter_Brooder_Growth(arrayListBrooderGrowthRecords);
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

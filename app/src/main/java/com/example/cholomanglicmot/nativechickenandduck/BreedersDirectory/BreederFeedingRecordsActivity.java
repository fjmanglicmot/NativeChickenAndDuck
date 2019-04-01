package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

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


public class BreederFeedingRecordsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView brooder_pen_textView;


    DatabaseHelper myDb;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Breeder_FeedingRecords> arrayListBrooderFeedingRecords = new ArrayList<>();//create constructor first for brooder feeding records
    ArrayList<Breeder_Inventory>arrayListBrooderInventory = new ArrayList<>();
    ArrayList<Breeder_Inventory>arrayList_temp = new ArrayList<>();
    ImageButton create_brooder_feeding_records;

    Map<Integer, Integer> inventory_dictionary = new HashMap<Integer, Integer>();
    ArrayList<Integer> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_feeding_records);
        final String brooder_pen, breeder_tag;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                brooder_pen= null;
                breeder_tag = null;
            } else {
                brooder_pen= extras.getString("Breeder Pen");
                breeder_tag= extras.getString("Breeder Tag");
            }
        } else {
            brooder_pen= (String) savedInstanceState.getSerializable("Breeder Pen");
            breeder_tag= (String) savedInstanceState.getSerializable("Breeder Tag");
        }

        final Bundle args = new Bundle();
        args.putString("breeder pen number",brooder_pen);
        args.putString("Breeder Tag", breeder_tag);
        brooder_pen_textView = findViewById(R.id.brooder_pen);
        brooder_pen_textView.setText("Breeder Tag | " +breeder_tag);
        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Breeder Feeding Records");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        create_brooder_feeding_records = findViewById(R.id.open_dialog);

        create_brooder_feeding_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateBreederFeedingRecordDialog newFragment = new CreateBreederFeedingRecordDialog();
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "CreateBreederFeedingRecordDialog");
            }
        });

        myDb = new DatabaseHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewBrooderFeedingRecords);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ///////////////////////////////DATABASE


        Cursor cursor_brooder_inventory = myDb.getAllDataFromBreederInventory(); //para sa pagstore ng data sa arraylist
        cursor_brooder_inventory.moveToFirst();
        if(cursor_brooder_inventory.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {
            do {

                Breeder_Inventory brooder_inventory = new Breeder_Inventory(cursor_brooder_inventory.getInt(0),cursor_brooder_inventory.getInt(1), cursor_brooder_inventory.getString(2), cursor_brooder_inventory.getString(3),cursor_brooder_inventory.getString(4), cursor_brooder_inventory.getInt(5), cursor_brooder_inventory.getInt(6),cursor_brooder_inventory.getInt(7), cursor_brooder_inventory.getString(8), cursor_brooder_inventory.getString(9));
                arrayListBrooderInventory.add(brooder_inventory);


            } while (cursor_brooder_inventory.moveToNext());
        }



        for (int i=0;i<arrayListBrooderInventory.size();i++){
            if(arrayListBrooderInventory.get(i).getBrooder_inv_pen().equals(brooder_pen) ){

                arrayList_temp.add(arrayListBrooderInventory.get(i)); //ito na yung list ng inventory na nasa pen

            }
        }

                    ////feeding records
        Cursor cursor_brooder_feeding_records = myDb.getAllDataFromBreederFeedingRecords();
        cursor_brooder_feeding_records.moveToFirst();
        if(cursor_brooder_feeding_records.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {
            do {

                for(int k=0;k<arrayList_temp.size();k++){
                    if(arrayList_temp.get(k).getBrooder_inv_brooder_id().equals(cursor_brooder_feeding_records.getInt(1))){
                        Breeder_FeedingRecords brooderFeedingRecords = new Breeder_FeedingRecords(cursor_brooder_feeding_records.getInt(0),cursor_brooder_feeding_records.getInt(1), cursor_brooder_feeding_records.getString(2), arrayList_temp.get(k).getBrooder_inv_brooder_tag(),cursor_brooder_feeding_records.getFloat(3),cursor_brooder_feeding_records.getFloat(4), cursor_brooder_feeding_records.getString(5), cursor_brooder_feeding_records.getString(6));

                        arrayListBrooderFeedingRecords.add(brooderFeedingRecords);


                    }
                }

            } while (cursor_brooder_feeding_records.moveToNext());
        }



        recycler_adapter = new RecyclerAdapter_Breeder_Feeding(arrayListBrooderFeedingRecords);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();



    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent_brooders = new Intent(BreederFeedingRecordsActivity.this, CreateBreeders.class);
        startActivity(intent_brooders);
        return true;
    }

    @Override
    public void onBackPressed() {


        Intent intent_brooders = new Intent(BreederFeedingRecordsActivity.this, CreateBreeders.class);
        startActivity(intent_brooders);

    }


}

package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

import android.app.AlertDialog;
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


public class ReplacementGrowthRecordsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView brooder_pen_textView;


    DatabaseHelper myDb;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Replacement_GrowthRecords> arrayListGrowth = new ArrayList<>();
    ArrayList<Replacement_Inventory>arrayListReplacementInventory = new ArrayList<>();
    ArrayList<Replacement_Inventory>arrayListReplacementInventory1 = new ArrayList<>();
    ArrayList<Replacement_GrowthRecords>arrayList_temp = new ArrayList<>();
    ImageButton create_brooder_feeding_records;


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


        ////inventory
        Cursor cursor_inventory = myDb.getAllDataFromReplacementInventory();
        cursor_inventory.moveToFirst();

        if (cursor_inventory.getCount() == 0) {

        } else {
            do {
                Replacement_Inventory replacement_inventory = new Replacement_Inventory(cursor_inventory.getInt(0), cursor_inventory.getInt(1), null,null,null,cursor_inventory.getString(2), cursor_inventory.getString(3), cursor_inventory.getString(4), cursor_inventory.getInt(5), cursor_inventory.getInt(6), cursor_inventory.getInt(7), null, cursor_inventory.getString(8), cursor_inventory.getString(9));
                arrayListReplacementInventory.add(replacement_inventory);
            } while (cursor_inventory.moveToNext());
        }

        ///GROWTH RECORDS
        Cursor cursor_growth = myDb.getAllDataFromReplacementGrowthRecords();
        cursor_growth.moveToFirst();

            if(cursor_growth.getCount()==0){
            Toast.makeText(this,"No data from growth records", Toast.LENGTH_SHORT).show();
        }else{
            do{
                Replacement_GrowthRecords replacement_growthRecords = new Replacement_GrowthRecords(cursor_growth.getInt(0), cursor_growth.getInt(1),            null,                  cursor_growth.getInt(2),                     cursor_growth.getString(3),                     cursor_growth.getInt(4),                    cursor_growth.getFloat(5),                      cursor_growth.getInt(6),                          cursor_growth.getFloat(7),                cursor_growth.getInt(8),                    cursor_growth.getFloat(9),          cursor_growth.getString(10));
                arrayList_temp.add(replacement_growthRecords);
            }while (cursor_growth.moveToNext());

        }

        //kunin mo naman yung replacement inventories ng given PEN NUMBER
        for (int i = 0; i<arrayListReplacementInventory.size();i++){
            if (arrayListReplacementInventory.get(i).getReplacement_inv_pen().equals(replacement_pen)){
                arrayListReplacementInventory1.add(arrayListReplacementInventory.get(i));


            }
        }

        //kunin mo naman yung feeding records based sa id ng brooder inventories sa arrayListReplacementInventory1

        for (int i=0; i<arrayList_temp.size();i++){
            for(int j=0;j<arrayListReplacementInventory1.size();j++){
                if (arrayList_temp.get(i).getReplacement_growth_inventory_id().equals(arrayListReplacementInventory1.get(j).getId())){
                    arrayListGrowth.add(arrayList_temp.get(i));

                }
            }
        }


        //  int count_inventory =0;

       /* for(int i=0;i<arrayList_temp.size();i++){
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
        }

        ////growth records
        StringBuffer buffer = new StringBuffer();
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
                        buffer.append("Browth brooder_inv_id"+cursor_brooder_growth_records.getInt(1) + "\n");

                    }
                }


            } while (cursor_brooder_growth_records.moveToNext());
        }




                for(int i = 0;i<arrayList_temp.size();i++){
                    buffer.append("arrayList_temp inv id "+arrayList_temp.get(i).getBrooder_inv_brooder_id().toString()+"\n\n");
                }

            showMessage("count_inventory", buffer.toString());



                                                                //pass dictionary   total count ng brooders arraylistngfeedingrecords
        recycler_adapter = new RecyclerAdapter_Brooder_Growth(arrayListBrooderGrowthRecords);
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();*/
        recycler_adapter = new RecyclerAdapter_Replacement_Growth(arrayListGrowth);
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


}

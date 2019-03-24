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


public class ReplacementFeedingRecordsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView replacement_pen_textView;


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

        //NOTE
        //wag mo muna problemahin yung pagshow ng database mula sa foreign keys



     /*   Cursor cursor_replacement = myDb.getAllDataFromReplacements();
        cursor_replacement.moveToFirst();

        if(cursor_replacement.getCount() == 0){
            Toast.makeText(this,"No data on Replacement Table", Toast.LENGTH_LONG).show();
        }else{
            do{

                Replacements replacements = new Replacements(cursor_replacement.getInt(0),cursor_replacement.getString(1), cursor_replacement.getString(2), cursor_replacement.getString(3), cursor_replacement.getString(4), cursor_replacement.getString(5));
                arrayListReplacements.add(replacements);
            }while (cursor_replacement.moveToNext());
        }



        Cursor curse = myDb.getAllDataFromReplacementInventory();
        curse.moveToFirst();

        if(curse.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {

            do {

                for(int i = 0; i<arrayListReplacements.size();i++){
                    if(arrayListReplacements.get(i).getId().equals(curse.getInt(1))){
                        Replacement_Inventory replace = new Replacement_Inventory(curse.getInt(0),curse.getInt(1), arrayListReplacements.get(i).getReplacement_family_number(),  arrayListReplacements.get(i).getReplacement_line_number(), arrayListReplacements.get(i).getReplacement_generation_number(), curse.getString(2), curse.getString(3),curse.getString(4), curse.getInt(5), curse.getInt(6), curse.getInt(7), arrayListReplacements.get(i).getReplacement_date_added(),curse.getString(8),curse.getString(9));
                        arrayListReplacementInventory.add(replace);
                    }
                }

            } while (curse.moveToNext());

        }

        for(int i=0;i<arrayListReplacementInventory.size();i++){
            if(arrayListReplacementInventory.get(i).getReplacement_inv_pen().equals(replacement_pen)){
                arrayListReplacementInventory1.add(arrayListReplacementInventory.get(i));
            }
        }


        Cursor cursor_replacement_feeding = myDb.getAllDataFromReplacementFeedingRecords();
        cursor_replacement_feeding.moveToFirst();

        if (cursor_replacement_feeding.getCount() == 0){
            //bla
        }else{
            do{
                for (int i = 0;i<arrayListReplacementInventory1.size();i++){
                    if (arrayListReplacementInventory1.get(i).getId().equals(cursor_replacement_feeding.getInt(1))){

                                                                                                             *//*    private Integer id;    private Integer replacement_feeding_inventory_id;    private String replacement_feeding_date_collected;    private String replacement_feeding_tag;                            private Float replacement_feeding_offered;    private Float replacement_feeding_refused;    private String replacement_feeding_remarks;    private String replacement_feeding_deleted_at;*//*
                        Replacement_FeedingRecords replacement_feedingRecords = new Replacement_FeedingRecords(cursor_replacement_feeding.getInt(0), cursor_replacement_feeding.getInt(1),cursor_replacement_feeding.getString(2), arrayListReplacementInventory1.get(i).getReplacement_inv_replacement_tag(), cursor_replacement_feeding.getFloat(3), cursor_replacement_feeding.getFloat(4), cursor_replacement_feeding.getString(5), cursor_replacement_feeding.getString(6));
                        arrayList_temp.add(replacement_feedingRecords);
                    }
                }

            }while (cursor_replacement_feeding.moveToNext());
        }

*/

        Cursor cursor_replacement_inv = myDb.getAllDataFromReplacementInventory(); //para sa pagstore ng data sa arraylist
        cursor_replacement_inv.moveToFirst();
        if(cursor_replacement_inv.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {
            do {

                Replacement_Inventory replacement_inventory = new Replacement_Inventory(cursor_replacement_inv.getInt(0),cursor_replacement_inv.getInt(1), cursor_replacement_inv.getString(2), cursor_replacement_inv.getString(3),cursor_replacement_inv.getString(4), cursor_replacement_inv.getInt(5), cursor_replacement_inv.getInt(6),cursor_replacement_inv.getInt(7), cursor_replacement_inv.getString(8), cursor_replacement_inv.getString(9));
                arrayListReplacementInventory.add(replacement_inventory);


            } while (cursor_replacement_inv.moveToNext());
        }



        for (int i=0;i<arrayListReplacementInventory.size();i++){
            if(arrayListReplacementInventory.get(i).getReplacement_inv_pen().equals(replacement_pen) ){

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
                    if(arrayList_temp.get(k).getReplacement_inv_replacement_id().equals(cursor_feeding.getInt(1))){
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

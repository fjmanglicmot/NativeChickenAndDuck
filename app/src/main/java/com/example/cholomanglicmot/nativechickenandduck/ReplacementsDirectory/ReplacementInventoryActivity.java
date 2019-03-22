package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;



public class ReplacementInventoryActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView replacement_pen_textView;

    DatabaseHelper myDb;

    ArrayList<Replacement_Inventory> arrayListReplacementInventory = new ArrayList<>();
    ArrayList<Replacement_Inventory> arrayList_temp = new ArrayList<>();
    ArrayList<Replacements> arrayListReplacements = new ArrayList<>();
    ArrayList list = new ArrayList();

    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_inventory);

        String replacement_pen;
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
        replacement_pen_textView = findViewById(R.id.replacement_pen);
        replacement_pen_textView.setText("Replacement Pen " +replacement_pen);

        myDb = new DatabaseHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewReplacementInventory);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Replacement Inventory");
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        Cursor cursor_replacement = myDb.getAllDataFromReplacements();
        cursor_replacement.moveToFirst();

     /*   if(cursor_replacement.getCount() == 0){
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

                                                                                                               //  REPLACEMENT_INV_COL_0 = "ID";    "REPLACEMENT_INV_REPLACEMENT_ID";                                                                                                                                                                                                    "REPLACEMENT_INV_PEN_NUMBER";              "REPLACEMENT_INV_REPLACEMENT_TAG";            "REPLACEMENT_INV_BATCHING_DATE";             "REPLACEMENT_INV_NUMBER_MALE";             "REPLACEMENT_INV_NUMBER_FEMALE";           "REPLACEMENT_INV_TOTAL";                                                                 "REPLACEMENT_INV_LAST_UPDATE";             "REPLACEMENT_INV_DELETED_AT";
              } while (curse.moveToNext());

        }*/
        Cursor cursor_replacement_inv = myDb.getAllDataFromReplacementInventory(); //para sa pagstore ng data sa arraylist
        cursor_replacement_inv.moveToFirst();
        if(cursor_replacement_inv.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {
            do {
/*
    public static final String TABLE_REPLACEMENT_INVENTORIES = "replacement_inventory_table";
    public static final String REPLACEMENT_INV_COL_0 = "ID";
    public static final String REPLACEMENT_INV_COL_1 = "REPLACEMENT_INV_REPLACEMENT_ID";
    public static final String REPLACEMENT_INV_COL_2 = "REPLACEMENT_INV_PEN_NUMBER"; //REFERENCES SA PEN
    public static final String REPLACEMENT_INV_COL_3 = "REPLACEMENT_INV_REPLACEMENT_TAG";
    public static final String REPLACEMENT_INV_COL_4 = "REPLACEMENT_INV_BATCHING_DATE";
    public static final String REPLACEMENT_INV_COL_5 = "REPLACEMENT_INV_NUMBER_MALE";
    public static final String REPLACEMENT_INV_COL_6 = "REPLACEMENT_INV_NUMBER_FEMALE";
    public static final String REPLACEMENT_INV_COL_7 = "REPLACEMENT_INV_TOTAL";
    public static final String REPLACEMENT_INV_COL_8 = "REPLACEMENT_INV_LAST_UPDATE";
    public static final String REPLACEMENT_INV_COL_9 = "REPLACEMENT_INV_DELETED_AT";*/
                Replacement_Inventory replace = new Replacement_Inventory(cursor_replacement_inv.getInt(0),cursor_replacement_inv.getInt(1), cursor_replacement_inv.getString(2), cursor_replacement_inv.getString(3),cursor_replacement_inv.getString(4), cursor_replacement_inv.getInt(5), cursor_replacement_inv.getInt(6), cursor_replacement_inv.getInt(7), null,null);
                arrayListReplacementInventory.add(replace);
            } while (cursor_replacement_inv.moveToNext());
        }



        for (int i=0;i<arrayListReplacementInventory.size();i++){
            if(arrayListReplacementInventory.get(i).getReplacement_inv_pen().equals(replacement_pen) ){

                arrayList_temp.add(arrayListReplacementInventory.get(i)); //arrayList_temp ay naglalaman ng lahat ng brooder_inv sa loob ng pen na napili

            }
        }




        for(int i = 0; i<arrayListReplacementInventory.size();i++){
            if(arrayListReplacementInventory.get(i).getReplacement_inv_pen().equals(replacement_pen)){
                arrayList_temp.add(arrayListReplacementInventory.get(i));
            }
        }



        recycler_adapter = new RecyclerAdapter_Replacement_Inventory(arrayList_temp);//arrayList = brooder_pen, arrayListInventory = brooder_inventory, arrayListBrooders = brooders
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

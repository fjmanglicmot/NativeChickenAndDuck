package com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory;

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
import java.util.HashMap;
import java.util.Map;

public class BrooderInventoryActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView brooder_pen_textView;

    DatabaseHelper myDb;

    ArrayList<Brooder_Inventory> arrayListBrooderInventory = new ArrayList<>();
    ArrayList<Brooder_Inventory> arrayList_temp = new ArrayList<>();
    ArrayList<Brooders> arrayListBrooders = new ArrayList<>();


    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;


    Map<Integer, ArrayList<Brooder_Inventory>> brooder_id_dictionary = new HashMap<Integer, ArrayList<Brooder_Inventory>>();
    ArrayList<Brooder_Inventory> arrayList_brooder_inv_dict_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brooder_inventory);
        String brooder_pen;
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
        brooder_pen_textView = findViewById(R.id.brooder_pen);
        brooder_pen_textView.setText("Brooder Pen " +brooder_pen);

        myDb = new DatabaseHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewBrooderInventory);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Brooder Inventory");
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Cursor cursor_brooder = myDb.getAllDataFromBrooders();
        Cursor cursor_brooder_inventory2 = myDb.getAllDataFromBrooderInventory(); //GINAMIT SA DICTIONARY PERO DI PA NAIIMPLEMENT
        cursor_brooder_inventory2.moveToFirst();
        cursor_brooder.moveToPosition(1); //just in case lang na gagamitin mo

        ///////so meron ka nang arraylist para sa brooders
                //////meron narin para sa brooderinventory
                    ///////gawa ka ng dictionary na ang key ay ang brooder_id ng brooder




        Cursor cursor_brooder_inventory = myDb.getAllDataFromBrooderInventory(); //para sa pagstore ng data sa arraylist
        cursor_brooder_inventory.moveToFirst();
        if(cursor_brooder_inventory.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {
            do {
                Brooder_Inventory brooder_inventory = new Brooder_Inventory(cursor_brooder_inventory2.getInt(0),cursor_brooder_inventory.getInt(1), cursor_brooder_inventory.getString(2), cursor_brooder_inventory.getString(3),cursor_brooder_inventory.getString(4), cursor_brooder_inventory.getInt(5), cursor_brooder_inventory.getInt(6),cursor_brooder_inventory.getInt(7), cursor_brooder_inventory.getString(8), cursor_brooder_inventory.getString(9));
                arrayListBrooderInventory.add(brooder_inventory);
            } while (cursor_brooder_inventory.moveToNext());
        }



        for (int i=0;i<arrayListBrooderInventory.size();i++){
            if(arrayListBrooderInventory.get(i).getBrooder_inv_pen().equals(brooder_pen) ){

                arrayList_temp.add(arrayListBrooderInventory.get(i)); //arrayList_temp ay naglalaman ng lahat ng brooder_inv sa loob ng pen na napili
                    //lol mukhang di mo naman ginamit
            }
        }

        cursor_brooder_inventory2.moveToFirst();
        if(cursor_brooder_inventory2.getCount() == 0){ //if walang laman yung brooder inventory

            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();

        }else {
            do {



              if (brooder_id_dictionary.containsKey(cursor_brooder_inventory2.getInt(1))){
                  arrayList_brooder_inv_dict_list = brooder_id_dictionary.get(cursor_brooder_inventory2.getInt(1));
                  Brooder_Inventory brooder_inventory = new Brooder_Inventory(cursor_brooder_inventory2.getInt(0),cursor_brooder_inventory2.getInt(1), cursor_brooder_inventory2.getString(2), cursor_brooder_inventory2.getString(3),cursor_brooder_inventory2.getString(4), cursor_brooder_inventory2.getInt(5), cursor_brooder_inventory2.getInt(6),cursor_brooder_inventory2.getInt(7), cursor_brooder_inventory2.getString(8), cursor_brooder_inventory2.getString(9));
                  arrayList_brooder_inv_dict_list.add(brooder_inventory);
              }else{
                  ArrayList<Brooder_Inventory> arrayList_brooder_inv_dict_list_temp = new ArrayList<Brooder_Inventory>();
                  Brooder_Inventory brooder_inventory2 = new Brooder_Inventory(cursor_brooder_inventory2.getInt(0),cursor_brooder_inventory2.getInt(1), cursor_brooder_inventory2.getString(2), cursor_brooder_inventory2.getString(3),cursor_brooder_inventory2.getString(4), cursor_brooder_inventory2.getInt(5), cursor_brooder_inventory2.getInt(6),cursor_brooder_inventory2.getInt(7), cursor_brooder_inventory2.getString(8), cursor_brooder_inventory2.getString(9));
                  arrayList_brooder_inv_dict_list_temp.add(brooder_inventory2);
                  brooder_id_dictionary.put(cursor_brooder_inventory2.getInt(1),arrayList_brooder_inv_dict_list_temp);
              }


            } while (cursor_brooder_inventory2.moveToNext());
        }



        recycler_adapter = new RecyclerAdapter_Brooder_Inventory(arrayList_temp, brooder_pen, brooder_id_dictionary);//arrayList = brooder_pen, arrayListInventory = brooder_inventory, arrayListBrooders = brooders
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

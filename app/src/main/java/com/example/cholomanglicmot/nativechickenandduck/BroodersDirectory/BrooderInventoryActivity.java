package com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory;

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

public class BrooderInventoryActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView brooder_pen_textView;
    Integer pen_id;

    DatabaseHelper myDb;

    ArrayList<Brooder_Inventory> arrayListBrooderInventory = new ArrayList<>();
    ArrayList<Brooder_Inventory> arrayList_temp = new ArrayList<>();
    ArrayList<Brooder_Inventory> arrayList_brooderInventory = new ArrayList<>();
    Integer brooder_pen_id;


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


        Cursor cursor1 = myDb.getAllDataFromPenWhere(brooder_pen);
        cursor1.moveToFirst();
        if(cursor1.getCount() != 0){
            brooder_pen_id = cursor1.getInt(0);
        }

        Cursor cursor = myDb.getAllDataFromPenWhere(brooder_pen);
        cursor.moveToFirst();
        if(cursor.getCount()!=0){
            pen_id = cursor.getInt(0);
        }

        boolean isNetworkAvailable = isNetworkAvailable();
        if (isNetworkAvailable) {
            //if internet is available, load data from web database


            //HARDCODED KASI WALA KA PANG DATABASE NA NANDUN EMAIL MO

            API_getBrooderInventory(pen_id);


        } else {

            ///////so meron ka nang arraylist para sa brooders
            //////meron narin para sa brooderinventory
            ///////gawa ka ng dictionary na ang key ay ang brooder_id ng brooder


            //famLineGen = myDb.getFamLineGen(cursor_brooder_inventory.get)

            Cursor cursor_brooder_inventory = myDb.getAllDataFromBrooderInventory(); //para sa pagstore ng data sa arraylist
            cursor_brooder_inventory.moveToFirst();
            if(cursor_brooder_inventory.getCount() == 0){
                //show message
                Toast.makeText(this,"No data inventories.", Toast.LENGTH_LONG).show();

            }else {
                do {

                    Brooder_Inventory brooder_inventory = new Brooder_Inventory(cursor_brooder_inventory.getInt(0),cursor_brooder_inventory.getInt(1), cursor_brooder_inventory.getInt(2), cursor_brooder_inventory.getString(3),cursor_brooder_inventory.getString(4), cursor_brooder_inventory.getInt(5), cursor_brooder_inventory.getInt(6),cursor_brooder_inventory.getInt(7), cursor_brooder_inventory.getString(8), cursor_brooder_inventory.getString(9));
                    arrayListBrooderInventory.add(brooder_inventory);
                } while (cursor_brooder_inventory.moveToNext());
            }



            for (int i=0;i<arrayListBrooderInventory.size();i++){
                if(arrayListBrooderInventory.get(i).getBrooder_inv_pen().equals(brooder_pen_id) ){

                    arrayList_temp.add(arrayListBrooderInventory.get(i)); //arrayList_temp ay naglalaman ng lahat ng brooder_inv sa loob ng pen na napili

                }
            }



            recycler_adapter = new RecyclerAdapter_Brooder_Inventory(arrayList_temp, brooder_pen);//arrayList = brooder_pen, arrayListInventory = brooder_inventory, arrayListBrooders = brooders
            recyclerView.setAdapter(recycler_adapter);
            recycler_adapter.notifyDataSetChanged();

        }



    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void API_getBrooderInventory(Integer pen_id){
        APIHelper.getBrooderInventory("getBrooderInventory/"+pen_id.toString(), new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){

                Gson gson = new Gson();
                JSONBrooderInventory jsonBrooderInventory = gson.fromJson(rawJsonResponse, JSONBrooderInventory.class);
                arrayList_brooderInventory = jsonBrooderInventory.getData();

                for (int i = 0; i < arrayList_brooderInventory.size(); i++) {
                    //check if generation to be inserted is already in the database
                    Cursor cursor = myDb.getAllDataFromBrooderInventoryWhereID(arrayList_brooderInventory.get(i).getId());
                    cursor.moveToFirst();

                   // if (cursor.getCount() == 0) {

                        arrayList_temp.add(arrayList_brooderInventory.get(i)); //arrayList_temp ay naglalaman ng lahat ng brooder_inv sa loob ng pen na napili
                        boolean isInserted = myDb.insertDataBrooderInventoryWithID(arrayList_brooderInventory.get(i).getId(), arrayList_brooderInventory.get(i).getBrooder_inv_brooder_id(), arrayList_brooderInventory.get(i).getBrooder_inv_pen(), arrayList_brooderInventory.get(i).getBrooder_inv_brooder_tag(),arrayList_brooderInventory.get(i).getBrooder_inv_batching_date(),arrayList_brooderInventory.get(i).getBrooder_male_quantity(),arrayList_brooderInventory.get(i).getBrooder_female_quantity(),arrayList_brooderInventory.get(i).getBrooder_total_quantity(), arrayList_brooderInventory.get(i).getBrooder_inv_last_update(), arrayList_brooderInventory.get(i).getBrooder_inv_deleted_at());
                    //Toast.makeText(BrooderInventoryActivity.this, "oyoyooyoy", Toast.LENGTH_SHORT).show();
                   // }

                }

                for (int i=0;i<arrayList_brooderInventory.size();i++){
                  //  if(arrayList_brooderInventory.get(i).getBrooder_inv_pen().equals(brooder_pen) ){



                   // }
                }
                recycler_adapter = new RecyclerAdapter_Brooder_Inventory(arrayList_temp, null);
                recyclerView.setAdapter(recycler_adapter);
                recycler_adapter.notifyDataSetChanged();

             //   Toast.makeText(getApplicationContext(), "Successfully added Brooder Inventory from web database ", Toast.LENGTH_SHORT).show();
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

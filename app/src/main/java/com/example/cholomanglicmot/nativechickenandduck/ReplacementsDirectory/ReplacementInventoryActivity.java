package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.APIHelper;
import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;
import com.google.gson.Gson;
import com.loopj.android.http.BaseJsonHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class ReplacementInventoryActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView replacement_pen_textView;

    DatabaseHelper myDb;

    ArrayList<Replacement_Inventory> arrayListReplacementInventory = new ArrayList<>();
    ArrayList<Replacement_Inventory> arrayList_temp = new ArrayList<>();
    ArrayList<Replacements> arrayListReplacements = new ArrayList<>();
    ArrayList list = new ArrayList();
    Integer replacement_pen_id;

    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Replacement_Inventory> arrayList_brooderInventory;


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



        Cursor cursor1 = myDb.getAllDataFromPenWhere(replacement_pen);
        cursor1.moveToFirst();
        if(cursor1.getCount() != 0){
            replacement_pen_id = cursor1.getInt(0);
        }



        boolean isNetworkAvailable = isNetworkAvailable();
        if (isNetworkAvailable) {
            //if internet is available, load data from web database




            API_getReplacementInventory();


        }


        Cursor cursor_replacement_inv = myDb.getAllDataFromReplacementInventory(); //para sa pagstore ng data sa arraylist
        cursor_replacement_inv.moveToFirst();
        if(cursor_replacement_inv.getCount() == 0){
            //show message
            //Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {
            do {

                Replacement_Inventory replace = new Replacement_Inventory(cursor_replacement_inv.getInt(0),cursor_replacement_inv.getInt(1), cursor_replacement_inv.getInt(2), cursor_replacement_inv.getString(3),cursor_replacement_inv.getString(4), cursor_replacement_inv.getInt(5), cursor_replacement_inv.getInt(6), cursor_replacement_inv.getInt(7), cursor_replacement_inv.getString(8),cursor_replacement_inv.getString(9));
                arrayListReplacementInventory.add(replace);
            } while (cursor_replacement_inv.moveToNext());
        }








        for (int i=0;i<arrayListReplacementInventory.size();i++){
            if(arrayListReplacementInventory.get(i).getReplacement_inv_pen() == replacement_pen_id && arrayListReplacementInventory.get(i).getReplacement_inv_deleted_at() == null) {

                arrayList_temp.add(arrayListReplacementInventory.get(i)); //arrayList_temp ay naglalaman ng lahat ng brooder_inv sa loob ng pen na napili

            }
        }



        recycler_adapter = new RecyclerAdapter_Replacement_Inventory(arrayList_temp);//arrayList = brooder_pen, arrayListInventory = brooder_inventory, arrayListBrooders = brooders
        recyclerView.setAdapter(recycler_adapter);
        recycler_adapter.notifyDataSetChanged();
    }
    private void API_getReplacementInventory(){
        APIHelper.getReplacementInventory("getReplacementInventory/", new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){

                Gson gson = new Gson();
                JSONReplacementInventory jsonBrooderInventory = gson.fromJson(rawJsonResponse, JSONReplacementInventory.class);
                arrayList_brooderInventory = jsonBrooderInventory.getData();

                for (int i = 0; i < arrayList_brooderInventory.size(); i++) {
                    //check if generation to be inserted is already in the database
                    Cursor cursor = myDb.getAllDataFromReplacementInventoryWhereID(arrayList_brooderInventory.get(i).getId());
                    cursor.moveToFirst();

                    if (cursor.getCount() == 0) {


                        boolean isInserted = myDb.insertDataReplacementInventoryWithID(arrayList_brooderInventory.get(i).getId(), arrayList_brooderInventory.get(i).getReplacement_inv_replacement_id(), arrayList_brooderInventory.get(i).getReplacement_inv_pen(), arrayList_brooderInventory.get(i).getReplacement_inv_replacement_tag(),arrayList_brooderInventory.get(i).getReplacement_inv_batching_date(),arrayList_brooderInventory.get(i).getReplacement_male_quantity(),arrayList_brooderInventory.get(i).getReplacement_female_quantity(),arrayList_brooderInventory.get(i).getReplacement_total_quantity(), arrayList_brooderInventory.get(i).getReplacement_inv_last_update(), arrayList_brooderInventory.get(i).getReplacement_inv_deleted_at());

                    }

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonResponse, Object response){

                Toast.makeText(getApplicationContext(), "Failed to fetch Replacement Inventory from web database ", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable{
                return null;
            }
        });
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent_brooders = new Intent(ReplacementInventoryActivity.this, CreateReplacements.class);

        startActivity(intent_brooders);

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

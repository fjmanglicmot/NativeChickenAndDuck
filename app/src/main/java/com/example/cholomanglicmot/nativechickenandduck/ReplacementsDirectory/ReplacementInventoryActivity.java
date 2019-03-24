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





        Cursor cursor_replacement_inv = myDb.getAllDataFromReplacementInventory(); //para sa pagstore ng data sa arraylist
        cursor_replacement_inv.moveToFirst();
        if(cursor_replacement_inv.getCount() == 0){
            //show message
            Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

        }else {
            do {

                Replacement_Inventory replace = new Replacement_Inventory(cursor_replacement_inv.getInt(0),cursor_replacement_inv.getInt(1), cursor_replacement_inv.getString(2), cursor_replacement_inv.getString(3),cursor_replacement_inv.getString(4), cursor_replacement_inv.getInt(5), cursor_replacement_inv.getInt(6), cursor_replacement_inv.getInt(7), null,null);
                arrayListReplacementInventory.add(replace);
            } while (cursor_replacement_inv.moveToNext());
        }



        for (int i=0;i<arrayListReplacementInventory.size();i++){
            if(arrayListReplacementInventory.get(i).getReplacement_inv_pen().equals(replacement_pen) ){

                arrayList_temp.add(arrayListReplacementInventory.get(i)); //arrayList_temp ay naglalaman ng lahat ng brooder_inv sa loob ng pen na napili

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

package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory.CreateBreeders;
import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.CreateBrooders;
import com.example.cholomanglicmot.nativechickenandduck.DashboardDirectory.DashBoardActivity;
import com.example.cholomanglicmot.nativechickenandduck.DataProvider;
import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.FamilyDirectory.CreateFamilies;
import com.example.cholomanglicmot.nativechickenandduck.FarmSettingsDirectory.MainActivity;
import com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory.CreateGenerationsAndLines;
import com.example.cholomanglicmot.nativechickenandduck.PensDirectory.CreatePen;
import com.example.cholomanglicmot.nativechickenandduck.ProjectAdapter;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CreateReplacements extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private ImageButton create_pen;
    private Button show_data_button;
    private Button delete_pen_table;
    private EditText replacement_search_bar;
    LinkedHashMap<String, List<String>> Project_category;
    List<String> Project_list;
    ExpandableListView Exp_list;
    ProjectAdapter adapter;
    DatabaseHelper myDb;
    //private String brooder_pen = getArguments().getString("brooder_pen");

    //Map<String, ArrayList<String>> brooder_inventory_dictionary = new HashMap<String, ArrayList<String>>();
    ArrayList<String> list = new ArrayList<String>();

    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Replacement_Pen> arrayList = new ArrayList<>();
    ArrayList<Replacement_Inventory> arrayList2 = new ArrayList<>();
    ArrayList<Replacements> arrayList3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_replacements);

        // SEARCHBAR DISABLED
/*        replacement_search_bar = findViewById(R.id.replacement_search_bar);
        replacement_search_bar.setInputType(InputType.TYPE_NULL);*/

        // SEARCHBAR DISABLED
        Exp_list = findViewById(R.id.exp_list);
        Project_category = DataProvider.getInfo();
        Project_list = new ArrayList<String>(Project_category.keySet());
        adapter = new ProjectAdapter(this, Project_category, Project_list);
        Exp_list.setAdapter(adapter);


        //delete_pen_table = findViewById(R.id.delete_pen_table);
        myDb = new DatabaseHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        Exp_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String string2 = Project_list.get(groupPosition);

                switch (string2) {
                    case "Dashboard":
                        Intent intent_main = new Intent(CreateReplacements.this, DashBoardActivity.class);
                        startActivity(intent_main);
                        break;
                    case "Pens":
                        Intent intent_pens = new Intent(CreateReplacements.this, CreatePen.class);
                        startActivity(intent_pens);
                        break;
                    case "Generations and Lines":
                        Intent intent_generations_and_lines = new Intent(CreateReplacements.this, CreateGenerationsAndLines.class);
                        startActivity(intent_generations_and_lines);
                        break;
                    case "Families":
                        Intent intent_families = new Intent(CreateReplacements.this, CreateFamilies.class);
                        startActivity(intent_families);
                        break;
                    case "Breeders":
                        Intent intent_breeders = new Intent(CreateReplacements.this, CreateBreeders.class);
                        startActivity(intent_breeders);
                        break;
                    case "Brooders":
                        Intent intent_brooders = new Intent(CreateReplacements.this, CreateBrooders.class);
                        startActivity(intent_brooders);
                        break;
                    case "Replacements":
                        finish();
                        startActivity(getIntent());
                        break;
                     case "Reports":
                        break;

                    case "Farm Settings":
                        Intent intent = new Intent(CreateReplacements.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.closed);
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Replacements");

        Cursor cursor_replacement_inventory = myDb.getAllDataFromReplacementInventory();
        Cursor cursor_replacement_pen = myDb.getReplacementsFromPen();
        Cursor cursor_replacement = myDb.getAllDataFromReplacements();

        cursor_replacement.moveToFirst();
        if (cursor_replacement.getCount() == 0) {
            //show message
            Toast.makeText(this, "No data from replacements.", Toast.LENGTH_LONG).show();

        } else {
            do {
/*   Brooders brooders = new Brooders(cursor_brooder.getInt(0), cursor_brooder.getInt(1), cursor_brooder.getString(2),cursor_brooder.getString(3));*/
                Replacements replacements = new Replacements(cursor_replacement.getInt(0), cursor_replacement.getString(1), cursor_replacement.getString(2), cursor_replacement.getString(3));
                arrayList3.add(replacements);


            } while (cursor_replacement.moveToNext());
        }


        if (cursor_replacement_pen.getCount() == 0) {
            //show message
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            return;
        } else {

            cursor_replacement_pen.moveToFirst();
            do {
                Replacement_Pen replacement_pen = new Replacement_Pen(cursor_replacement_pen.getString(1), cursor_replacement_pen.getInt(3), cursor_replacement_pen.getInt(4) - cursor_replacement_pen.getInt(3));
                arrayList.add(replacement_pen);
            } while (cursor_replacement_pen.moveToNext());


            recycler_adapter = new RecyclerAdapter_Replacement_Pen(arrayList,arrayList2,arrayList3);//arrayList = brooder_pen, arrayListInventory = replacement_inventory, arrayListReplacements = brooders
            recyclerView.setAdapter(recycler_adapter);
            recycler_adapter.notifyDataSetChanged();

        }






    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}

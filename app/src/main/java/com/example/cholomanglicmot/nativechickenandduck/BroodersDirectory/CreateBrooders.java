
package com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory;

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
import com.example.cholomanglicmot.nativechickenandduck.DataProvider;
import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.FamilyDirectory.CreateFamilies;
import com.example.cholomanglicmot.nativechickenandduck.FarmSettingsDirectory.MainActivity;
import com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory.CreateGenerationsAndLines;
import com.example.cholomanglicmot.nativechickenandduck.PensDirectory.CreatePen;
import com.example.cholomanglicmot.nativechickenandduck.ProjectAdapter;
import com.example.cholomanglicmot.nativechickenandduck.R;
import com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory.CreateReplacements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CreateBrooders extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private ImageButton create_pen;
    private Button show_data_button;
    private Button delete_pen_table;
    private EditText brooder_search_bar;
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

    ArrayList<Brooders_Pen> arrayList = new ArrayList<>();
    ArrayList<Brooder_Inventory> arrayList2 = new ArrayList<>();
    ArrayList<Brooders> arrayList3 = new ArrayList<>();

    //RecyclerAdapter_Brooder_Pen recyclerAdapter_brooder_pen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_brooders);


        // SEARCHBAR DISABLED


        // SEARCHBAR DISABLED


        Exp_list = findViewById(R.id.exp_list);
        Project_category = DataProvider.getInfo();
        Project_list =  new ArrayList<String>(Project_category.keySet());
        adapter = new ProjectAdapter(this, Project_category, Project_list);
        Exp_list.setAdapter(adapter);



        myDb = new DatabaseHelper(this);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        Exp_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String string2 = Project_list.get(groupPosition);

                switch(string2){
                    case "Dashboard":
                        Intent intent_main = new Intent(CreateBrooders.this, MainActivity.class);
                        startActivity(intent_main);
                        break;
                    case "Pens":
                        Intent intent_pens = new Intent(CreateBrooders.this, CreatePen.class);
                        startActivity(intent_pens);
                        break;
                    case "Generations and Lines":
                        Intent intent_generations_and_lines = new Intent(CreateBrooders.this, CreateGenerationsAndLines.class);
                        startActivity(intent_generations_and_lines);
                        break;
                    case "Families":
                        Intent intent_families = new Intent(CreateBrooders.this, CreateFamilies.class);
                        startActivity(intent_families);
                        break;
                    case "Breeders":
                        Intent intent_breeders = new Intent(CreateBrooders.this, CreateBreeders.class);
                        startActivity(intent_breeders);
                        break;
                    case "Brooders":
                        finish();
                        startActivity(getIntent());
                        break;
                    case "Replacements":
                        Intent intent_replacements = new Intent(CreateBrooders.this, CreateReplacements.class);
                        startActivity(intent_replacements);
                        break;
                    case "Mortality, Culling, and Sales":
                        break;

                    case "Reports":
                        break;

                    case "Farm Settings":

                        break;
                }
                return false;
            }
        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.closed);
        mToolbar = (Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Brooders");

/////////////////////////
        StringBuffer buffer = new StringBuffer();
        Cursor cursor_pen = myDb.getAllDataFromPen();
        cursor_pen.moveToFirst();




        //////////////////////////////////////////

        Cursor cursor_brooder_inventory = myDb.getAllDataFromBrooderInventory();
        Cursor cursor_brooder_pen = myDb.getBroodersFromPen();
        Cursor cursor_brooder = myDb.getAllDataFromBrooders();

        cursor_brooder.moveToFirst();
        if(cursor_brooder.getCount() == 0){
            //show message
            Toast.makeText(this,"No data from brooders.", Toast.LENGTH_LONG).show();

        }else {
            do {
                /*    private Integer id;
    private Integer brooder_family_number;
    private String brooder_date_added;
    private String brooder_deleted_at;
*/

                Brooders brooders = new Brooders(cursor_brooder.getInt(0), cursor_brooder.getInt(1), cursor_brooder.getString(2),cursor_brooder.getString(3));
                arrayList3.add(brooders);



            } while (cursor_brooder.moveToNext());
        }



        if(cursor_brooder_pen.getCount() == 0){
            //show message
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
            return;
        }else{

            cursor_brooder_pen.moveToFirst();
            do {
                                                      /*    private String brooder_pen_number;    private Integer brooder_pen_content;    private Integer brooder_pen_free;*/
                Brooders_Pen broodersPen = new Brooders_Pen(cursor_brooder_pen.getString(1), cursor_brooder_pen.getInt(3), cursor_brooder_pen.getInt(4)-cursor_brooder_pen.getInt(3));
                arrayList.add(broodersPen);
            }while (cursor_brooder_pen.moveToNext());

            cursor_brooder_inventory.moveToFirst();
            if(cursor_brooder_inventory.getCount() == 0){
                //show message
                Toast.makeText(this,"No data.", Toast.LENGTH_LONG).show();

            }else {
                do {

                    Brooder_Inventory brooder_inventory = new Brooder_Inventory(cursor_brooder_inventory.getInt(0),cursor_brooder_inventory.getInt(1), cursor_brooder_inventory.getString(2), cursor_brooder_inventory.getString(3),cursor_brooder_inventory.getString(4), cursor_brooder_inventory.getInt(5), cursor_brooder_inventory.getInt(6),cursor_brooder_inventory.getInt(7), cursor_brooder_inventory.getString(8), cursor_brooder_inventory.getString(9));
                    arrayList2.add(brooder_inventory);


                } while (cursor_brooder_inventory.moveToNext());
            }


            recycler_adapter = new RecyclerAdapter_Brooder_Pen(arrayList,arrayList2,arrayList3);//arrayList = brooder_pen, arrayListInventory = brooder_inventory, arrayListBrooders = brooders
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

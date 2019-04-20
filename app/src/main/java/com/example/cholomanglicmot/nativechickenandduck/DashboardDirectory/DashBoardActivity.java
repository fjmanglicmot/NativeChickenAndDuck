package com.example.cholomanglicmot.nativechickenandduck.DashboardDirectory;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.APIHelper;
import com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory.CreateBreeders;
import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.Brooder_FeedingRecords;
import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.Brooder_GrowthRecords;
import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.Brooder_Inventory;
import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.Brooders;
import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.CreateBrooders;
import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.JSONBrooder;
import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.JSONBrooderFeeding;
import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.JSONBrooderGrowth;
import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.JSONBrooderInventory;
import com.example.cholomanglicmot.nativechickenandduck.DataProvider;
import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.FamilyDirectory.CreateFamilies;
import com.example.cholomanglicmot.nativechickenandduck.FamilyDirectory.Family1;
import com.example.cholomanglicmot.nativechickenandduck.FamilyDirectory.JSONFamily1;
import com.example.cholomanglicmot.nativechickenandduck.FarmSettingsDirectory.MainActivity;
import com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory.CreateGenerationsAndLines;
import com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory.Generation;
import com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory.JSONGeneration;
import com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory.JSONLine;
import com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory.Line;
import com.example.cholomanglicmot.nativechickenandduck.PensDirectory.CreatePen;
import com.example.cholomanglicmot.nativechickenandduck.PensDirectory.JSONPen;
import com.example.cholomanglicmot.nativechickenandduck.PensDirectory.Pen;
import com.example.cholomanglicmot.nativechickenandduck.ProjectAdapter;
import com.example.cholomanglicmot.nativechickenandduck.R;
import com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory.CreateReplacements;
import com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory.JSONReplacement;
import com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory.JSONReplacementInventory;
import com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory.Replacement_Inventory;
import com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory.Replacements;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

//import com.squareup.picasso.Picasso;

public class DashBoardActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;

    RecyclerView.LayoutManager layoutManager;
    LinkedHashMap<String, List<String>> Project_category;
    List<String> Project_list;
    ExpandableListView Exp_list;
    ProjectAdapter adapter;
    String farm_id;
    ArrayList<Pen> arrayList_pen;
    DatabaseHelper myDb;
    String name, email;
    Uri photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Exp_list = findViewById(R.id.exp_list);
        Project_category = DataProvider.getInfo();
        Project_list =  new ArrayList<String>(Project_category.keySet());
        adapter = new ProjectAdapter(this, Project_category, Project_list);
        Exp_list.setAdapter(adapter);
        myDb = new DatabaseHelper(this);

        ////////////
        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        String name = user.getDisplayName();

        String email = user.getEmail();

        Uri photo = user.getPhotoUrl();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.textView8);
        TextView nav_email = (TextView)hView.findViewById(R.id.textView9);
        CircleImageView circleImageView = hView.findViewById(R.id.display_photo);
        nav_user.setText(name);
        Picasso.get().load(photo).into(circleImageView);
        nav_email.setText(email);

        ///////////////////



        layoutManager = new LinearLayoutManager(this);

        Exp_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String string2 = Project_list.get(groupPosition);

                switch(string2){
                    case "Dashboard":
                        finish();
                        startActivity(getIntent());
                        break;

                    case "Pens":
                        Intent intent_create_pens = new Intent(DashBoardActivity.this, CreatePen.class);
                        startActivity(intent_create_pens);
                        break;
                    case "Generations and Lines":
                        Intent intent_main = new Intent(DashBoardActivity.this, CreateGenerationsAndLines.class);
                        startActivity(intent_main);
                        break;
                    case "Families":
                        Intent intent_families = new Intent(DashBoardActivity.this, CreateFamilies.class);
                        startActivity(intent_families);
                        break;
                    case "Breeders":
                        Intent intent_breeders = new Intent(DashBoardActivity.this, CreateBreeders.class);
                        startActivity(intent_breeders);
                        break;
                    case "Brooders":
                        Intent intent_brooders = new Intent(DashBoardActivity.this, CreateBrooders.class);
                        startActivity(intent_brooders);
                        break;
                    case "Replacements":
                        Intent intent_replacements = new Intent(DashBoardActivity.this, CreateReplacements.class);
                        startActivity(intent_replacements);
                        break;
                    case "Reports":
                        break;
                    case "Farm Settings":
                        Intent intent_settings = new Intent(DashBoardActivity.this, MainActivity.class);
                        startActivity(intent_settings);
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
        getSupportActionBar().setTitle("Dashboard");

        boolean isNetworkAvailable = isNetworkAvailable();
        if(isNetworkAvailable){
            //if internet is available, load data from web database




            API_getFarmID(email);
            API_getFamily();
            API_getBrooder();
            API_getBrooderInventory();
            API_getReplacement();
            API_getReplacementInventory();
            API_getBrooderFeeding();
            API_getBrooderGrowth();




        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void API_getBrooderGrowth(){
        APIHelper.getBrooderGrowth("getBrooderGrowth/", new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){

                Gson gson = new Gson();
                JSONBrooderGrowth jsonBrooderGrowth = gson.fromJson(rawJsonResponse, JSONBrooderGrowth.class);
                ArrayList<Brooder_GrowthRecords> arrayList_brooderInventory = jsonBrooderGrowth.getData();


                for (int i = 0; i < arrayList_brooderInventory.size(); i++) {
                    //check if generation to be inserted is already in the database
                    Cursor cursor = myDb.getAllDataFromBrooderGrowthRecordsWhereGrowthID(arrayList_brooderInventory.get(i).getId());
                    cursor.moveToFirst();

                    if (cursor.getCount() == 0) {


                        boolean isInserted = myDb.insertDataBrooderGrowthRecordsWithID(arrayList_brooderInventory.get(i).getId(), arrayList_brooderInventory.get(i).getBrooder_growth_inventory_id(), arrayList_brooderInventory.get(i).getBrooder_growth_collection_day(), arrayList_brooderInventory.get(i).getBrooder_growth_date_collected(),arrayList_brooderInventory.get(i).getBrooder_growth_male_quantity(),arrayList_brooderInventory.get(i).getBrooder_growth_male_weight(),arrayList_brooderInventory.get(i).getBrooder_growth_female_quantity(),arrayList_brooderInventory.get(i).getBrooder_growth_female_weight(),arrayList_brooderInventory.get(i).getBrooder_growth_total_quantity(),arrayList_brooderInventory.get(i).getBrooder_growth_total_weight(),arrayList_brooderInventory.get(i).getBrooder_growth_deleted_at());
                        //Toast.makeText(BrooderInventoryActivity.this, "oyoyooyoy", Toast.LENGTH_SHORT).show();
                    }

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonResponse, Object response){

                Toast.makeText(getApplicationContext(), "Failed to fetch Brooders Growth Records from web database ", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable{
                return null;
            }
        });
    }
    private void API_getFarmInfo(String farm_id){
        APIHelper.getFarmInfo("getFarmInfo/"+farm_id, new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){


                myDb = new DatabaseHelper(getApplicationContext());
                rawJsonResponse = rawJsonResponse.replaceAll("\\[", "").replaceAll("\\]","");
              //  Toast.makeText(DashBoardActivity.this, rawJsonResponse, Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                FarmInfo farmInfo = gson.fromJson(rawJsonResponse, FarmInfo.class);

                Cursor cursor = myDb.getAllDataFromFarms();
                cursor.moveToFirst();
                if(cursor.getCount() != 0){
                    if(cursor.getInt(0) != farmInfo.getId()){
                        boolean isInserted = myDb.insertDataFarm(farmInfo.getId(), farmInfo.getName(), farmInfo.getCode(), farmInfo.getAddress(), farmInfo.getBatching_week(), farmInfo.getBreedable_id());
                        if(isInserted){
                            //Toast.makeText(DashBoardActivity.this, "SUCCESS BOI", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(DashBoardActivity.this, "Farm Info already exists in your account", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    boolean isInserted = myDb.insertDataFarm(farmInfo.getId(), farmInfo.getName(), farmInfo.getCode(), farmInfo.getAddress(), farmInfo.getBatching_week(), farmInfo.getBreedable_id());
                    if(isInserted){
                       //Toast.makeText(DashBoardActivity.this, "SUCCESS BOI", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DashBoardActivity.this, "Farm Info already exists in your account", Toast.LENGTH_SHORT).show();
                    }
                }




            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonResponse, Object response){

                Toast.makeText(getApplicationContext(), "Failed getting farm info ", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable{
                return null;
            }
        });
    }

    private void API_getPen(String farm_id){
        APIHelper.getPen("getPen/"+farm_id, new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){

                Gson gson = new Gson();
                JSONPen jsonPen = gson.fromJson(rawJsonResponse, JSONPen.class);
                arrayList_pen = jsonPen.getData();

                for (int i = 0; i < arrayList_pen.size(); i++) {
                    //check if generation to be inserted is already in the database
                    Cursor cursor1 = myDb.getAllDataFromPenWhereID(arrayList_pen.get(i).getId());
                    cursor1.moveToFirst();

                    if (cursor1.getCount() == 0) {

                        boolean isInserted = myDb.insertDataPenWithID(arrayList_pen.get(i).getId(), arrayList_pen.get(i).getFarm_id(), arrayList_pen.get(i).getPen_number(), arrayList_pen.get(i).getPen_type(), arrayList_pen.get(i).getPen_inventory(),arrayList_pen.get(i).getPen_capacity(), arrayList_pen.get(i).getIs_active());
                    }

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonResponse, Object response){

                Toast.makeText(getApplicationContext(), "Failed to fetch Pens from web database ", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable{
                return null;
            }
        });
    }
    private void API_getBrooderFeeding(){
        APIHelper.getBrooderFeeding("getBrooderFeeding/", new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){

                Gson gson = new Gson();
                JSONBrooderFeeding jsonBrooderInventory = gson.fromJson(rawJsonResponse, JSONBrooderFeeding.class);
                ArrayList<Brooder_FeedingRecords> arrayList_brooderInventory = jsonBrooderInventory.getData();


                for (int i = 0; i < arrayList_brooderInventory.size(); i++) {
                    //check if generation to be inserted is already in the database
                    Cursor cursor = myDb.getAllDataFromBrooderFeedingRecordsWhereFeedingID(arrayList_brooderInventory.get(i).getId());
                    cursor.moveToFirst();

                    if (cursor.getCount() == 0) {


                        boolean isInserted = myDb.insertDataBrooderFeedingRecordsWithID(arrayList_brooderInventory.get(i).getId(), arrayList_brooderInventory.get(i).getBrooder_feeding_inventory_id(), arrayList_brooderInventory.get(i).getBrooder_feeding_date_collected(), arrayList_brooderInventory.get(i).getBrooder_feeding_offered(),arrayList_brooderInventory.get(i).getBrooder_feeding_refused(),arrayList_brooderInventory.get(i).getBrooder_feeding_remarks(),arrayList_brooderInventory.get(i).getBrooder_feeding_deleted_at());

                    }

                }


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
    private void API_getFarmID(String email){
        APIHelper.getFarmID("getFarmID/"+email, new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){


                farm_id = rawJsonResponse;

                farm_id = farm_id.replaceAll("\\[", "").replaceAll("\\]","");
                API_getFarmInfo(farm_id);
                API_getGeneration(farm_id);
                API_getPen(farm_id);
                myDb = new DatabaseHelper(getApplicationContext());
                boolean isInsertedUser = myDb.insertDataUser(name, email, null, null, Integer.parseInt(farm_id), null, null, null);




            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonResponse, Object response){

                Toast.makeText(getApplicationContext(), "Failed in getting farm ID ", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable{
                return null;
            }
        });
    }
    private void API_getGeneration(String farm_id){
        APIHelper.getGeneration("getGeneration/"+farm_id, new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {

                Gson gson = new Gson();
                JSONGeneration jsonGeneration = gson.fromJson(rawJsonResponse, JSONGeneration.class);
                ArrayList<Generation> arrayList_gen = jsonGeneration.getData();

                for (int i = 0; i < arrayList_gen.size(); i++) {
                    //check if generation to be inserted is already in the database
                    Cursor cursor = myDb.getDataFromGenerationWhereID(arrayList_gen.get(i).getId());
                    cursor.moveToFirst();

                    if (cursor.getCount() == 0) {
                        API_getLine(arrayList_gen.get(i).getId().toString());
                        //edit insertDataGeneration function, dapat kasama yung primary key "id" kapag nilalagay sa database
                        boolean isInserted = myDb.insertDataGenerationWithID(arrayList_gen.get(i).getId(), arrayList_gen.get(i).getFarm_id(), arrayList_gen.get(i).getGeneration_number(), arrayList_gen.get(i).getNumerical_generation(), arrayList_gen.get(i).getGeneration_status());
                    }

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonResponse, Object response){

                Toast.makeText(getApplicationContext(), "Failed ", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable{
                return null;
            }
        });
    }
    private void API_getLine(String generation_id){
        // for(int i = 0; i<arrayList_gen.size();i++){
        APIHelper.getLine("getLine/"+generation_id, new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){


                Gson gson = new Gson();
                JSONLine jsonLine = gson.fromJson(rawJsonResponse, JSONLine.class);
                ArrayList<Line> arrayList = jsonLine.getData();
                for(int i=0;i<arrayList.size();i++){
                    Cursor cursor = myDb.getAllDataFromLineWhereID(arrayList.get(i).getId());
                    if(cursor.getCount() == 0){
                        //dapat insert mo kasama yung primary key "id"
                        //edit mo yung existing insertDataLine function tapos dapat pati primary key iniinsert mo kapag galing sa web yung data
                        boolean isInserted = myDb.insertDataLineWithID(arrayList.get(i).getId(),arrayList.get(i).getLine_number(),1,Integer.parseInt(generation_id));

                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonResponse, Object response){

                Toast.makeText(getApplicationContext(), "Failed Lines ", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable{
                return null;
            }
        });
    }
    private void API_getFamily(){
        APIHelper.getFamily("getFamily/", new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){
                Gson gson = new Gson();
                JSONFamily1 jsonFamily1 = gson.fromJson(rawJsonResponse, JSONFamily1.class);
                ArrayList<Family1> arrayList_family1 = jsonFamily1.getData();
                for (int i = 0; i < arrayList_family1.size(); i++) {
                    //check if generation to be inserted is already in the database
                    DatabaseHelper myDb = new DatabaseHelper(getApplicationContext());
                    Cursor cursor = myDb.getAllDataFromFamilyWhereID(arrayList_family1.get(i).getId());
                    cursor.moveToFirst();

                    if (cursor.getCount() == 0) {
                        // API_getLine(arrayList_pen.get(i).getId().toString());
                        //edit insertDataGeneration function, dapat kasama yung primary key "id" kapag nilalagay sa database
                        boolean isInserted = myDb.insertDataFamilyWithID(arrayList_family1.get(i).getId(), arrayList_family1.get(i).getNumber(), arrayList_family1.get(i).getIs_active(), arrayList_family1.get(i).getLine_id(), arrayList_family1.get(i).getDeleted_at());
                    }

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonResponse, Object response){

                Toast.makeText(getApplicationContext(), "Failed to fetch Pens from web database ", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable{
                return null;
            }
        });
    }
    private void API_getBrooderInventory(){
        APIHelper.getBrooderInventory("getBrooderInventory/", new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){

                Gson gson = new Gson();
                JSONBrooderInventory jsonBrooderInventory = gson.fromJson(rawJsonResponse, JSONBrooderInventory.class);
                ArrayList<Brooder_Inventory> arrayList_brooderInventory = jsonBrooderInventory.getData();

                for (int i = 0; i < arrayList_brooderInventory.size(); i++) {

                    Cursor cursor = myDb.getAllDataFromBrooderInventoryWhereID(arrayList_brooderInventory.get(i).getId());
                    cursor.moveToFirst();
                    if(cursor.getCount() == 0){
                        boolean isInserted = myDb.insertDataBrooderInventoryWithID(arrayList_brooderInventory.get(i).getId(), arrayList_brooderInventory.get(i).getBrooder_inv_brooder_id(), arrayList_brooderInventory.get(i).getBrooder_inv_pen(), arrayList_brooderInventory.get(i).getBrooder_inv_brooder_tag(),arrayList_brooderInventory.get(i).getBrooder_inv_batching_date(),arrayList_brooderInventory.get(i).getBrooder_male_quantity(),arrayList_brooderInventory.get(i).getBrooder_female_quantity(),arrayList_brooderInventory.get(i).getBrooder_total_quantity(), arrayList_brooderInventory.get(i).getBrooder_inv_last_update(), arrayList_brooderInventory.get(i).getBrooder_inv_deleted_at());

                    }


                }



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
    private void API_getBrooder(){
        APIHelper.getBrooder("getBrooder/", new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){

                Gson gson = new Gson();
                JSONBrooder jsonBrooder = gson.fromJson(rawJsonResponse, JSONBrooder.class);
                ArrayList<Brooders>arrayList_brooder = jsonBrooder.getData();

                for (int i = 0; i < arrayList_brooder.size(); i++) {
                    //check if generation to be inserted is already in the database
                    Cursor cursor = myDb.getAllDataFromBroodersWhereID(arrayList_brooder.get(i).getId());
                    cursor.moveToFirst();

                    if (cursor.getCount() == 0) {

                        boolean isInserted = myDb.insertDataBrooderWithID(arrayList_brooder.get(i).getId(), arrayList_brooder.get(i).getBrooder_family_number(), arrayList_brooder.get(i).getBrooder_date_added(), arrayList_brooder.get(i).getBrooder_deleted_at());
                       // Toast.makeText(CreateBrooders.this, "Brooders Added", Toast.LENGTH_SHORT).show();
                    }

                }



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonResponse, Object response){

                Toast.makeText(getApplicationContext(), "Failed to fetch Brooders from web database ", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable{
                return null;
            }
        });
    }
    private void API_getReplacement(){
        APIHelper.getReplacement("getReplacement/", new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){

                Gson gson = new Gson();
                JSONReplacement jsonReplacement = gson.fromJson(rawJsonResponse, JSONReplacement.class);
                ArrayList <Replacements> arrayList_brooder = jsonReplacement.getData();

                for (int i = 0; i < arrayList_brooder.size(); i++) {
                    //check if generation to be inserted is already in the database
                    Cursor cursor = myDb.getAllDataFromReplacementsWhereID(arrayList_brooder.get(i).getId());
                    cursor.moveToFirst();

                    if (cursor.getCount() == 0) {

                        boolean isInserted = myDb.insertDataReplacementWithID(arrayList_brooder.get(i).getId(), arrayList_brooder.get(i).getReplacement_family_number(), arrayList_brooder.get(i).getReplacement_date_added(), arrayList_brooder.get(i).getReplacement_deleted_at());
                        //Toast.makeText(CreateReplacements.this, "Replacements Added", Toast.LENGTH_SHORT).show();
                    }

                }



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonResponse, Object response){

                Toast.makeText(getApplicationContext(), "Failed to fetch Brooders from web database ", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable{
                return null;
            }
        });
    }
    private void API_getReplacementInventory(){
        APIHelper.getReplacementInventory("getReplacementInventory/", new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){

                Gson gson = new Gson();
                JSONReplacementInventory jsonReplacementInventory = gson.fromJson(rawJsonResponse, JSONReplacementInventory.class);
                ArrayList<Replacement_Inventory> arrayList_brooderInventory = jsonReplacementInventory.getData();

                for (int i = 0; i < arrayList_brooderInventory.size(); i++) {
                    //check if generation to be inserted is already in the database
                    Cursor cursor = myDb.getAllDataFromBrooderInventoryWhereID(arrayList_brooderInventory.get(i).getId());
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
    @Override
    public void onBackPressed() {


        finish();
        startActivity(getIntent());

    }
}

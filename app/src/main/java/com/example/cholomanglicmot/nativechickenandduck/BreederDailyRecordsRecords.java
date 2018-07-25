package com.example.cholomanglicmot.nativechickenandduck;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class BreederDailyRecordsRecords extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private EggProductionFragment eggProductionFragment;
    private FeedingRecordsFragment feedingRecordsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_daily_records_records);
        FabSpeedDial fabSpeedDial = findViewById(R.id.fabSpeedDial);
        toolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Breeder Daily Records");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        eggProductionFragment = new EggProductionFragment();
        feedingRecordsFragment = new FeedingRecordsFragment();
        setFragment(eggProductionFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.egg_production:
                        setFragment(eggProductionFragment);
                        return true;
                    case R.id.feeding_records:
                        setFragment(feedingRecordsFragment);
                        return true;
                    default:
                        return false;
                }

            }
        });



        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.egg_production_btn:
                        //Toast.makeText(BreederDailyRecordsRecords.this,"hello egg", Toast.LENGTH_SHORT).show();
                        Intent intent_egg = new Intent(BreederDailyRecordsRecords.this, BreederDailyRecordsAddEgg.class);
                        startActivity(intent_egg);
                        return true;


                    case R.id.feeding_records_btn:
                        //Toast.makeText(BreederDailyRecordsRecords.this,"hello feeds", Toast.LENGTH_SHORT).show();
                        Intent intent_feeding = new Intent(BreederDailyRecordsRecords.this, BreederDailyRecordsAddFeeding.class);
                        startActivity(intent_feeding);
                        return true;


                }
                return true;


            }

            @Override
            public void onMenuClosed() {

            }
        });




    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.breeder_daily_records_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}

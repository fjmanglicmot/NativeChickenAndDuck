package com.example.cholomanglicmot.nativechickenandduck;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class BreederDailyRecordsAddEgg extends AppCompatActivity {

    private Toolbar toolbar;
    private Button button;
    private EditText egg_date_collected,egg_total_intact,egg_total_weight_intact,egg_ave_egg_weight,egg_no_broken,egg_no_soft_or_shlss;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_daily_records_add_egg);
        toolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Breeder");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        egg_date_collected = findViewById(R.id.egg_date_collected);
        egg_total_intact = findViewById(R.id.egg_total_intact);
        egg_total_weight_intact = findViewById(R.id.egg_total_weight_intact);
        egg_ave_egg_weight = findViewById(R.id.egg_ave_egg_weight);
        egg_no_broken = findViewById(R.id.egg_no_broken);
        egg_no_soft_or_shlss = findViewById(R.id.egg_no_soft_or_shlss);

        button = findViewById(R.id.add_egg_submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!egg_date_collected.getText().toString().isEmpty() && !egg_total_intact.getText().toString().isEmpty()&& !egg_total_weight_intact.getText().toString().isEmpty()&& !egg_ave_egg_weight.getText().toString().isEmpty()&& !egg_no_broken.getText().toString().isEmpty()&& !egg_no_soft_or_shlss.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Egg Production Record added to database", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().popBackStack();
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().popBackStack();

                }
            }
        });

        egg_date_collected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(BreederDailyRecordsAddEgg.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        egg_date_collected.setText(selectedDay + "/" + selectedDay + "/" + selectedYear);
                        calendar.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}

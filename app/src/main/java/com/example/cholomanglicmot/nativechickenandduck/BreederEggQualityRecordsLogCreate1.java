package com.example.cholomanglicmot.nativechickenandduck;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class BreederEggQualityRecordsLogCreate1 extends AppCompatActivity {
    EditText breeder_egg_quality_records_date_eggset,breeder_egg_quality_records_noEggs;
    private Calendar calendar;
    private Button breeder_egg_quality_records_submit;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_egg_quality_records_log_create1);
        breeder_egg_quality_records_date_eggset = findViewById(R.id.breeder_egg_quality_records_date_eggset);

        mToolbar = findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Breeder");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        breeder_egg_quality_records_date_eggset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(BreederEggQualityRecordsLogCreate1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        breeder_egg_quality_records_date_eggset.setText(selectedDay + "/" + selectedDay + "/" + selectedYear);
                        calendar.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });


        breeder_egg_quality_records_submit = findViewById(R.id.breeder_egg_quality_records_submit);
        breeder_egg_quality_records_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breeder_egg_quality_records_noEggs = findViewById(R.id.breeder_egg_quality_records_noEggs);
                breeder_egg_quality_records_date_eggset = findViewById(R.id.breeder_egg_quality_records_date_eggset);

                String breeder_egg_quality_records_noEggs_text = breeder_egg_quality_records_noEggs.getText().toString();
                String breeder_egg_quality_records_date_eggset_text = breeder_egg_quality_records_date_eggset.getText().toString();

                Intent intent_breeder_egg_quality_records_log = new Intent(BreederEggQualityRecordsLogCreate1.this, BreederEggQualityRecordsLog.class);
                intent_breeder_egg_quality_records_log.putExtra("breeder_egg_quality_records_noEggs", breeder_egg_quality_records_noEggs_text);
                intent_breeder_egg_quality_records_log.putExtra("breeder_egg_quality_records_date_eggset", breeder_egg_quality_records_date_eggset_text);
                startActivity(intent_breeder_egg_quality_records_log);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

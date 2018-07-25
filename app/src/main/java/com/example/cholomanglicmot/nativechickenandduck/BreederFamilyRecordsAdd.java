package com.example.cholomanglicmot.nativechickenandduck;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


public class BreederFamilyRecordsAdd extends AppCompatActivity {

    private Toolbar toolbar;
    private Button submit_button;
    private EditText family_id, date_transferred, date_hatched, date_of_first_egg;
    private Calendar calendar,calendar1,calendar2;
    private Spinner generation,line,pen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_family_records_add);
        toolbar = (Toolbar)findViewById(R.id.nav_action);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Breeder");
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        submit_button = findViewById(R.id.add_family_submit_button);
        family_id = findViewById(R.id.breeder_family_records_add_familyID);
        date_transferred = findViewById(R.id.breeder_family_records_add_dateTransferred);
        date_of_first_egg = findViewById(R.id.breeder_family_records_add_dateFirstEgg);
        date_hatched = findViewById(R.id.breeder_family_records_add_dateHatched);



        date_transferred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(BreederFamilyRecordsAdd.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        date_transferred.setText(selectedDay + "/" + selectedDay + "/" + selectedYear);
                        calendar.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();
            }
        });

        date_of_first_egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar1 = Calendar.getInstance();
                int year1 = calendar1.get(Calendar.YEAR);
                int month1 = calendar1.get(Calendar.MONTH);
                int day1 = calendar1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatepicker1 = new DatePickerDialog(BreederFamilyRecordsAdd.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker1, int selectedYear1, int selectedMonth1, int selectedDay1) {
                        date_of_first_egg.setText(selectedDay1 + "/" + selectedDay1 + "/" + selectedYear1);
                        calendar1.set(selectedYear1,selectedMonth1,selectedDay1);
                    }
                }, year1, month1, day1);
                mDatepicker1.show();

            }
        });

        date_hatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar2 = Calendar.getInstance();
                int year2 = calendar2.get(Calendar.YEAR);
                int month2 = calendar2.get(Calendar.MONTH);
                int day2 = calendar2.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatepicker3 = new DatePickerDialog(BreederFamilyRecordsAdd.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker2, int selectedYear2, int selectedMonth2, int selectedDay2) {
                        date_hatched.setText(selectedDay2 + "/" + selectedDay2 + "/" + selectedYear2);
                        calendar2.set(selectedYear2,selectedMonth2,selectedDay2);
                    }
                }, year2, month2, day2);
                mDatepicker3.show();

            }
        });


        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!family_id.getText().toString().isEmpty() && !date_transferred.getText().toString().isEmpty()&& !date_of_first_egg.getText().toString().isEmpty()&& !date_hatched.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Family Record added to database", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().popBackStack();
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().popBackStack();

                }
            }
        });




    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



}

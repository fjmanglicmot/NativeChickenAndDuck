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

public class ReplacementFeedingRecordAdd extends AppCompatActivity {

    private Toolbar toolbar;
    private Button button;
    private EditText feeding_date_collected,feeding_amount_concentrates_offered, feeding_amount_concentrates_refused;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_feeding_record_add);
        toolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Replacement");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        feeding_date_collected = findViewById(R.id.feeding_date_collected);
        feeding_amount_concentrates_offered = findViewById(R.id.feeding_amount_concentrates_offered);
        feeding_amount_concentrates_refused = findViewById(R.id.feeding_amount_concentrates_refused);


        button = findViewById(R.id.add_feeding_submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!feeding_date_collected.getText().toString().isEmpty() && !feeding_amount_concentrates_offered.getText().toString().isEmpty()&& !feeding_amount_concentrates_refused.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Feeding Records added to database", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().popBackStack();
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().popBackStack();

                }
            }
        });

        feeding_date_collected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(ReplacementFeedingRecordAdd.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        feeding_date_collected.setText(selectedDay + "/" + selectedDay + "/" + selectedYear);
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

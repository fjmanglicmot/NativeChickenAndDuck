package com.example.cholomanglicmot.nativechickenandduck;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class ReplacementIndividualRecordAdd extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText replacement_add_date_hatched,replacement_add_date_transferred;
    private Spinner replacement_add_fam, replacement_add_gen,replacement_add_line,replacement_add_pen;
    private Button replacement_add_submit_button;
    private Calendar calendar,calendar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement_individual_record_add);
        toolbar = findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Replacement");
        replacement_add_date_hatched = findViewById(R.id.replacement_add_date_hatched);
        replacement_add_date_transferred = findViewById(R.id.replacement_add_date_transferred);
        replacement_add_fam = findViewById(R.id.replacement_add_fam);
        replacement_add_gen = findViewById(R.id.replacement_add_gen);
        replacement_add_line = findViewById(R.id.replacement_add_line);
        replacement_add_pen = findViewById(R.id.replacement_add_pen);
        replacement_add_submit_button = findViewById(R.id.replacement_add_submit_button);

        replacement_add_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!replacement_add_date_hatched.getText().toString().isEmpty() && !replacement_add_date_transferred.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Individual Record added to database", Toast.LENGTH_SHORT).show();
                    //getSupportFragmentManager().popBackStack();
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                   // getSupportFragmentManager().popBackStack();

                }
            }
        });

        replacement_add_date_hatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(ReplacementIndividualRecordAdd.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        replacement_add_date_hatched.setText(selectedDay + "/" + selectedDay + "/" + selectedYear);
                        calendar.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });

        replacement_add_date_transferred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar2 = Calendar.getInstance();
                int year = calendar2.get(Calendar.YEAR);
                int month = calendar2.get(Calendar.MONTH);
                int day = calendar2.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker1 = new DatePickerDialog(ReplacementIndividualRecordAdd.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        replacement_add_date_transferred.setText(selectedDay + "/" + selectedDay + "/" + selectedYear);
                        calendar2.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker1.show();

            }
        });



    }
}

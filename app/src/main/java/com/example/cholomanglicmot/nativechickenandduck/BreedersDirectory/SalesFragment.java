package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class SalesFragment extends Fragment {
    private Button account_submit_button;
    private TextView profile_id;
    private EditText date_sold, male_sold, female_sold, price, remarks;
    DatabaseHelper myDb;
    Calendar calendar;

    public SalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sales, container, false);
        myDb = new DatabaseHelper(getContext());

        date_sold = view.findViewById(R.id.date_sold);
        male_sold = view.findViewById(R.id.male_sold);
        female_sold = view.findViewById(R.id.female_sold);
        price = view.findViewById(R.id.price);
        remarks = view.findViewById(R.id.remarks);

        date_sold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        selectedMonth++;
                        date_sold.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
                        calendar.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });


        account_submit_button = view.findViewById(R.id.action_ok);



        account_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!date_sold.getText().toString().isEmpty() && !male_sold.getText().toString().isEmpty() && !female_sold.getText().toString().isEmpty() && !price.getText().toString().isEmpty()){
                    boolean isInserted = myDb.insertDataMortalityAndSales(date_sold.getText().toString(), null,null,null,"breeder","sold", Integer.parseInt(price.getText().toString()), Integer.parseInt(male_sold.getText().toString()), Integer.parseInt(female_sold.getText().toString()), Integer.parseInt(male_sold.getText().toString())+Integer.parseInt(female_sold.getText().toString()), null, remarks.getText().toString(), null);
                    if(isInserted == true){
                        Toast.makeText(getActivity(),"Sales added to database", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    }
                }else{
                    Toast.makeText(getActivity(),"Please fill empty fields", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                }

            }
        });
        return view;
    }


}

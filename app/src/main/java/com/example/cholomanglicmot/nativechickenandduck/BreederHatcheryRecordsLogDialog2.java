package com.example.cholomanglicmot.nativechickenandduck;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class BreederHatcheryRecordsLogDialog2  extends DialogFragment{

    private static final String TAG = "BreederHatcheryRecordsLogDialog2";
    private EditText breeder_hatching_records_noHatched,breeder_hatching_records_date_hatched,breeder_hatching_records_remarks;
    private Spinner breeder_hatching_records_moved_to_pen;
    private Calendar calendar;
    private Button mActionOk;
    private BreederHatcheryRecordsLogDialog2.DialogListener dialogListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_breeder_hatchery_records_dialog2, container, false);

        mActionOk = view.findViewById(R.id.action_ok);
        breeder_hatching_records_noHatched = view.findViewById(R.id.breeder_hatching_records_noHatched);
        breeder_hatching_records_date_hatched = view.findViewById(R.id.breeder_hatching_records_date_hatched);
        breeder_hatching_records_remarks = view.findViewById(R.id.breeder_hatching_records_remarks);
        breeder_hatching_records_moved_to_pen = view.findViewById(R.id.breeder_hatching_records_moved_to_pen);


        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!breeder_hatching_records_noHatched.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Added to database", Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                    String number_hatched = breeder_hatching_records_noHatched.getText().toString();
                    String date_hatched = breeder_hatching_records_date_hatched.getText().toString();
                    String moved_to_pen = breeder_hatching_records_moved_to_pen.getSelectedItem().toString();
                    String remarks = breeder_hatching_records_remarks.getText().toString();
                    dialogListener.applyTexts(number_hatched, date_hatched, moved_to_pen, remarks);

                }else{
                    Toast.makeText(getActivity(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                }


            }
        });
        breeder_hatching_records_date_hatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        breeder_hatching_records_date_hatched.setText(selectedDay + "/" + selectedDay + "/" + selectedYear);
                        calendar.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });

        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dialogListener = (BreederHatcheryRecordsLogDialog2.DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Must implement dialog listener");
        }
    }

    public interface DialogListener{
        void applyTexts(String number_hatched, String date_hatched, String moved_to_pen, String remarks);
    }
}

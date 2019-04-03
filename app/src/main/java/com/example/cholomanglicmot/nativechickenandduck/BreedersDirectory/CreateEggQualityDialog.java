package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateEggQualityDialog extends DialogFragment{
    private EditText date, weight, color, shape, length, width, albumen_height, albumen_weight, yolk_weight,yolk_color, shell_weight, top_shell, middle_shell, bottom_shell,remarks;
    ImageButton deleted_at;
    private RadioGroup radio_group_collection_day;
    private RadioButton radioButton,radioButton2,radioButton3;


    private Button mActionOk;
    DatabaseHelper myDb;
    Calendar calendar;
    ArrayList<Breeder_Inventory> arrayListBrooderInventory = new ArrayList<>();
    ArrayList<Breeder_Inventory>arrayList_temp = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create_egg_quality_records, container, false);
        myDb = new DatabaseHelper(getContext());
        final String breeder_tag = getArguments().getString("Breeder Tag");


        date = view.findViewById(R.id.date);
        radio_group_collection_day = view.findViewById(R.id.radio_group_collection_day);
        radioButton = view.findViewById(R.id.radioButton);
        radioButton2 = view.findViewById(R.id.radioButton2);
        radioButton3 = view.findViewById(R.id.radioButton3);
        weight = view.findViewById(R.id.weight);
        color = view.findViewById(R.id.color);
        shape = view.findViewById(R.id.shape);
        length = view.findViewById(R.id.length);
        width = view.findViewById(R.id.width);
        albumen_height = view.findViewById(R.id.albument_height);
        albumen_weight = view.findViewById(R.id.albument_weight);
        yolk_weight = view.findViewById(R.id.yolk_weight);
        yolk_color = view.findViewById(R.id.yolk_color);
        shell_weight = view.findViewById(R.id.shell_weight);
        top_shell = view.findViewById(R.id.shell_thickness_top);
        middle_shell = view.findViewById(R.id.shell_thickness_middle);
        bottom_shell = view.findViewById(R.id.shell_thickness_bottom);

        deleted_at = view.findViewById(R.id.delete);


        final int day_35 = 1000;
        final int day_40 = 1001;
        final int day_60 = 1002;

        radioButton.setId(day_35);
        radioButton2.setId(day_40);
        radioButton3.setId(day_60);

        date.setOnClickListener(new View.OnClickListener() {
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
                        date.setText(selectedYear + "-" + selectedMonth + "-" + selectedDay);
                        calendar.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });




        mActionOk = view.findViewById(R.id.action_ok);
        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!date.getText().toString().isEmpty()  ){

                    int selectedDay = radio_group_collection_day.getCheckedRadioButtonId();
                    int brooder_growth_collection = 0;
                    // if(!())
                    switch (selectedDay) {
                        case day_35:
                            // the first RadioButton is checked.
                            brooder_growth_collection = 35;

                            break;
                        //other checks for the other RadioButtons ids from the RadioGroup
                        case day_40:
                            // the first RadioButton is checked.
                            brooder_growth_collection = 40;
                            break;
                        //other checks for the other RadioButtons ids from the RadioGroup
                        case day_60:
                            // the first RadioButton is checked.
                            brooder_growth_collection = 60;
                            break;
                        //other checks for the other RadioButtons ids from the RadioGroup
                        case -1:
                            brooder_growth_collection = 0;
                            // no RadioButton is checked inthe Radiogroup
                            break;
                    }
                    //kunin mo yung

                    //kunin mo muna yung id ng breeder_inv given yung tag

                    Cursor cursor = myDb.getAllDataFromBreederInventory();
                    cursor.moveToFirst();

                    if(cursor.getCount() != 0){
                        do{
                            Breeder_Inventory breeder_inventory = new  Breeder_Inventory(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8),cursor.getString(9));
                            arrayListBrooderInventory.add(breeder_inventory);

                        }while(cursor.moveToNext());
                    }


                    for(int i=0;i<arrayListBrooderInventory.size();i++){
                        if(arrayListBrooderInventory.get(i).getBrooder_inv_brooder_tag().equals(breeder_tag)){
                            arrayList_temp.add(arrayListBrooderInventory.get(i));
                        }
                    }

////////////////////////
                    boolean isInserted = myDb.insertEggQualityRecords(arrayList_temp.get(0).getId(),
                            date.getText().toString(),brooder_growth_collection,
                            Float.parseFloat(weight.getText().toString()),
                            color.getText().toString() , shape.getText().toString(),
                            Float.parseFloat(length.getText().toString()),
                            Float.parseFloat(width.getText().toString()),
                            Float.parseFloat(albumen_height.getText().toString()),
                            Float.parseFloat(albumen_weight.getText().toString()),
                            Float.parseFloat(yolk_weight.getText().toString()),
                            yolk_color.getText().toString(),
                            Float.parseFloat(shell_weight.getText().toString()),
                            Float.parseFloat(top_shell.getText().toString()),
                            Float.parseFloat(middle_shell.getText().toString()),
                            Float.parseFloat(bottom_shell.getText().toString()));

                    if (isInserted == true){
                        Toast.makeText(getContext(),"Egg Quality added to database", Toast.LENGTH_LONG).show();
                        Intent intent_line = new Intent(getActivity(), EggQualityRecords.class);
                        intent_line.putExtra("Breeder Tag", breeder_tag);
                        startActivity(intent_line);

                    }else{
                        Toast.makeText(getContext(),"Error adding to database", Toast.LENGTH_LONG).show();
                    }







                }else{
                    Toast.makeText(getContext(),"Please fill any empty fields", Toast.LENGTH_LONG).show();
                }




            }

        });

        return view;
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
package com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory;

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
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateBrooderFeedingRecordDialog extends DialogFragment {

    private static final String TAG = "CreateFamilyDialog";
    private EditText brooder_feeding_date_collected, brooder_feeding_record_offered,brooder_feeding_record_refused,brooder_feeding_record_remarks;
    private Button mActionOk;
    DatabaseHelper myDb;
    Calendar calendar;
    ArrayList<Brooder_Inventory>arrayListBrooderInventory = new ArrayList<>();
    ArrayList<Brooder_Inventory>arrayList_temp = new ArrayList<>();


    Map<Integer, ArrayList<Float>> inventory_dictionary = new LinkedHashMap<Integer, ArrayList<Float>>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create_brooder_feeding_records, container, false);
        myDb = new DatabaseHelper(getContext());
        final String brooder_pen = getArguments().getString("brooder pen number");


        brooder_feeding_date_collected = view.findViewById(R.id.brooder_feeding_date_collected);
        brooder_feeding_record_offered = view.findViewById(R.id.brooder_feeding_offered);
        brooder_feeding_record_refused = view.findViewById(R.id.brooder_feeding_record_refused);
        brooder_feeding_record_remarks = view.findViewById(R.id.brooder_feeding_record_remarks);

        brooder_feeding_date_collected.setOnClickListener(new View.OnClickListener() {
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
                        brooder_feeding_date_collected.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
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
                if(!brooder_feeding_date_collected.getText().toString().isEmpty() && !brooder_feeding_record_offered.getText().toString().isEmpty() && !brooder_feeding_record_refused.getText().toString().isEmpty()){
                    //kunin mo yung


////////////////////////

                    Cursor cursor_brooder_inventory = myDb.getAllDataFromBrooderInventory(); //para sa pagstore ng data sa arraylist
                    cursor_brooder_inventory.moveToFirst();
                    if(cursor_brooder_inventory.getCount() == 0){
                        //show message

                        Toast.makeText(getActivity(),"Add Brooders first", Toast.LENGTH_LONG).show();

                    }else {
                        do {

                            Brooder_Inventory brooder_inventory = new Brooder_Inventory(cursor_brooder_inventory.getInt(0),cursor_brooder_inventory.getInt(1), cursor_brooder_inventory.getString(2), cursor_brooder_inventory.getString(3),cursor_brooder_inventory.getString(4), cursor_brooder_inventory.getInt(5), cursor_brooder_inventory.getInt(6),cursor_brooder_inventory.getInt(7), cursor_brooder_inventory.getString(8), cursor_brooder_inventory.getString(9));
                            arrayListBrooderInventory.add(brooder_inventory);


                        } while (cursor_brooder_inventory.moveToNext());
                    }

                     for (int i=0;i<arrayListBrooderInventory.size();i++){
                            if(arrayListBrooderInventory.get(i).getBrooder_inv_pen().equals(brooder_pen) ){

                                arrayList_temp.add(arrayListBrooderInventory.get(i)); //ito na yung list ng inventory na nasa pen

                        }
                    }

                    //GETTING TOTAL BROODER INVENTORY COUNT
                    int count_inventory = 0;

                    for(int i=0;i<arrayList_temp.size();i++){
                        count_inventory = count_inventory+arrayList_temp.get(i).getBrooder_total_quantity();
                    }



                    ArrayList<Integer> arrayListBrooder = new ArrayList<>();
                    for(int i = 0;i<arrayList_temp.size();i++){
                        if(arrayListBrooder.contains(arrayList_temp.get(i).getBrooder_inv_brooder_id())){
                            //do nothing
                        }else{
                            arrayListBrooder.add(arrayList_temp.get(i).getBrooder_inv_brooder_id());
                        }
                    }

                    //create arraylist with 0 as initial elements which is given to inventory_dictionary
                    ArrayList<Float> float_zero = new ArrayList<>();
                    float_zero.add(0.0f);
                    float_zero.add(0.0f);
                    for(int i = 0;i<arrayListBrooder.size();i++){
                        inventory_dictionary.put(arrayListBrooder.get(i),float_zero);
                    }


                    StringBuffer buffer = new StringBuffer();
                    //populating inventory_dictionary
                    ArrayList<Integer> arrayList = new ArrayList<>();

                    for (int i=0;i<arrayList_temp.size();i++){
                        if(inventory_dictionary.get(arrayList_temp.get(i).getBrooder_inv_brooder_id()) != null && !arrayList.contains(arrayList_temp.get(i).getBrooder_inv_brooder_id())){
                            ArrayList<Float> arrayList2 = new ArrayList<>();
                            arrayList2.add((float)arrayList_temp.get(i).getBrooder_total_quantity());
                            arrayList2.add((float)arrayList_temp.get(i).getBrooder_total_quantity());
                            inventory_dictionary.put(arrayList_temp.get(i).getBrooder_inv_brooder_id(),arrayList2);
                            arrayList.add(arrayList_temp.get(i).getBrooder_inv_brooder_id());
                        }else if(arrayList.contains(arrayList_temp.get(i).getBrooder_inv_brooder_id())){


                            ArrayList<Float> arrayList3 = new ArrayList<>();
                            arrayList3.add(inventory_dictionary.get(arrayList_temp.get(i).getBrooder_inv_brooder_id()).get(0)+arrayList_temp.get(i).getBrooder_total_quantity());
                            arrayList3.add(inventory_dictionary.get(arrayList_temp.get(i).getBrooder_inv_brooder_id()).get(1)+arrayList_temp.get(i).getBrooder_total_quantity());


                            inventory_dictionary.put(arrayList_temp.get(i).getBrooder_inv_brooder_id(),arrayList3);
                        }
                    }
                   //buffer.append("inventory_dictionary  "+inventory_dictionary.toString()+"\n\n");


                    ////important values:
                    //1. inventory_dictionary
                    //2. count_inventory


                                            /////////////////////////////////////////////////////////////////

                    ///A. COMPUTING FOR THE MULTIPLIER
                    float feedOffered = Float.parseFloat(brooder_feeding_record_offered.getText().toString());
                    float feedRefused = Float.parseFloat(brooder_feeding_record_refused.getText().toString());
                    float multiplierOffered = feedOffered/count_inventory;
                    float mutiplierRefused = feedRefused/count_inventory;


                    ///B. REPLACE THE VALUES OF THE KEYS IN inventory_dictionary WITH THEIR COUNT

                    ArrayList<Integer> arrayList1 = new ArrayList<>();
                    for (int i=0;i<arrayList_temp.size();i++){
                        if(arrayList1.contains(arrayList_temp.get(i).getBrooder_inv_brooder_id())){
                            //skip
                        }else{

                            ArrayList<Float> multiplier = new ArrayList<>();
                            multiplier.add(inventory_dictionary.get(arrayList_temp.get(i).getBrooder_inv_brooder_id()).get(0)*multiplierOffered);
                            multiplier.add(inventory_dictionary.get(arrayList_temp.get(i).getBrooder_inv_brooder_id()).get(1)*mutiplierRefused);
                            inventory_dictionary.put(arrayList_temp.get(i).getBrooder_inv_brooder_id(),multiplier);
                            arrayList1.add(arrayList_temp.get(i).getBrooder_inv_brooder_id());
                        }
                    }



/*                    buffer.append("feedOffered  "+feedOffered+"\n");
                    buffer.append("count_inventory  "+count_inventory+"\n");
                    buffer.append("count_inventory  "+result+"\n");
                    buffer.append("inventory_dictionary  "+inventory_dictionary.toString()+"\n");

                    buffer.append("multiplier  "+multiplierOffered+"\n\n");

                    showMessage("yo", buffer.toString());*/

                    ///C. INSERTING FEEDING RECORD TO THE DATABASE BY BATCH

                    for ( Map.Entry<Integer, ArrayList<Float>> entry : inventory_dictionary.entrySet()) {
                        Integer key = entry.getKey();
                        Float valueOffered = entry.getValue().get(0);
                        Float valueRefused = entry.getValue().get(1);
                        // do something with key and/or tab
                        boolean isInserted = myDb.insertDataBrooderFeedingRecords( key ,brooder_feeding_date_collected.getText().toString(), valueOffered, valueRefused,brooder_feeding_record_remarks.getText().toString(),null);
                        if(isInserted){
                            //continue
                        }else{
                            Toast.makeText(getContext(),"Error inserting record with Inventory id: "+key, Toast.LENGTH_SHORT).show();
                            getDialog().dismiss();

                        }
                    }

                    Toast.makeText(getContext(),"Added to database", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), BrooderFeedingRecordsActivity.class);
                    intent.putExtra("Brooder Pen",brooder_pen);
                    startActivity(intent);





                   /* boolean isInserted = myDb.insertDataBrooderFeedingRecords( n ,brooder_feeding_date_collected.getText().toString(), parseInt(brooder_feeding_record_offered.getText().toString()), parseInt(brooder_feeding_record_refused.getText().toString()),brooder_feeding_record_remarks.getText().toString(),null);
                    if(!isInserted){
                        Toast.makeText(getContext(),"Not added to database", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getContext(),"Added to database", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), BrooderFeedingRecordsActivity.class);
                        startActivity(intent);
                    }*/

                    getDialog().dismiss();
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
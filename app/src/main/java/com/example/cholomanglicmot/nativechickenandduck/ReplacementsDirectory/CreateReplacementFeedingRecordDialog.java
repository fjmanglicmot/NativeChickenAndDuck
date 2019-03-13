package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateReplacementFeedingRecordDialog extends DialogFragment {

    private static final String TAG = "CreateReplacementGrowthRecordDialog";
    private EditText replacement_feeding_date_collected, replacement_feeding_record_offered,replacement_feeding_record_refused,replacement_feeding_record_remarks;
    private TextView textView;
    private Button mActionOk;
    DatabaseHelper myDb;
    Calendar calendar;
    ArrayList<Replacement_Inventory> arrayListReplacementInventory = new ArrayList<>();
    ArrayList<Replacement_Inventory>arrayList_temp = new ArrayList<>();
    ArrayList<Replacement_Inventory>arrayList_temp1 = new ArrayList<>();
    ArrayList<Replacements> arrayListReplacements = new ArrayList<>();


    Map<Integer, ArrayList<Float>> inventory_dictionary = new LinkedHashMap<Integer, ArrayList<Float>>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create_replacement_feeding_records, container, false);
        myDb = new DatabaseHelper(getContext());
        final String replacement_pen = getArguments().getString("Replacement Pen");
        final StringBuffer buffer = new StringBuffer();


        replacement_feeding_date_collected = view.findViewById(R.id.replacement_feeding_date_collected);
        replacement_feeding_record_offered = view.findViewById(R.id.replacement_feeding_record_offered);
        replacement_feeding_record_refused = view.findViewById(R.id.replacement_feeding_record_refused);
        replacement_feeding_record_remarks = view.findViewById(R.id.replacement_feeding_record_remarks);
        textView = view.findViewById(R.id.textView);

        textView.setText(replacement_pen+ " Feeding Records");

        replacement_feeding_date_collected.setOnClickListener(new View.OnClickListener() {
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
                        replacement_feeding_date_collected.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
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
                if(!replacement_feeding_date_collected.getText().toString().isEmpty() && !replacement_feeding_record_offered.getText().toString().isEmpty() && !replacement_feeding_record_refused.getText().toString().isEmpty()){
                    //kunin mo yung


////////////////////////
        ///GET ALL REPLACEMENTS FROM DATABASE  FIRST
                    Cursor cursor_replacement = myDb.getAllDataFromReplacements();
                    cursor_replacement.moveToFirst();

                    if(cursor_replacement.getCount() == 0){
                        Toast.makeText(getContext(),"No data on Replacement Table", Toast.LENGTH_LONG).show();
                    }else{
                        do{

                            Replacements replacements = new Replacements(cursor_replacement.getInt(0),cursor_replacement.getString(1), cursor_replacement.getString(2), cursor_replacement.getString(3), cursor_replacement.getString(4), cursor_replacement.getString(5));
                            arrayListReplacements.add(replacements);
                        }while (cursor_replacement.moveToNext());
                    }
        ///THEN, GET ALL REPLACEMENT_INVENTORIES SA DATABASE
                    Cursor curse = myDb.getAllDataFromReplacementInventory();
                    curse.moveToFirst();

                    if(curse.getCount() == 0){
                        //show message
                        Toast.makeText(getContext(),"No data.", Toast.LENGTH_LONG).show();

                    }else {

                        do {

                            for(int i = 0; i<arrayListReplacements.size();i++){
                                if(arrayListReplacements.get(i).getId().equals(curse.getInt(1))){
                                    Replacement_Inventory replace = new Replacement_Inventory(curse.getInt(0),curse.getInt(1), arrayListReplacements.get(i).getReplacement_family_number(),  arrayListReplacements.get(i).getReplacement_line_number(), arrayListReplacements.get(i).getReplacement_generation_number(), curse.getString(2), curse.getString(3),curse.getString(4), curse.getInt(5), curse.getInt(6), curse.getInt(7), arrayListReplacements.get(i).getReplacement_date_added(),curse.getString(8),curse.getString(9));
                                    arrayList_temp.add(replace);

                                }
                            }

                            } while (curse.moveToNext());

                    }



          /*          for (int i=0;i<arrayList_temp.size();i++){
                        buffer.append(arrayList_temp.get(i).getId()+"\n");
                        buffer.append(arrayList_temp.get(i).getReplacement_inv_replacement_id()+"\n");
                        buffer.append(arrayList_temp.get(i).getReplacement_inv_pen()+"\n\n");
                    }

*/
                    for(int i =0;i<arrayList_temp.size();i++){
                        if (arrayList_temp.get(i).getReplacement_inv_pen().equals(replacement_pen)){
                            arrayList_temp1.add(arrayList_temp.get(i));
                        }
                    }
           /*         for (int i=0;i<arrayList_temp1.size();i++){
                        buffer.append(arrayList_temp1.get(i).getId()+"\n");
                        buffer.append(arrayList_temp1.get(i).getReplacement_inv_replacement_id()+"\n");
                        buffer.append(arrayList_temp1.get(i).getReplacement_inv_pen()+"\n\n");
                    }
*/

                    //GETTING TOTAL BROODER INVENTORY COUNT

                    //DAPAT YUNG NASA PEN LANG NA GIVEN, ANG GINAGAWA MO, COUNT NG LAHAT
                    int count_inventory = 0;

                    for(int i=0;i<arrayList_temp1.size();i++){
                        count_inventory = count_inventory+arrayList_temp1.get(i).getReplacement_total_quantity();
                    }

                   // buffer.append("count from arraylisttemp= " + count_inventory+"\n");



                    ArrayList<Integer> arrayListBrooder = new ArrayList<>();
                    for(int i = 0;i<arrayList_temp1.size();i++){
                        if(arrayListBrooder.contains(arrayList_temp1.get(i).getReplacement_inv_replacement_id())){
                            //do nothing
                        }else{
                            arrayListBrooder.add(arrayList_temp1.get(i).getReplacement_inv_replacement_id());
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

                    for (int i=0;i<arrayList_temp1.size();i++){
                        if(inventory_dictionary.get(arrayList_temp1.get(i).getReplacement_inv_replacement_id()) != null && !arrayList.contains(arrayList_temp1.get(i).getReplacement_inv_replacement_id())){
                            ArrayList<Float> arrayList2 = new ArrayList<>();
                            arrayList2.add((float)arrayList_temp1.get(i).getReplacement_total_quantity());
                            arrayList2.add((float)arrayList_temp1.get(i).getReplacement_total_quantity());
                            inventory_dictionary.put(arrayList_temp1.get(i).getReplacement_inv_replacement_id(),arrayList2);
                            arrayList.add(arrayList_temp1.get(i).getReplacement_inv_replacement_id());
                        }else if(arrayList.contains(arrayList_temp1.get(i).getReplacement_inv_replacement_id())){


                            ArrayList<Float> arrayList3 = new ArrayList<>();
                            arrayList3.add(inventory_dictionary.get(arrayList_temp1.get(i).getReplacement_inv_replacement_id()).get(0)+arrayList_temp1.get(i).getReplacement_total_quantity());
                            arrayList3.add(inventory_dictionary.get(arrayList_temp1.get(i).getReplacement_inv_replacement_id()).get(1)+arrayList_temp1.get(i).getReplacement_total_quantity());


                            inventory_dictionary.put(arrayList_temp1.get(i).getReplacement_inv_replacement_id(),arrayList3);
                        }
                    }
                   //buffer.append("inventory_dictionary  "+inventory_dictionary.toString()+"\n\n");


                    ////important values:
                    //1. inventory_dictionary
                    //2. count_inventory


                                            /////////////////////////////////////////////////////////////////

                    ///A. COMPUTING FOR THE MULTIPLIER
                    float feedOffered = Float.parseFloat(replacement_feeding_record_offered.getText().toString());
                    float feedRefused = Float.parseFloat(replacement_feeding_record_refused.getText().toString());
                    float multiplierOffered = feedOffered/count_inventory;
                    float mutiplierRefused = feedRefused/count_inventory;


                    ///B. REPLACE THE VALUES OF THE KEYS IN inventory_dictionary WITH THEIR COUNT

                    ArrayList<Integer> arrayList1 = new ArrayList<>();
                    for (int i=0;i<arrayList_temp1.size();i++){
                        if(arrayList1.contains(arrayList_temp1.get(i).getReplacement_inv_replacement_id())){
                            //skip
                        }else{

                            ArrayList<Float> multiplier = new ArrayList<>();
                            multiplier.add(inventory_dictionary.get(arrayList_temp1.get(i).getReplacement_inv_replacement_id()).get(0)*multiplierOffered);
                            multiplier.add(inventory_dictionary.get(arrayList_temp1.get(i).getReplacement_inv_replacement_id()).get(1)*mutiplierRefused);
                            inventory_dictionary.put(arrayList_temp1.get(i).getReplacement_inv_replacement_id(),multiplier);
                            arrayList1.add(arrayList_temp1.get(i).getReplacement_inv_replacement_id());
                        }
                    }




                    ///C. INSERTING FEEDING RECORD TO THE DATABASE BY BATCH

                    for ( Map.Entry<Integer, ArrayList<Float>> entry : inventory_dictionary.entrySet()) {
                        Integer key = entry.getKey();
                        Float valueOffered = entry.getValue().get(0);
                        Float valueRefused = entry.getValue().get(1);
                        // do something with key and/or tab
                        boolean isInserted = myDb.insertDataReplacementFeedingRecords( key ,replacement_feeding_date_collected.getText().toString(), valueOffered, valueRefused,replacement_feeding_record_remarks.getText().toString(),null);
                        if(isInserted){
                            //continue
                        }else{
                            Toast.makeText(getContext(),"Error inserting record with Inventory id: "+key, Toast.LENGTH_SHORT).show();
                            getDialog().dismiss();

                        }
                    }

                    Toast.makeText(getContext(),"Added to database", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), ReplacementFeedingRecordsActivity.class);
                    intent.putExtra("Replacement Pen",replacement_pen);
                   // startActivity(intent);





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

                showMessage("arraylist_temp",buffer.toString()+"\n");


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
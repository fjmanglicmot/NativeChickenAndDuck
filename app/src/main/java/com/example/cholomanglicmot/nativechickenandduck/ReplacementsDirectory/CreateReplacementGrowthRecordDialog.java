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
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateReplacementGrowthRecordDialog extends DialogFragment {

    private static final String TAG = "CreateFamilyDialog";
    private EditText brooder_growth_other_day,brooder_growth_date_added,brooder_growth_total_weight,brooder_growth_male_weight,brooder_growth_female_weight;
    private TextView textView;
    private RadioGroup radio_group_collection_day;
    private RadioButton radioButton,radioButton2,radioButton3;
    private LinearLayout linear_total, linear_male, linear_female;


    private Button mActionOk;
    DatabaseHelper myDb;
    Calendar calendar;
    ArrayList<Replacement_Inventory>arrayListBrooderInventory = new ArrayList<>();
    ArrayList<Replacement_Inventory>arrayList_temp = new ArrayList<>();
    Switch other_day_switch, sexing_switch;
    //ArrayList<Replacement_Inventory>arrayList_temp = new ArrayList<>();
    ArrayList<Replacement_Inventory>arrayList_temp1 = new ArrayList<>();
    ArrayList<Replacements> arrayListReplacements = new ArrayList<>();

    Map<Integer, ArrayList<Float>> inventory_dictionary = new LinkedHashMap<Integer, ArrayList<Float>>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create_replacement_growth_records, container, false);
        myDb = new DatabaseHelper(getContext());
        final String replacement_pen = getArguments().getString("Replacement Pen");




        textView = view.findViewById(R.id.textView);
        textView.setText(replacement_pen + " Growth Records");

        other_day_switch = view.findViewById(R.id.brooder_switch_other_day);

        brooder_growth_other_day = view.findViewById(R.id.brooder_growth_other_day);
        radio_group_collection_day = view.findViewById(R.id.radio_group_collection_day);
        brooder_growth_date_added = view.findViewById(R.id.brooder_growth_date_added);

        brooder_growth_male_weight = view.findViewById(R.id.brooder_growth_male_weight);
        brooder_growth_female_weight = view.findViewById(R.id.brooder_growth_female_weight);

        linear_male = view.findViewById(R.id.linear_male);
        linear_female = view.findViewById(R.id.linear_female);
        mActionOk = view.findViewById(R.id.action_ok);

        radio_group_collection_day = view.findViewById(R.id.radio_group_collection_day);
        radioButton = view.findViewById(R.id.radioButton);
        radioButton2 = view.findViewById(R.id.radioButton2);
        radioButton3 = view.findViewById(R.id.radioButton3);




        other_day_switch.setChecked(false);
        brooder_growth_other_day.setVisibility(View.GONE);
        radio_group_collection_day.setVisibility(View.VISIBLE);
        other_day_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    brooder_growth_other_day.setVisibility(View.VISIBLE);
                    radio_group_collection_day.setVisibility(View.GONE);

                } else {

                    brooder_growth_other_day.setVisibility(View.GONE);
                    radio_group_collection_day.setVisibility(View.VISIBLE);

                }

            }
        });




        brooder_growth_date_added.setOnClickListener(new View.OnClickListener() {
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
                        brooder_growth_date_added.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
                        calendar.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });


        final int day_45 = 1000;
        final int day_75 = 1001;
        final int day_100 = 1002;
        radioButton.setId(day_45);
        radioButton2.setId(day_75);
        radioButton3.setId(day_100);

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                //////for radio button
                int selectedDay = radio_group_collection_day.getCheckedRadioButtonId();
                int brooder_growth_collection = 0;
               // if(!())
                switch (selectedDay) {
                    case day_45:
                        // the first RadioButton is checked.
                        brooder_growth_collection = 45;

                        break;
                    //other checks for the other RadioButtons ids from the RadioGroup
                    case day_75:
                        // the first RadioButton is checked.
                        brooder_growth_collection = 75;
                        break;
                    //other checks for the other RadioButtons ids from the RadioGroup
                    case day_100:
                        brooder_growth_collection = 100;
                        break;

                    case -1:
                        brooder_growth_collection = 0;
                        // no RadioButton is checked inthe Radiogroup
                        break;
                }

                //////for radio button
//(brooder_growth_collection != 0 || !brooder_growth_other_day.getText().toString().isEmpty()) && !brooder_growth_date_added.getText().toString().isEmpty() && (!brooder_growth_total_weight.getText().toString().isEmpty() || !brooder_growth_male_weight.getText().toString().isEmpty() || !brooder_growth_female_weight.getText().toString().isEmpty()
                if((brooder_growth_collection != -1 || !brooder_growth_other_day.getText().toString().isEmpty())  &&  !brooder_growth_male_weight.getText().toString().isEmpty() && !brooder_growth_female_weight.getText().toString().isEmpty() && !brooder_growth_date_added.getText().toString().isEmpty()){

                    /////////////////////////////DATABASE OPERATIONS
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
                                  /*  Replacement_Inventory replace = new Replacement_Inventory(curse.getInt(0),curse.getInt(1), arrayListReplacements.get(i).getReplacement_family_number(),  arrayListReplacements.get(i).getReplacement_line_number(), arrayListReplacements.get(i).getReplacement_generation_number(), curse.getString(2), curse.getString(3),curse.getString(4), curse.getInt(5), curse.getInt(6), curse.getInt(7), arrayListReplacements.get(i).getReplacement_date_added(),curse.getString(8),curse.getString(9));
                                    arrayList_temp.add(replace);*/

                                }
                            }

                        } while (curse.moveToNext());

                    }





                    for(int i =0;i<arrayList_temp.size();i++){
                        if (arrayList_temp.get(i).getReplacement_inv_pen().equals(replacement_pen)){
                            arrayList_temp1.add(arrayList_temp.get(i));
                        }
                    }
              /*      for (int i=0;i<arrayList_temp1.size();i++){
                        buffer.append(arrayList_temp1.get(i).getId()+"\n");
                        buffer.append(arrayList_temp1.get(i).getReplacement_inv_replacement_id()+"\n");
                        buffer.append(arrayList_temp1.get(i).getReplacement_inv_pen()+"\n\n");
                    }*/


                    //GETTING TOTAL BROODER INVENTORY COUNT
                    int count_inventory = 0;

                    for(int i=0;i<arrayList_temp1.size();i++){
                        count_inventory = count_inventory+arrayList_temp1.get(i).getReplacement_total_quantity();
                    }



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
                    float_zero.add(0.0f);
                    for(int i = 0;i<arrayListBrooder.size();i++){
                        inventory_dictionary.put(arrayListBrooder.get(i),float_zero);
                    }



                    //populating inventory_dictionary
                    ArrayList<Integer> arrayList = new ArrayList<>();

                    for (int i=0;i<arrayList_temp1.size();i++){
                        if(inventory_dictionary.get(arrayList_temp1.get(i).getReplacement_inv_replacement_id()) != null && !arrayList.contains(arrayList_temp.get(i).getReplacement_inv_replacement_id())){
                            ArrayList<Float> arrayList2 = new ArrayList<>();
                            arrayList2.add((float)arrayList_temp1.get(i).getReplacement_total_quantity());
                            arrayList2.add((float)arrayList_temp1.get(i).getReplacement_total_quantity());
                            arrayList2.add((float)arrayList_temp1.get(i).getReplacement_total_quantity());
                            inventory_dictionary.put(arrayList_temp1.get(i).getReplacement_inv_replacement_id(),arrayList2);
                            arrayList.add(arrayList_temp1.get(i).getReplacement_inv_replacement_id());
                        }else if(arrayList.contains(arrayList_temp1.get(i).getReplacement_inv_replacement_id())){


                            ArrayList<Float> arrayList3 = new ArrayList<>();
                            arrayList3.add(inventory_dictionary.get(arrayList_temp1.get(i).getReplacement_inv_replacement_id()).get(0)+arrayList_temp1.get(i).getReplacement_total_quantity());
                            arrayList3.add(inventory_dictionary.get(arrayList_temp1.get(i).getReplacement_inv_replacement_id()).get(1)+arrayList_temp1.get(i).getReplacement_total_quantity());
                            arrayList3.add(inventory_dictionary.get(arrayList_temp1.get(i).getReplacement_inv_replacement_id()).get(2)+arrayList_temp1.get(i).getReplacement_total_quantity());


                            inventory_dictionary.put(arrayList_temp1.get(i).getReplacement_inv_replacement_id(),arrayList3);
                        }
                    }
                    //buffer.append("inventory_dictionary  "+inventory_dictionary.toString()+"\n\n");


                    ////important values:
                    //1. inventory_dictionary
                    //2. count_inventory


                    /////////////////////////////////////////////////////////////////

                    ///A. COMPUTING FOR THE MULTIPLIER

                    float maleWeight, femaleWeight, totalWeight;

                    totalWeight = Float.parseFloat(brooder_growth_male_weight.getText().toString()) + Float.parseFloat(brooder_growth_female_weight.getText().toString());
                    maleWeight = Float.parseFloat(brooder_growth_male_weight.getText().toString());
                    femaleWeight = Float.parseFloat(brooder_growth_female_weight.getText().toString());

            ///////////////di pa gumagana yung sa female at male, kailangan pala kukunin ko pa sa inventory yun

                    float multiplierTotalWeight = totalWeight/count_inventory;
                    //buffer.append("multiplierTotalWeight "+multiplierTotalWeight);
                    float multiplierMaleWeight = maleWeight/count_inventory;
                   // buffer.append("multiplierMaleWeight "+multiplierMaleWeight);
                    float multiplierFemaleWeight = femaleWeight/count_inventory;
                   // buffer.append("multiplierFemaleWeight "+multiplierFemaleWeight);

                    //float mutiplierRefused = feedRefused/count_inventory;


                    ///B. REPLACE THE VALUES OF THE KEYS IN inventory_dictionary WITH THEIR COUNT

                    ArrayList<Integer> arrayList1 = new ArrayList<>();
                    for (int i=0;i<arrayList_temp1.size();i++){
                        if(arrayList1.contains(arrayList_temp1.get(i).getReplacement_inv_replacement_id())){
                            //skip
                        }else{

                            ArrayList<Float> multiplier = new ArrayList<>();
                            multiplier.add(inventory_dictionary.get(arrayList_temp1.get(i).getReplacement_inv_replacement_id()).get(0)*multiplierTotalWeight);
                            multiplier.add(inventory_dictionary.get(arrayList_temp1.get(i).getReplacement_inv_replacement_id()).get(1)*multiplierMaleWeight);
                            multiplier.add(inventory_dictionary.get(arrayList_temp1.get(i).getReplacement_inv_replacement_id()).get(2)*multiplierFemaleWeight);
                            inventory_dictionary.put(arrayList_temp1.get(i).getReplacement_inv_replacement_id(),multiplier);
                            arrayList1.add(arrayList_temp1.get(i).getReplacement_inv_replacement_id());
                        }
                    }




                    ///C. INSERTING FEEDING RECORD TO THE DATABASE BY BATCH

                    for ( Map.Entry<Integer, ArrayList<Float>> entry : inventory_dictionary.entrySet()) {
                        Integer key = entry.getKey();
                        Float total = entry.getValue().get(0);
                        Float male = entry.getValue().get(1);
                        Float female = entry.getValue().get(2);
                                              /*        , brooder_growth_brooder brooder_growth_collection_day);  brooder_growth_date_collected);        brooder_growth_male_quantity);        , brooder_growth_male_weight);  brooder_growth_female_quantity);       , brooder_growth_female_weight);     brooder_growth_total_quantity);        brooder_growth_total_weight);      , brooder_growth_deleted_at);
*/
                        boolean isInserted = myDb.insertDataReplacementGrowthRecords( key ,brooder_growth_collection, brooder_growth_date_added.getText().toString(),null ,male,null, female,count_inventory, total,null );
                        if(isInserted){
                            //continue
                        }else{
                            Toast.makeText(getContext(),"Error inserting record with Inventory id: "+key, Toast.LENGTH_SHORT).show();
                            break;


                        }



                    }


                    Intent intent = new Intent(getActivity(), ReplacementGrowthRecordsActivity.class);
                    intent.putExtra("Replacement Pen",replacement_pen);
                    startActivity(intent);


                    getDialog().dismiss();
                }else{
                    Toast.makeText(getContext(),"Please fill any empty fields", Toast.LENGTH_SHORT).show();
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
package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.PensDirectory.Pen;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class CreateReplacementDialog extends DialogFragment {
    private static final String TAG = "CreateReplacementFragment";
    private EditText replacement_number_of_male,replacement_number_of_female, replacement_date_added;
    private TextView add_replacement_to_pen_text;
    private Spinner line_spinner, generation_spinner, family_spinner, transfer_spinner;
    private RadioGroup radio_group_system_type;
    private RadioButton within, outside;
    private Button mActionOk;
    private Calendar calendar,calendar2;
    private Context context;
    private String replacement_pen;
    private Switch replacement_system_type;
    private LinearLayout linear2;
    ArrayList<Pen> arrayListPen= new ArrayList();
    ArrayList<Pen> arrayListPen2= new ArrayList();

    DatabaseHelper myDb;
    Random random = new Random();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create_replacement, container, false);
        mActionOk = view.findViewById(R.id.action_ok);
        replacement_pen = getArguments().getString("Replacement Pen");
        add_replacement_to_pen_text = view.findViewById(R.id.add_brooder_to_pen_text);
        add_replacement_to_pen_text.setText("Add Replacement to Pen "+ replacement_pen);
        replacement_system_type = view.findViewById(R.id.replacement_system_type);

        generation_spinner = (Spinner) view.findViewById(R.id.generation_spinner);
        line_spinner = (Spinner) view.findViewById(R.id.line_spinner);
        family_spinner = (Spinner) view.findViewById(R.id.family_spinner);
        replacement_number_of_male = view.findViewById(R.id.replacement_number_of_male);
        replacement_number_of_female = view.findViewById(R.id.replacement_number_of_female);

        replacement_date_added = view.findViewById(R.id.replacement_date_added);
        transfer_spinner = view.findViewById(R.id.transfer_spinner);
        linear2 = view.findViewById(R.id.linear2);


        myDb = new DatabaseHelper(getContext());




        loadSpinnerDataForGeneration();
        generation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_generation = generation_spinner.getSelectedItem().toString();
                loadSpinnerDataForLine(selected_generation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        line_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_generation = generation_spinner.getSelectedItem().toString();
                String selected_line = line_spinner.getSelectedItem().toString();
                loadSpinnerDataForFamily(selected_line, selected_generation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        replacement_system_type.setChecked(false);
        replacement_system_type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    linear2.setVisibility(View.GONE);



                } else {

                    linear2.setVisibility(View.VISIBLE);


                }

            }
        });





        replacement_date_added.setOnClickListener(new View.OnClickListener() {
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
                        replacement_date_added.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
                        calendar.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });


        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           /*     int m = random.nextInt(100); //GAWAN MO NG RANDOMIZER NA TULAD NG KAY SHANNON
                if(!generation_spinner.getSelectedItem().toString().isEmpty() && !line_spinner.getSelectedItem().toString().isEmpty() && !family_spinner.getSelectedItem().toString().isEmpty() && !replacement_number_of_male.getText().toString().isEmpty() &&!replacement_number_of_female.getText().toString().isEmpty() && !replacement_date_added.getText().toString().isEmpty() ){
                    Cursor cursorReplacmentChecker = myDb.getAllDataFromReplacementsWhere(generation_spinner.getSelectedItem().toString(),line_spinner.getSelectedItem().toString(),family_spinner.getSelectedItem().toString());
                    cursorReplacmentChecker.moveToFirst();
                    if(cursorReplacmentChecker.getCount()==0){ //kapag wala pang laman
                        Cursor cursor_pen = myDb.getAllDataFromPen();
                        cursor_pen.moveToFirst();
                        if(cursor_pen.getCount() == 0){
                            //
                        }else{
                            do{
                                if(cursor_pen != null){
                                    Pen pen = new Pen(cursor_pen.getString(1), cursor_pen.getString(2), cursor_pen.getInt(3), cursor_pen.getInt(4));
                                    arrayListPen.add(pen);

                                }

                            }while(cursor_pen.moveToNext());
                        }


                        for (int i = 0; i<arrayListPen.size();i++){
                            if(arrayListPen.get(i).getPen_number().equals(replacement_pen)){
                                arrayListPen2.add(arrayListPen.get(i));
                            }
                        }

                        int total = arrayListPen2.get(0).getPen_inventory();
                        int current = arrayListPen2.get(0).getPen_capacity();



                        boolean isPenUpdated = myDb.updatePen(replacement_pen, "Grower", Integer.parseInt(replacement_number_of_male.getText().toString())+ Integer.parseInt(replacement_number_of_female.getText().toString())+total,current);


                        boolean isInserted = myDb.insertDataReplacement(family_spinner.getSelectedItem().toString(),line_spinner.getSelectedItem().toString(),generation_spinner.getSelectedItem().toString(),replacement_date_added.getText().toString(),null);
                        //INSERT DATA SA BROODER TABLE

                        //GET ID OF INSERTED BROODER

                        Cursor cursor = myDb.getIDFromReplacementsWhere(generation_spinner.getSelectedItem().toString(),line_spinner.getSelectedItem().toString(),family_spinner.getSelectedItem().toString());
                        cursor.moveToFirst();


                        Integer id = cursor.getInt(0);
                       // Toast.makeText(getActivity(), "no currently existing replacement", Toast.LENGTH_SHORT).show();

                        //get all data from pen na may pen number na G0X, tapos kunin mo dun yung id ng pen.


                        boolean isInventoryInserted = myDb.insertDataReplacementInventory(id,replacement_pen, "QUEBAI"+generation_spinner.getSelectedItem().toString()+line_spinner.getSelectedItem().toString()+family_spinner.getSelectedItem().toString()+m, replacement_date_added.getText().toString(), Integer.parseInt(replacement_number_of_male.getText().toString()), Integer.parseInt(replacement_number_of_female.getText().toString()), Integer.parseInt(replacement_number_of_male.getText().toString())+ Integer.parseInt(replacement_number_of_female.getText().toString()),null,null);



                        if(isInserted == true && isInventoryInserted == true){
                            Toast.makeText(getActivity(),"Replacement added to database", Toast.LENGTH_SHORT).show();
                            Intent intent_line = new Intent(getActivity(), CreateReplacements.class);
                            startActivity(intent_line);
                            getDialog().dismiss();
                        }else{
                            Toast.makeText(getActivity(),"Replacement not added to database", Toast.LENGTH_SHORT).show();
                        }

                    }else{ //KUNG MAY LAMAN YUNG DATABASE BASED SA VALUES KANINA

                        Cursor cursor_pen = myDb.getAllDataFromPen();
                        cursor_pen.moveToFirst();
                        if(cursor_pen.getCount() == 0){
                            //
                        }else{
                            do{
                                if(cursor_pen != null){
                                    Pen pen = new Pen(cursor_pen.getString(1), cursor_pen.getString(2), cursor_pen.getInt(3), cursor_pen.getInt(4));
                                    arrayListPen.add(pen);

                                }

                            }while(cursor_pen.moveToNext());
                        }


                        for (int i = 0; i<arrayListPen.size();i++){
                            if(arrayListPen.get(i).getPen_number().equals(replacement_pen)){
                                arrayListPen2.add(arrayListPen.get(i));
                            }
                        }

                        int total = arrayListPen2.get(0).getPen_inventory();
                        int current = arrayListPen2.get(0).getPen_capacity();



                        boolean isPenUpdated = myDb.updatePen(replacement_pen, "Grower", Integer.parseInt(replacement_number_of_male.getText().toString())+ Integer.parseInt(replacement_number_of_female.getText().toString())+total,current);


                        int replacement_id = cursorReplacmentChecker.getInt(0);
                        boolean isInventoryInserted = myDb.insertDataReplacementInventory(replacement_id,replacement_pen, "QUEBAI5678"+generation_spinner.getSelectedItem().toString()+line_spinner.getSelectedItem().toString()+family_spinner.getSelectedItem().toString()+m, replacement_date_added.getText().toString(), Integer.parseInt(replacement_number_of_male.getText().toString()), Integer.parseInt(replacement_number_of_female.getText().toString()), Integer.parseInt(replacement_number_of_male.getText().toString())+ Integer.parseInt(replacement_number_of_female.getText().toString()),null,null);

                        if(isPenUpdated && isInventoryInserted){
                            Toast.makeText(getActivity(), "Successfully added to the database", Toast.LENGTH_SHORT).show();
                            Intent intent_line = new Intent(getActivity(), CreateReplacements.class);
                            startActivity(intent_line);
                        }else{
                            Toast.makeText(getActivity(), "Error in adding to the database", Toast.LENGTH_SHORT).show();
                        }



                    }


                }else{
                    Toast.makeText(getActivity(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                }

*/
                StringBuffer buffer = new StringBuffer();
                int m = random.nextInt(100); //GAWAN MO NG RANDOMIZER NA TULAD NG KAY SHANNON
                if(!generation_spinner.getSelectedItem().toString().isEmpty() && !line_spinner.getSelectedItem().toString().isEmpty() && !family_spinner.getSelectedItem().toString().isEmpty() && !replacement_number_of_male.getText().toString().isEmpty() &&!replacement_number_of_male.getText().toString().isEmpty() && !replacement_date_added.getText().toString().isEmpty() ){


                    //dapat ang ipapasa dito ay yung primary key ng family number
                    //gawa ka ng function na nagrereturn ng id ng family based sa selected line and generation. gayahin mo nalang yung sa spinner loader ng family

                    //Cursor cursorBrooderChecker = myDb.getAllDataFromBroodersWhere(generation_spinner.getSelectedItem().toString(),line_spinner.getSelectedItem().toString(),family_spinner.getSelectedItem().toString());
                    Cursor cursorBrooderChecker = myDb.replacementFamilyChecker(family_spinner.getSelectedItem().toString(),line_spinner.getSelectedItem().toString(),generation_spinner.getSelectedItem().toString());
                    cursorBrooderChecker.moveToFirst();
                    //kapag wala pang laman
                    if(cursorBrooderChecker.getCount()==0){
                        Integer familyID = null;
                        Cursor cursor_familyID = myDb.getFamilyID(family_spinner.getSelectedItem().toString(),line_spinner.getSelectedItem().toString(),generation_spinner.getSelectedItem().toString());
                        cursor_familyID.moveToFirst();
                        if(cursor_familyID.getCount() != 0){
                            familyID = cursor_familyID.getInt(0);
                        }
                        boolean isInserted = myDb.insertDataReplacement(familyID,replacement_date_added.getText().toString(),null);
                        if(isInserted == true){
                            Toast.makeText(getContext(), "Replacement Added", Toast.LENGTH_SHORT).show();
                        }
                        //INSERT DATA SA BROODER TABLE
                        Cursor cursor_pen = myDb.getAllDataFromPen();
                        cursor_pen.moveToFirst();
                        if(cursor_pen.getCount() == 0){
                        }else{
                            do{
                                if(cursor_pen != null){
                                    Pen pen = new Pen(cursor_pen.getString(1), cursor_pen.getString(2), cursor_pen.getInt(3), cursor_pen.getInt(4));
                                    arrayListPen.add(pen);
                                }
                            }while (cursor_pen.moveToNext());
                        }
                        for (int i = 0; i<arrayListPen.size();i++){
                            if(arrayListPen.get(i).getPen_number().equals(replacement_pen)){
                                arrayListPen2.add(arrayListPen.get(i));
                            }
                        }

                        int total = arrayListPen2.get(0).getPen_inventory();
                        int current = arrayListPen2.get(0).getPen_capacity();

                        //GET ID OF INSERTED BROODER

                        Cursor cursor = myDb.getIDFromReplacementsWhere(familyID);
                        cursor.moveToFirst();



                        Integer id = cursor.getInt(0);

                        // boolean isInventoryInserted = myDb.insertDataBrooderInventory(id,brooder_pen, "QUEBAI"+generation_spinner.getSelectedItem().toString()+line_spinner.getSelectedItem().toString()+family_spinner.getSelectedItem().toString()+m, brooder_date_added.getText().toString()+m, null,null,Integer.parseInt(brooder_total_number.getText().toString()),null,null);
                        boolean isInventoryInserted = myDb.insertDataReplacementInventory(id,replacement_pen, "QUEBAI"+Integer.parseInt(generation_spinner.getSelectedItem().toString())+Integer.parseInt(line_spinner.getSelectedItem().toString())+Integer.parseInt(family_spinner.getSelectedItem().toString())+m, replacement_date_added.getText().toString(), Integer.parseInt(replacement_number_of_male.getText().toString()),Integer.parseInt(replacement_number_of_female.getText().toString()),Integer.parseInt(replacement_number_of_male.getText().toString())+Integer.parseInt(replacement_number_of_female.getText().toString()),null,null);

                        boolean isPenUpdated = myDb.updatePen(replacement_pen, "Brooder", Integer.parseInt(replacement_number_of_male.getText().toString())+Integer.parseInt(replacement_number_of_female.getText().toString())+total,current);
                        if(isInventoryInserted == true && isPenUpdated == true){
                            Toast.makeText(getActivity(), "Successfully added to database", Toast.LENGTH_SHORT).show();
                            Intent intent_line = new Intent(getActivity(), CreateReplacements.class);
                            startActivity(intent_line);


                        }else{
                            Toast.makeText(getActivity(), "Error in adding to the database", Toast.LENGTH_SHORT).show();
                        }

                    }else{ //KUNG MAY LAMAN YUNG DATABASE BASED SA VALUES KANINA

                        Cursor cursor_pen = myDb.getAllDataFromPen();
                        cursor_pen.moveToFirst();
                        if(cursor_pen.getCount() == 0){
                            //
                        }else{
                            do{
                                if(cursor_pen != null){
                                    Pen pen = new Pen(cursor_pen.getString(1), cursor_pen.getString(2), cursor_pen.getInt(3), cursor_pen.getInt(4));
                                    arrayListPen.add(pen);

                                }

                            }while(cursor_pen.moveToNext());
                        }


                        for (int i = 0; i<arrayListPen.size();i++){
                            if(arrayListPen.get(i).getPen_number().equals(replacement_pen)){
                                arrayListPen2.add(arrayListPen.get(i));
                            }
                        }

                        int total = arrayListPen2.get(0).getPen_inventory();
                        int current = arrayListPen2.get(0).getPen_capacity();



                        int brooder_id = cursorBrooderChecker.getInt(0);
                        boolean isPenUpdated = myDb.updatePen(replacement_pen, "Grower", (Integer.parseInt(replacement_number_of_male.getText().toString())+Integer.parseInt(replacement_number_of_female.getText().toString())+total),current);


                        //   boolean isInventoryInserted = myDb.insertDataBrooderInventory(brooder_id,brooder_pen, "QUEBAI"+generation_spinner.getSelectedItem().toString()+line_spinner.getSelectedItem().toString()+family_spinner.getSelectedItem().toString()+m, brooder_date_added.getText().toString()+m, null,null,Integer.parseInt(brooder_total_number.getText().toString()),null,null);
                        boolean isInventoryInserted = myDb.insertDataReplacementInventory(brooder_id,replacement_pen, "QUEBAI"+Integer.parseInt(generation_spinner.getSelectedItem().toString())+Integer.parseInt(line_spinner.getSelectedItem().toString())+Integer.parseInt(family_spinner.getSelectedItem().toString())+m, replacement_date_added.getText().toString(), Integer.parseInt(replacement_number_of_male.getText().toString()),Integer.parseInt(replacement_number_of_female.getText().toString()),Integer.parseInt(replacement_number_of_male.getText().toString())+Integer.parseInt(replacement_number_of_female.getText().toString()),null,null);

                        if(isPenUpdated == true && isInventoryInserted == true){
                            Toast.makeText(getActivity(), "Successfully added to database", Toast.LENGTH_SHORT).show();/*String.format("%04d" , Integer.parseInt(mInput_generation_number.getText().toString()));*/
                            Intent intent_line = new Intent(getActivity(), CreateReplacements.class);
                            startActivity(intent_line);

                        }else{
                        Toast.makeText(getActivity(),"Replacement not added to database", Toast.LENGTH_SHORT).show();
                        }


                    }



                }else{
                    Toast.makeText(getActivity(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                }
            }

        });


        return view;
    }

    private void loadSpinnerDataForGeneration() {
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> generations = db.getAllDataFromGenerationasList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, generations);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        generation_spinner.setAdapter(dataAdapter);



    }

    public void loadSpinnerDataForLine(String selected_generation){
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> lines = db.getAllDataFromLineasList(selected_generation);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lines);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        line_spinner.setAdapter(dataAdapter2);
    }
    public void loadSpinnerDataForFamily(String selected_line, String selected_generation){
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> lines = db.getAllDataFromFamilyasList(selected_line, selected_generation);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, lines);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        family_spinner.setAdapter(dataAdapter2);
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
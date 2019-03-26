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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class CreateBreederDialog extends DialogFragment {

    private static final String TAG = "CreateFamilyDialog";
    private EditText estimated_date_of_hatch,outside_date_transferred,outside_male_quantity, outside_female_quantity, quantity,female_quantity, date_transferred;

    private Button mActionOk;
    DatabaseHelper myDb;
    Switch in_or_out;
    LinearLayout within_system, outside_system;
    Calendar calendar, calendar2,calendar3;
    Spinner outside_generation_spinner, outside_line_spinner, outside_family_spinner, outside_place_to_pen, place_to_pen, generation_spinner, line_spinner, family_spinner, replacement_spinner,female_generation_spinner, female_line_spinner, female_family_spinner, female_replacement_spinner;
    DatabaseHelper mydb;
    Random random = new Random();
    boolean isSystemOutside;

    ArrayList<Breeders> arrayListBreeder = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create_breeder, container, false);
        mActionOk = view.findViewById(R.id.action_ok);

        //breeder_pen = getArguments().getString("breeder_pen");
        myDb = new DatabaseHelper(getContext());

        in_or_out = view.findViewById(R.id.in_or_out);
        within_system = view.findViewById(R.id.within_system);
        outside_system = view.findViewById(R.id.outside_system);
        estimated_date_of_hatch = view.findViewById(R.id.estimated_date_of_hatch);
        outside_date_transferred= view.findViewById(R.id.outside_date_transferred);

        outside_generation_spinner = view.findViewById(R.id.outside_generation_spinner);
        outside_line_spinner = view.findViewById(R.id.outside_line_spinner);
        outside_family_spinner = view.findViewById(R.id.outside_family_spinner);
        outside_place_to_pen = view.findViewById(R.id.outside_place_to_pen);
        outside_male_quantity = view.findViewById(R.id.outside_male_quantity);
        outside_female_quantity = view.findViewById(R.id.outside_female_quantity);


        generation_spinner = view.findViewById(R.id.generation_spinner);
        line_spinner = view.findViewById(R.id.line_spinner);
        family_spinner = view.findViewById(R.id.family_spinner);
        replacement_spinner = view.findViewById(R.id.replacement_spinner);
        female_generation_spinner = view.findViewById(R.id.female_generation_spinner);
        female_line_spinner = view.findViewById(R.id.female_line_spinner);
        female_family_spinner = view.findViewById(R.id.female_family_spinner);
        female_replacement_spinner = view.findViewById(R.id.female_replacement_spinner);
        place_to_pen = view.findViewById(R.id.place_to_pen);
        quantity = view.findViewById(R.id.quantity);
        female_quantity = view.findViewById(R.id.female_quantity);
        date_transferred = view.findViewById(R.id.date_transferred);



        loadSpinnerDataForPen();
        loadSpinnerDataForGeneration();
        loadSpinnerDataForWithinGeneration();
        loadSpinnerDataForFemaleWithinGeneration();

        outside_generation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_generation = outside_generation_spinner.getSelectedItem().toString();
                loadSpinnerDataForLine(selected_generation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        outside_line_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_generation = outside_generation_spinner.getSelectedItem().toString();
                String selected_line = outside_line_spinner.getSelectedItem().toString();
                loadSpinnerDataForFamily(selected_line, selected_generation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        generation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_generation = generation_spinner.getSelectedItem().toString();
                loadSpinnerDataForWithinLine(selected_generation);
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
                loadSpinnerDataForWithinFamily(selected_line, selected_generation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        family_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_generation = generation_spinner.getSelectedItem().toString();
                String selected_line = line_spinner.getSelectedItem().toString();
                String selected_family = family_spinner.getSelectedItem().toString();
                loadSpinnerDataForWithinReplacement(selected_family,selected_line, selected_generation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        female_generation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_generation = female_generation_spinner.getSelectedItem().toString();
                loadSpinnerDataForFemaleWithinLine(selected_generation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        female_line_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_generation = female_generation_spinner.getSelectedItem().toString();
                String selected_line = female_line_spinner.getSelectedItem().toString();
                loadSpinnerDataForFemaleWithinFamily(selected_line, selected_generation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        female_family_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_generation = female_generation_spinner.getSelectedItem().toString();
                String selected_line = female_line_spinner.getSelectedItem().toString();
                String selected_family = female_family_spinner.getSelectedItem().toString();
                loadSpinnerDataForFemaleWithinReplacement(selected_family,selected_line, selected_generation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        outside_place_to_pen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_generation = outside_place_to_pen.getSelectedItem().toString();
                //loadSpinnerDataForLine(selected_generation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        outside_system.setVisibility(View.GONE);
        in_or_out.setChecked(false);
        in_or_out.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    within_system.setVisibility(View.GONE);
                    outside_system.setVisibility(View.VISIBLE);
                    isSystemOutside = true;

                } else {
                    within_system.setVisibility(View.VISIBLE);
                    outside_system.setVisibility(View.GONE);
                    isSystemOutside = false;

                }

            }
        });



        estimated_date_of_hatch.setOnClickListener(new View.OnClickListener() {
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
                        estimated_date_of_hatch.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
                        calendar.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });



        outside_date_transferred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar2 = Calendar.getInstance();
                int year = calendar2.get(Calendar.YEAR);
                int month = calendar2.get(Calendar.MONTH);
                int day = calendar2.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        selectedMonth++;
                        outside_date_transferred.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
                        calendar2.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });






        date_transferred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar3 = Calendar.getInstance();
                int year = calendar3.get(Calendar.YEAR);
                int month = calendar3.get(Calendar.MONTH);
                int day = calendar3.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        selectedMonth++;
                        date_transferred.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
                        calendar3.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });







        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int m = random.nextInt(100);

                //IBIG SABIHIN, HINDI MO NA KAILANGAN NG CHECKER KUNG MERON BA SA DATABASE NG BREEDER GIVEN NG GEN LINE FAM
                    //NEXT TIME MO NA AYUSIN
                if(isSystemOutside){
                    if(!estimated_date_of_hatch.getText().toString().isEmpty() && !outside_generation_spinner.getSelectedItem().toString().isEmpty() && !outside_male_quantity.getText().toString().isEmpty() && !outside_female_quantity.getText().toString().isEmpty()){
                    ////DATABASE OPERATIONSSS

                        Integer familyID = null;
                        Cursor cursor_familyID = myDb.getFamilyID(outside_family_spinner.getSelectedItem().toString(),outside_line_spinner.getSelectedItem().toString(),outside_generation_spinner.getSelectedItem().toString());
                        cursor_familyID.moveToFirst();
                        if(cursor_familyID.getCount() != 0){
                            familyID = cursor_familyID.getInt(0);
                        }
                        boolean isInserted = myDb.insertDataBreeder(familyID, null,outside_date_transferred.getText().toString(),null);
                        //kunin yung selected pen tapos kung 0 yung content niya, ay

/*    public static final String TABLE_PEN = "pen_table";
    public static final String PEN_COL_0 = "ID";
    public static final String PEN_COL_1 = "PEN_NUMBER";
    public static final String PEN_COL_2 = "PEN_TYPE";
    public static final String PEN_COL_3 = "PEN_CURRENT_CAPACITY";
    public static final String PEN_COL_4 = "PEN_TOTAL_CAPACITY";
    public static final String PEN_COL_5 = "PEN_FARM_ID";
    public static final String PEN_COL_6 = "PEN_IS_ACTIVE";*/

                        Cursor cursor_pen = myDb.getAllDataFromPenWhere(outside_place_to_pen.getSelectedItem().toString());
                        cursor_pen.moveToFirst();
                        int total = cursor_pen.getInt(4);
                        int current = cursor_pen.getInt(3);
                        //update pen count
                        boolean isPenUpdated = myDb.updatePen(outside_place_to_pen.getSelectedItem().toString(), "Layer", (Integer.parseInt(outside_male_quantity.getText().toString())+Integer.parseInt(outside_female_quantity.getText().toString()))+current,total);



                        Cursor cursor = myDb.getIDFromBreedersWhere(familyID);
                        cursor.moveToFirst();
                        Integer id = cursor.getInt(0);

                        boolean isInventoryInserted = myDb.insertDataBreederInventory(id, outside_place_to_pen.getSelectedItem().toString(), "QUEBAI"+Integer.parseInt(outside_generation_spinner.getSelectedItem().toString())+Integer.parseInt(outside_line_spinner.getSelectedItem().toString())+Integer.parseInt(outside_line_spinner.getSelectedItem().toString())+m, outside_date_transferred.getText().toString(),Integer.parseInt(outside_male_quantity.getText().toString()), Integer.parseInt(outside_female_quantity.getText().toString()),Integer.parseInt(outside_female_quantity.getText().toString())+Integer.parseInt(outside_male_quantity.getText().toString()),null,null);

                        if(isInserted == true && isInventoryInserted == true){
                            Toast.makeText(getActivity(),"Breeder added to database", Toast.LENGTH_SHORT).show();
                            Intent intent_line = new Intent(getActivity(), CreateBreeders.class);
                            startActivity(intent_line);
                            getDialog().dismiss();
                        }else{
                            Toast.makeText(getActivity(),"Breeder not added to database", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "Please fill empty entries", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    if(!generation_spinner.getSelectedItem().toString().isEmpty() && !quantity.getText().toString().isEmpty() && !female_quantity.getText().toString().isEmpty() && !date_transferred.getText().toString().isEmpty()){
                        Integer familyID = null;
                        Integer female_familyID = null;
                        Cursor cursor_familyID = myDb.getFamilyID(family_spinner.getSelectedItem().toString(),line_spinner.getSelectedItem().toString(),generation_spinner.getSelectedItem().toString());
                        cursor_familyID.moveToFirst();
                        if(cursor_familyID.getCount() != 0){
                            familyID = cursor_familyID.getInt(0);
                        }
                        Cursor cursor_female_familyID = myDb.getFamilyID(female_family_spinner.getSelectedItem().toString(),female_line_spinner.getSelectedItem().toString(),female_generation_spinner.getSelectedItem().toString());
                        cursor_female_familyID.moveToFirst();
                        if(cursor_female_familyID.getCount() != 0){
                            female_familyID = cursor_female_familyID.getInt(0);
                        }
                        boolean isInserted = myDb.insertDataBreeder(familyID, female_familyID,date_transferred.getText().toString(),null);


                        Cursor cursor_pen = myDb.getAllDataFromPenWhere(place_to_pen.getSelectedItem().toString());
                        cursor_pen.moveToFirst();
                        int total = cursor_pen.getInt(4);
                        int current = cursor_pen.getInt(3);
                        //update pen count
                        boolean isPenUpdated = myDb.updatePen(place_to_pen.getSelectedItem().toString(), "Layer", (Integer.parseInt(quantity.getText().toString())+Integer.parseInt(female_quantity.getText().toString())+current),total);


                        Cursor cursor = myDb.getIDFromBreedersWhere(familyID);
                        cursor.moveToFirst();
                        Integer id = cursor.getInt(0);

                        boolean isInventoryInserted = myDb.insertDataBreederInventory(id, place_to_pen.getSelectedItem().toString(), "QUEBAI"+Integer.parseInt(generation_spinner.getSelectedItem().toString())+Integer.parseInt(line_spinner.getSelectedItem().toString())+Integer.parseInt(line_spinner.getSelectedItem().toString())+m, date_transferred.getText().toString(),Integer.parseInt(quantity.getText().toString()), Integer.parseInt(female_quantity.getText().toString()),Integer.parseInt(female_quantity.getText().toString())+Integer.parseInt(quantity.getText().toString()),null,null);

                        if(isInserted == true && isInventoryInserted == true){
                            Toast.makeText(getActivity(),"Breeder added to database", Toast.LENGTH_SHORT).show();
                            Intent intent_line = new Intent(getActivity(), CreateBreeders.class);
                            startActivity(intent_line);
                            getDialog().dismiss();
                        }else{
                            Toast.makeText(getActivity(),"Breeder not added to database", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getContext(), "Please fill empty entries", Toast.LENGTH_SHORT).show();
                    }
                ///within system
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
        outside_generation_spinner.setAdapter(dataAdapter);
    }

    public void loadSpinnerDataForLine(String selected_generation){
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> lines = db.getAllDataFromLineasList(selected_generation);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lines);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        outside_line_spinner.setAdapter(dataAdapter2);
    }
    public void loadSpinnerDataForFamily(String selected_line, String selected_generation){
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> lines = db.getAllDataFromFamilyasList(selected_line, selected_generation);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, lines);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        outside_family_spinner.setAdapter(dataAdapter2);
    }





    private void loadSpinnerDataForWithinGeneration() {

        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> generations = db.getAllDataFromGenerationasList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, generations);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        generation_spinner.setAdapter(dataAdapter);
    }
    public void loadSpinnerDataForWithinLine(String selected_generation){
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> lines = db.getAllDataFromLineasList(selected_generation);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lines);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        line_spinner.setAdapter(dataAdapter2);
    }
    public void loadSpinnerDataForWithinFamily(String selected_line, String selected_generation){
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> lines = db.getAllDataFromFamilyasList(selected_line, selected_generation);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, lines);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        family_spinner.setAdapter(dataAdapter2);
    }

    public void loadSpinnerDataForWithinReplacement(String selected_family, String selected_line, String selected_generation){
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> lines = db.getAllDataFromReplacementasList(selected_family,selected_line, selected_generation);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, lines);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        replacement_spinner.setAdapter(dataAdapter2);

    }




    private void loadSpinnerDataForFemaleWithinGeneration() {

        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> generations = db.getAllDataFromGenerationasList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, generations);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        female_generation_spinner.setAdapter(dataAdapter);
    }
    public void loadSpinnerDataForFemaleWithinLine(String selected_generation){
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> lines = db.getAllDataFromLineasList(selected_generation);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lines);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        female_line_spinner.setAdapter(dataAdapter2);
    }
    public void loadSpinnerDataForFemaleWithinFamily(String selected_line, String selected_generation){
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> lines = db.getAllDataFromFamilyasList(selected_line, selected_generation);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, lines);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        female_family_spinner.setAdapter(dataAdapter2);
    }

    public void loadSpinnerDataForFemaleWithinReplacement(String selected_family, String selected_line, String selected_generation){
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> lines = db.getAllDataFromReplacementasList(selected_family,selected_line, selected_generation);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, lines);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        female_replacement_spinner.setAdapter(dataAdapter2);

    }
    private void loadSpinnerDataForPen() {
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> generations = db.getAllDataFromPenasList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, generations);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        outside_place_to_pen.setAdapter(dataAdapter);
        place_to_pen.setAdapter(dataAdapter);
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
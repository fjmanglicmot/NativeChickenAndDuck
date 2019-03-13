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
    private EditText estimated_date_of_hatch,outside_date_transferred,outiside_quantity, outside_female_quantity;

    private Button mActionOk;
    DatabaseHelper myDb;
    Switch in_or_out;
    LinearLayout within_system, outside_system;
    Calendar calendar, calendar2;
    Spinner outside_generation_spinner, outside_line_spinner, outside_family_spinner, outside_place_to_pen;
    DatabaseHelper mydb;
    Random random = new Random();
    String breeder_pen;

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
        outiside_quantity = view.findViewById(R.id.outiside_quantity);
        outside_female_quantity = view.findViewById(R.id.outside_female_quantity);




        loadSpinnerDataForPen();
        loadSpinnerDataForGeneration();

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

                } else {
                    within_system.setVisibility(View.VISIBLE);
                    outside_system.setVisibility(View.GONE);

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













        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ONLY FOR OUTSIDE

                int m = random.nextInt(100);
                //IBIG SABIHIN, HINDI MO NA KAILANGAN NG CHECKER KUNG MERON BA SA DATABASE NG BREEDER GIVEN NG GEN LINE FAM
                    //NEXT TIME MO NA AYUSIN
                if(!estimated_date_of_hatch.getText().toString().isEmpty()){
                    ////DATABASE OPERATIONSSS

                   boolean isInserted = myDb.insertDataBreeder(outside_family_spinner.getSelectedItem().toString(), null, outside_line_spinner.getSelectedItem().toString(), outside_generation_spinner.getSelectedItem().toString(),estimated_date_of_hatch.getText().toString(), null);

                   Cursor cursor = myDb.getIDFromBreedersWhere(outside_generation_spinner.getSelectedItem().toString(),outside_line_spinner.getSelectedItem().toString(),outside_family_spinner.getSelectedItem().toString());
                   cursor.moveToFirst();

                   Integer id = cursor.getInt(0);

                   boolean isInventoryInserted = myDb.insertDataBreederInventory(id, outside_place_to_pen.getSelectedItem().toString(), "QUEBAI"+outside_generation_spinner.getSelectedItem().toString()+outside_line_spinner.getSelectedItem().toString()+outside_line_spinner.getSelectedItem().toString()+m, outside_date_transferred.getText().toString(),Integer.parseInt(outiside_quantity.getText().toString()), Integer.parseInt(outside_female_quantity.getText().toString()),null,null,null);

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
    private void loadSpinnerDataForPen() {

        DatabaseHelper db = new DatabaseHelper(getContext());

        List<String> generations = db.getAllDataFromPenasList();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, generations);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        outside_place_to_pen.setAdapter(dataAdapter);


    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
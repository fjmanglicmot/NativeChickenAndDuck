package com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.APIHelper;
import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.PensDirectory.Pen;
import com.example.cholomanglicmot.nativechickenandduck.R;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import cz.msebera.android.httpclient.Header;

/*import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;*/

public class CreateBrooderDialog extends DialogFragment {

    private static final String TAG = "CreateBrooderFragment";
    private EditText brooder_estimated_date_of_hatch,brooder_date_added, brooder_total_number;
    private Spinner line_spinner, generation_spinner, family_spinner;

    private Button mActionOk;
    private Calendar calendar,calendar2;
    private Context context;
    private String brooder_pen;
    Integer brooder_pen_id;
    Brooders_Pen brooders_pen;
    DatabaseHelper myDb;
    APIHelper APIHelper;
    Random random = new Random();
    ArrayList<Pen> arrayListPen = new ArrayList<>();
    ArrayList<Pen> arrayListPen2 = new ArrayList<>();

    ArrayList<Brooders> arrayListBrooders = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create_brooder, container, false);
        mActionOk = view.findViewById(R.id.action_ok);


        brooder_pen = getArguments().getString("brooder_pen");

        generation_spinner = (Spinner) view.findViewById(R.id.generation_spinner);
        line_spinner = (Spinner) view.findViewById(R.id.line_spinner);
        family_spinner = (Spinner) view.findViewById(R.id.family_spinner);
        brooder_estimated_date_of_hatch = view.findViewById(R.id.replacement_feeding_date_collected);
        brooder_date_added = view.findViewById(R.id.brooder_date_added);
        brooder_total_number = view.findViewById(R.id.total_brooder_number);



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
        APIHelper = new APIHelper();
        myDb = new DatabaseHelper(getContext());
        brooder_estimated_date_of_hatch.setOnClickListener(new View.OnClickListener() {
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
                        brooder_estimated_date_of_hatch.setText(selectedYear + "-" + selectedMonth + "-" + selectedDay);
                        calendar.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });
        brooder_date_added.setOnClickListener(new View.OnClickListener() {
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
                        brooder_date_added.setText(selectedYear + "-" + selectedMonth + "-" + selectedDay);
                        calendar2.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();
            }
        });








        Cursor cursor = myDb.getAllDataFromPenWhere(brooder_pen);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            brooder_pen_id = cursor.getInt(0);
        }
        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer();
                int m = random.nextInt(100); //GAWAN MO NG RANDOMIZER NA TULAD NG KAY SHANNON
                if(!generation_spinner.getSelectedItem().toString().isEmpty() && !line_spinner.getSelectedItem().toString().isEmpty() && !family_spinner.getSelectedItem().toString().isEmpty() && !brooder_total_number.getText().toString().isEmpty() &&!brooder_estimated_date_of_hatch.getText().toString().isEmpty() && !brooder_date_added.getText().toString().isEmpty() ){


                    //dapat ang ipapasa dito ay yung primary key ng family number
                    //gawa ka ng function na nagrereturn ng id ng family based sa selected line and generation. gayahin mo nalang yung sa spinner loader ng family

                    //Cursor cursorBrooderChecker = myDb.getAllDataFromBroodersWhere(generation_spinner.getSelectedItem().toString(),line_spinner.getSelectedItem().toString(),family_spinner.getSelectedItem().toString());
                    Cursor cursorBrooderChecker = myDb.familyChecker(family_spinner.getSelectedItem().toString(),line_spinner.getSelectedItem().toString(),generation_spinner.getSelectedItem().toString());
                    cursorBrooderChecker.moveToFirst();
                    //kapag wala pang laman

                    //isInternetAvailable -> API
                    //else, local saving

                    if(cursorBrooderChecker.getCount()==0){
                        Integer familyID = null;
                        Cursor cursor_familyID = myDb.getFamilyID(family_spinner.getSelectedItem().toString(),line_spinner.getSelectedItem().toString(),generation_spinner.getSelectedItem().toString());
                        cursor_familyID.moveToFirst();
                        if(cursor_familyID.getCount() != 0){
                            familyID = cursor_familyID.getInt(0);
                        }
                        boolean isInserted = myDb.insertDataBrooder(familyID,brooder_date_added.getText().toString(),null);

                        RequestParams requestParams = new RequestParams();
                        requestParams.add("family_id", familyID.toString());
                        requestParams.add("date_added", brooder_date_added.getText().toString());
                        requestParams.add("deleted_at", null);

                        API_addBrooder(requestParams);


                        if(isInserted == true){
                      //      Toast.makeText(getContext(), "Gujab", Toast.LENGTH_SHORT).show();
                        }
                        //INSERT DATA SA BROODER TABLE
                        Cursor cursor_pen = myDb.getAllDataFromPen();
                        cursor_pen.moveToFirst();
                        if(cursor_pen.getCount() == 0){
                        }else{
                            do{
                                if(cursor_pen != null){
                                    Pen pen = new Pen(cursor_pen.getInt(0),cursor_pen.getString(2), cursor_pen.getString(3), cursor_pen.getInt(4), cursor_pen.getInt(5), cursor_pen.getInt(6), cursor_pen.getInt(7));
                                    arrayListPen.add(pen);
                                }
                            }while (cursor_pen.moveToNext());
                        }
                        for (int i = 0; i<arrayListPen.size();i++){
                            if(arrayListPen.get(i).getPen_number().equals(brooder_pen)){
                                arrayListPen2.add(arrayListPen.get(i));
                            }
                        }

                        int total = arrayListPen2.get(0).getPen_inventory();
                        int current = arrayListPen2.get(0).getPen_capacity();

                        //GET ID OF INSERTED BROODER

                        Cursor cursor = myDb.getIDFromBroodersWhere(familyID);
                        cursor.moveToFirst();


                        Integer id = cursor.getInt(0);

                         boolean isInventoryInserted = myDb.insertDataBrooderInventory(id,brooder_pen_id, "QUEBAI"+Integer.parseInt(generation_spinner.getSelectedItem().toString())+Integer.parseInt(line_spinner.getSelectedItem().toString())+Integer.parseInt(family_spinner.getSelectedItem().toString())+m, brooder_estimated_date_of_hatch.getText().toString(), 0,0,Integer.parseInt(brooder_total_number.getText().toString()),null,null);

                        boolean isPenUpdated = myDb.updatePen(brooder_pen, "Brooder", Integer.parseInt(brooder_total_number.getText().toString())+total,current);
                        if(isPenUpdated && isInventoryInserted){
                           // Toast.makeText(getActivity(), "Successfully added to database", Toast.LENGTH_SHORT).show();
                            Intent intent_line = new Intent(getActivity(), CreateBrooders.class);
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
                                    Pen pen = new Pen(cursor_pen.getInt(0),cursor_pen.getString(2), cursor_pen.getString(3), cursor_pen.getInt(4), cursor_pen.getInt(5), cursor_pen.getInt(6), cursor_pen.getInt(7));
                                    arrayListPen.add(pen);

                                }

                            }while(cursor_pen.moveToNext());
                        }


                        for (int i = 0; i<arrayListPen.size();i++){
                            if(arrayListPen.get(i).getPen_number().equals(brooder_pen)){
                                arrayListPen2.add(arrayListPen.get(i));
                            }
                        }

                        int total = arrayListPen2.get(0).getPen_inventory();
                        int current = arrayListPen2.get(0).getPen_capacity();



                        int brooder_id = cursorBrooderChecker.getInt(0);
                        boolean isPenUpdated = myDb.updatePen(brooder_pen, "Brooder", (Integer.parseInt(brooder_total_number.getText().toString())+total),current);

                        if(isPenUpdated == true){
                          //  Toast.makeText(getActivity(), "Successfully added to database", Toast.LENGTH_SHORT).show();/*String.format("%04d" , Integer.parseInt(mInput_generation_number.getText().toString()));*/
                            Intent intent_line = new Intent(getActivity(), CreateBrooders.class);
                            startActivity(intent_line);

                        }
                        boolean isInventoryInserted = myDb.insertDataBrooderInventory(brooder_id,brooder_pen_id, "QUEBAI"+Integer.parseInt(generation_spinner.getSelectedItem().toString())+Integer.parseInt(line_spinner.getSelectedItem().toString())+Integer.parseInt(family_spinner.getSelectedItem().toString())+m, brooder_estimated_date_of_hatch.getText().toString(), 0,0,Integer.parseInt(brooder_total_number.getText().toString()),null,null);



                        if(isInventoryInserted == true){
                          //  Toast.makeText(getActivity(), "Successfully added to database", Toast.LENGTH_SHORT).show();

                            getDialog().dismiss();
                        }else{
                            Toast.makeText(getActivity(),"Brooder not added to database", Toast.LENGTH_SHORT).show();
                        }
                        getDialog().dismiss();
                    }



                }else{
                    Toast.makeText(getActivity(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                }



            }

        });


        return view;
    }

    private void API_addBrooder(RequestParams requestParams){
        APIHelper.addBrooderFamily("addBrooderFamily", requestParams, new BaseJsonHttpResponseHandler<Object>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response){
                Toast.makeText(getContext(), "Successfully added to web", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonResponse, Object response){

                Toast.makeText(getContext(), "Failed to add to web", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable{
                return null;
            }
        });
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
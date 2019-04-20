package com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CullBrooderInventoryDialog extends DialogFragment {

    DatabaseHelper myDb;
    TextView textView, family_number, line_number, generation_number, brooder_male_count, brooder_female_count, brooder_total, brooder_date_added,batching_date ;
    EditText edit_male_count, edit_female_count;
    Button no, yes;
    List<String> famLineGen = new ArrayList<>();
    Context context;
    Integer  pen_capacity, total;
    String pen_number;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_cull_brooder_inventory, container, false);

        final String brooder_tag = getArguments().getString("Brooder Tag");
        final Integer brooder_pen = getArguments().getInt("Brooder Pen ID");
        final Integer brooder_id = getArguments().getInt("Brooder ID");
        context = getActivity();

        myDb = new DatabaseHelper(getContext());


        textView = view.findViewById(R.id.textView);


        no = view.findViewById(R.id.no);
        yes = view.findViewById(R.id.yes);

        textView.setText("Cull Replacement "+brooder_tag+"?");
        Cursor cursor1 = myDb.getAllDataFromBrooderInventoryWhereID(brooder_id);
        cursor1.moveToFirst();
        if(cursor1.getCount() != 0){
            total = cursor1.getInt(7);
        }


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getDialog().dismiss();

            }
        });
        Cursor cursor = myDb.getAllDataFromPenWhereID(brooder_pen);
        cursor.moveToFirst();

        if(cursor.getCount() != 0){
            pen_number = cursor.getString(2);
            pen_capacity = cursor.getInt(5);
        }

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update  is_active column of selected inventory to 0 or false
                String date = getDate();
                Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();
                boolean isDeleted = myDb.cullBrooderInventory(brooder_tag, date);
                //update brooder pen
                boolean isUpdateed = myDb.updateDataPen1(brooder_pen, pen_number,pen_capacity-total);
                if(isDeleted){
                    Toast.makeText(getContext(), "Brooder "+brooder_tag+" deleted.", Toast.LENGTH_SHORT).show();
                }
                Intent intent_line = new Intent(getActivity(), CreateBrooders.class);
                startActivity(intent_line);

            }
        });


        return view;
    }
    private String getDate() {

/*
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=dateFormat.format(date);
        Date date= new Date();

        long time = date.getTime();
        time = time/1000;
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date1 = DateFormat.format("YYYY-MM-dd", cal).toString();
        return date1;
*/



        Date currentTime = Calendar.getInstance().getTime();
       // currentTime = currentTime.toString();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        //System.out.println(formatter.format(date));
        return formatter.format(date);
    }
}
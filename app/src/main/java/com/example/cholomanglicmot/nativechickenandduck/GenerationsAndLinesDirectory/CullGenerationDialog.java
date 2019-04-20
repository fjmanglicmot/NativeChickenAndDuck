package com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory;

import android.content.Context;
import android.content.Intent;
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
import com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory.CreateReplacements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CullGenerationDialog extends DialogFragment {

    DatabaseHelper myDb;
    TextView textView, family_number, line_number, generation_number, brooder_male_count, brooder_female_count, brooder_total, brooder_date_added,batching_date;
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

        final String generation_number = getArguments().getString("Generation Number");
        final Integer generation_id = getArguments().getInt("Generation ID");

        context = getActivity();

        myDb = new DatabaseHelper(getContext());


        textView = view.findViewById(R.id.textView);


        no = view.findViewById(R.id.no);
        yes = view.findViewById(R.id.yes);

        textView.setText("Cull Generation "+generation_number+"?");

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getDialog().dismiss();

            }
        });


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update  is_active column of selected inventory to 0 or false
                String date = getDate();
                Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();
                boolean isDeleted = myDb.cullReplacementInventory(generation_number, date);
                //update brooder pen
                boolean isUpdateed = myDb.updateDataPen1(generation_id, pen_number,pen_capacity-total);
                if(isDeleted){
                    Toast.makeText(getContext(), "Replacement "+generation_number+" deleted.", Toast.LENGTH_SHORT).show();
                }
                Intent intent_line = new Intent(getActivity(), CreateReplacements.class);
                startActivity(intent_line);

            }
        });


        return view;
    }
    private String getDate() {

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }
}
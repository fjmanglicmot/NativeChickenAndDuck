package com.example.cholomanglicmot.nativechickenandduck;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BreederGenerationDialog extends DialogFragment {

    private static final String TAG = "BreederGenerationDialog";
    private EditText mInput_generation_number,add_lines2,add_lines3,add_lines4,add_lines5 ;
    private EditText add_lines;
    private Button mActionOk, mAddLine;
    private int ctr = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_generation, container, false);
        mActionOk = view.findViewById(R.id.action_ok);
        add_lines = view.findViewById(R.id.add_lines);
        mInput_generation_number = view.findViewById(R.id.generation_no);
        mAddLine = view.findViewById(R.id.add_line_field_button);
        add_lines2 = view.findViewById(R.id.add_lines2);
        add_lines3 = view.findViewById(R.id.add_lines3);
        add_lines4 = view.findViewById(R.id.add_lines4);
        add_lines5 = view.findViewById(R.id.add_lines5);


        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mInput_generation_number.getText().toString().isEmpty() && !add_lines.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Generation added to database", Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                }else{
                    Toast.makeText(getActivity(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mAddLine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ctr = ctr + 1;
                if (ctr == 1){
                    add_lines2.setVisibility(View.VISIBLE);
                }else if(ctr == 2){
                    add_lines3.setVisibility(View.VISIBLE);
                }else if(ctr == 3){
                    add_lines4.setVisibility(View.VISIBLE);
                }else if(ctr == 4){
                    add_lines5.setVisibility(View.VISIBLE);
                }else  Toast.makeText(getActivity(),"Maximum lines reached", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}

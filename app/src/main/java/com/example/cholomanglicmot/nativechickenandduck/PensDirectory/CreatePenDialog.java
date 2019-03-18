package com.example.cholomanglicmot.nativechickenandduck.PensDirectory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

public class CreatePenDialog extends DialogFragment {

    private static final String TAG = "CreatePenDialog";
    private EditText mInput_pen_number,mInput_pen_inventory;
    private RadioGroup radioGroup;
    private RadioButton radioButton,radioButton2,radioButton3;

    private EditText mInput_pen_capacity;
    private Button mActionOk;
    DatabaseHelper myDb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create_pen, container, false);
        mActionOk = view.findViewById(R.id.action_ok);
        mInput_pen_number = view.findViewById(R.id.generation_no);
        mInput_pen_capacity = view.findViewById(R.id.capacity);
      //  mInput_pen_inventory = view.findViewById(R.id.inventory);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group_collection_day);
        radioButton = view.findViewById(R.id.radioButton);
        radioButton2 = view.findViewById(R.id.radioButton2);
        radioButton3 = view.findViewById(R.id.radioButton3);

        final int radio_brooder = 1000;//first radio button id
        final int radio_grower = 1001;//second radio button id
        final int radio_layer = 1002;//third radio button id

        radioButton.setId(radio_brooder);
        radioButton2.setId(radio_grower);
        radioButton3.setId(radio_layer);


        // find the radiobutton by returned id



        myDb = new DatabaseHelper(getContext());


        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                String selected_pen_type = "None Selected";
                String pen_number = null;
                switch (selectedId) {
                    case radio_brooder:
                        // the first RadioButton is checked.
                        selected_pen_type = "Brooder";
                        /*String.format("%04d" , Integer.parseInt(mInput_generation_number.getText().toString()));*/
                        pen_number = "B"+String.format("%02d" , Integer.parseInt(mInput_pen_number.getText().toString()));
                        break;
                    //other checks for the other RadioButtons ids from the RadioGroup
                    case radio_grower:
                        // the first RadioButton is checked.
                        selected_pen_type = "Grower";
                        pen_number = "G"+String.format("%02d" , Integer.parseInt(mInput_pen_number.getText().toString()));
                        break;
                    //other checks for the other RadioButtons ids from the RadioGroup
                    case radio_layer:
                        // the first RadioButton is checked.
                        selected_pen_type = "Layer";
                        pen_number = "L"+String.format("%02d" , Integer.parseInt(mInput_pen_number.getText().toString()));
                        break;
                    //other checks for the other RadioButtons ids from the RadioGroup
                    case -1:

                        // no RadioButton is checked inthe Radiogroup
                        break;
                }


                if(!mInput_pen_number.getText().toString().isEmpty() && !mInput_pen_capacity.getText().toString().isEmpty() && radioGroup.getCheckedRadioButtonId() != -1 ){

                    boolean isInserted = myDb.insertDataPen(pen_number, selected_pen_type,0,Integer.parseInt(mInput_pen_capacity.getText().toString()));
                    if(isInserted == true){
                        Toast.makeText(getActivity(),"Pen added to database", Toast.LENGTH_SHORT).show();
                        Intent intent_breeder_generation = new Intent(getActivity(), CreatePen.class);
                        startActivity(intent_breeder_generation);
                        getDialog().dismiss();
                    }else{
                        Toast.makeText(getActivity(),"Pen not added to database", Toast.LENGTH_SHORT).show();
                    }
                    getDialog().dismiss();
                }else{
                    Toast.makeText(getActivity(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                }



            }

        });

        return view;
    }
}
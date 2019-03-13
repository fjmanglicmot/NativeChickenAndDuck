package com.example.cholomanglicmot.nativechickenandduck.GenerationsAndLinesDirectory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
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

public class CreateGenerationDialog extends DialogFragment {

    private static final String TAG = "CreateGenerationDialog";
    private EditText mInput_generation_number,mInput_generation_status;
    private RadioGroup radioGroup;
    private RadioButton radioButton,radioButton2,radioButton3;
    public RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;

    private EditText mInput_pen_capacity;
    private Button mActionOk;
    DatabaseHelper myDb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create_generation, container, false);
        mActionOk = view.findViewById(R.id.action_ok);
        mInput_generation_number = view.findViewById(R.id.generation_no);

        // find the radiobutton by returned id



        myDb = new DatabaseHelper(getContext());


        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!mInput_generation_number.getText().toString().isEmpty()){

                    boolean isInserted = myDb.insertDataGeneration(mInput_generation_number.getText().toString());
                    if(isInserted == true){
                        Toast.makeText(getActivity(),"Generation added to database", Toast.LENGTH_SHORT).show();
                        Intent intent_generation = new Intent(getActivity(), CreateGenerationsAndLines.class);
                        startActivity(intent_generation);
                       //does not work, kailangan may way ka para maupdate yung adapter mo
                        getDialog().dismiss();
                    }else{
                        Toast.makeText(getActivity(),"Generation not added to database", Toast.LENGTH_SHORT).show();
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
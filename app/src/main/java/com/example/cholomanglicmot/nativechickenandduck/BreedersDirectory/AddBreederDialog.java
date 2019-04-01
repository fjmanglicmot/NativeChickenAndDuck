package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

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
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

public class AddBreederDialog extends DialogFragment {

    private static final String TAG = "CreateGenerationDialog";
    private EditText male_quantity,female_quantity;

    private EditText mInput_pen_capacity;
    private Button mActionOk;
    DatabaseHelper myDb;
    Switch in_or_out;
    String option;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_breeder, container, false);
        final String breeder_tag = getArguments().getString("Breeder Tag");
        mActionOk = view.findViewById(R.id.action_ok);
        male_quantity = view.findViewById(R.id.male_quantity);
        female_quantity = view.findViewById(R.id.female_quantity);
        in_or_out = view.findViewById(R.id.in_or_out);
        textView = view.findViewById(R.id.textView);

        textView.setText("Add Breeders to "+ breeder_tag);

        // find the radiobutton by returned id



        myDb = new DatabaseHelper(getContext());
        in_or_out.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    option = "Within System";

                } else {

                    option = "Outside System";

                }

            }
        });


        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer current_male_count = 0;
                Integer current_female_count = 0;


                if(!female_quantity.getText().toString().isEmpty() || !male_quantity.getText().toString().isEmpty()){
                    Cursor cursor = myDb.getDataFromBreederInvWhereTag(breeder_tag);
                    cursor.moveToFirst();
                    if(cursor.getCount() != 0){
                        current_male_count = cursor.getInt(5);
                        current_female_count = cursor.getInt(6);
                    }
                    Integer new_male_count = Integer.parseInt(male_quantity.getText().toString())+ current_male_count;
                    Integer new_female_count = Integer.parseInt(female_quantity.getText().toString())+ current_female_count;
                    Integer new_total_count = new_female_count+new_male_count;
                    boolean isUpdated = myDb.updateMaleFemaleBreederCount(breeder_tag, new_male_count, new_female_count,new_total_count);
                    if(isUpdated == true){
                        Toast.makeText(getActivity(), "Succesfully added to "+breeder_tag, Toast.LENGTH_SHORT).show();
                        Intent intent_line = new Intent(getContext(), CreateBreeders.class);
                        startActivity(intent_line);

                    }

                }else{
                    Toast.makeText(getActivity(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                }



            }

        });

        return view;
    }


}
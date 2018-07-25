package com.example.cholomanglicmot.nativechickenandduck;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class BreederEggQualityRecordsDialog1 extends DialogFragment{

    private static final String TAG = "BreederEggQualityRecordsDialog1";

    private EditText breeder_egg_quality_records_nofertile;
    private Button mActionOk;
    private DialogListener dialogListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_breeder_egg_quality_dialog1, container, false);
        mActionOk = view.findViewById(R.id.action_ok);

        breeder_egg_quality_records_nofertile = view.findViewById(R.id.breeder_egg_quality_records_nofertile);

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!breeder_egg_quality_records_nofertile.getText().toString().isEmpty()){
                    String number_of_fertile_eggs = breeder_egg_quality_records_nofertile.getText().toString();
                    dialogListener.applyTexts(number_of_fertile_eggs);
                    Toast.makeText(getActivity(), "Added to database", Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                }else{
                    Toast.makeText(getActivity(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dialogListener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement dialog listener");
        }
    }

    public interface DialogListener{
        void applyTexts(String number_of_fertile_eggs);
    }
}

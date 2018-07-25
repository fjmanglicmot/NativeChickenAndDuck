package com.example.cholomanglicmot.nativechickenandduck;

        import android.database.Cursor;
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

public class CreatePenDialog extends DialogFragment {

    private static final String TAG = "CreatePenDialog";
    private EditText mInput_pen_number;
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
        mInput_pen_capacity = view.findViewById(R.id.capacity);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group1);
        radioButton = view.findViewById(R.id.radioButton);
        radioButton2 = view.findViewById(R.id.radioButton2);
        radioButton3 = view.findViewById(R.id.radioButton3);




        mInput_pen_number = view.findViewById(R.id.generation_no);

        myDb = new DatabaseHelper(getContext());



        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mInput_pen_number.getText().toString().isEmpty() && !mInput_pen_capacity.getText().toString().isEmpty() && radioGroup.getCheckedRadioButtonId() != -1){
                    Toast.makeText(getActivity(), "Pen added to database", Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                }else{
                    Toast.makeText(getActivity(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                } //ITO YUNG LUMA SA TAAS
             /*   Cursor res = myDb.getAllData();
           //     if(res.getCount() == 0){//check ang database kung meron na laman
                    boolean isInserted = myDb.insertDataPen(mInput_pen_number.getText().toString(), radioButton.getText().toString(),mInput_pen_capacity.getText().toString(), "0");
                    if(isInserted == true){
                        Toast.makeText(getActivity(),"Pen added to database", Toast.LENGTH_SHORT).show();
                        getDialog().dismiss();
                    }else{
                        Toast.makeText(getActivity(),"Pen not added to database", Toast.LENGTH_SHORT).show();
                    }
             *//*   }else{
                    boolean isUpdate = myDb.updateDataAddress("QUEBAI","BP",profile_region.getText().toString(),
                            profile_province.getText().toString(),profile_town.getText().toString(),
                            profile_barangay.getText().toString());
                    if(isUpdate == true){
                        Toast.makeText(getActivity(),"Database updated", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(),"Database not updated", Toast.LENGTH_SHORT).show();
                    }
                }*//*
*/

            }
        });

        return view;
    }
}

/*
     address_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0){//check ang database kung meron na laman
                    boolean isInserted = myDb.insertDataAddress("QUEBAI","BP", profile_region.getText().toString(),
                            profile_province.getText().toString(),profile_town.getText().toString(),
                            profile_barangay.getText().toString());
                    if(isInserted == true){
                        Toast.makeText(getActivity(),"Data inserted to database", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(),"Data not inserted to database", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    boolean isUpdate = myDb.updateDataAddress("QUEBAI","BP",profile_region.getText().toString(),
                            profile_province.getText().toString(),profile_town.getText().toString(),
                            profile_barangay.getText().toString());
                    if(isUpdate == true){
                        Toast.makeText(getActivity(),"Database updated", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(),"Database not updated", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
*/

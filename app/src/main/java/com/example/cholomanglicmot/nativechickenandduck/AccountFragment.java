package com.example.cholomanglicmot.nativechickenandduck;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private Button account_submit_button;
    private TextView profile_id;
    private EditText profile_breed;
    DatabaseHelper myDb;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        myDb = new DatabaseHelper(getContext());

        profile_id = view.findViewById(R.id.profile_id);
        profile_breed = view.findViewById(R.id.profile_breed);
        account_submit_button = (Button) view.findViewById(R.id.account_submit_button);


        Cursor cursor = myDb.getAllData();
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            profile_id.setText(cursor.getString(0));
            profile_breed.setHint(cursor.getString(1));
        }


        account_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Database updated", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}

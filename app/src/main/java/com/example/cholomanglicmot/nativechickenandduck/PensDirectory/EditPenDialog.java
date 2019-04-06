package com.example.cholomanglicmot.nativechickenandduck.PensDirectory;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.example.cholomanglicmot.nativechickenandduck.APIHelper;
import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class EditPenDialog extends DialogFragment {

    private static final String TAG = "EditPenDialog";


    private EditText new_pen_number, new_pen_capacity;
    private Button mActionOk;
    DatabaseHelper myDb;
    Context context;
    String pen_number;
    Integer pen_capacity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_pen, container, false);
        final String pen = getArguments().getString("Pen");
        myDb = new DatabaseHelper(getContext());
        mActionOk = view.findViewById(R.id.action_ok);

        new_pen_number = view.findViewById(R.id.new_pen_number);
        new_pen_capacity = view.findViewById(R.id.new_pen_capacity);
        context = getActivity().getApplicationContext();


        Cursor cursor = myDb.getAllDataFromPenWhere(pen);
        cursor.moveToFirst();

        if(cursor.getCount() != 0){
            pen_number = cursor.getString(2);
            pen_capacity = cursor.getInt(4);
        }

        new_pen_number.setHint(pen_number);
        new_pen_capacity.setHint(pen_capacity.toString());

        myDb = new DatabaseHelper(getContext());


        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(context, "yo", Toast.LENGTH_SHORT).show();




            }

        });

        return view;
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
package com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory;

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
import android.widget.TextView;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

public class ViewBrooderInventoryDialog extends DialogFragment {

    DatabaseHelper myDb;
    TextView textView, family_number, line_number, generation_number, brooder_male_count, brooder_female_count, brooder_total, brooder_date_added;
    EditText edit_male_count, edit_female_count;
    Button update, save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_view_brooder_inventory, container, false);
        final String brooder_tag = getArguments().getString("Brooder Tag");
        final String brooder_pen = getArguments().getString("Brooder Pen");



        textView = view.findViewById(R.id.textView);
        family_number = view.findViewById(R.id.family_number);
        line_number = view.findViewById(R.id.line_number);
        generation_number = view.findViewById(R.id.generation_number);
        brooder_male_count = view.findViewById(R.id.brooder_male_count);
        brooder_female_count = view.findViewById(R.id.brooder_female_count);
        brooder_total = view.findViewById(R.id.brooder_total);
        brooder_date_added = view.findViewById(R.id.brooder_date_added);

        edit_male_count = view.findViewById(R.id.edit_male_count);
        edit_female_count = view.findViewById(R.id.edit_female_count);

        update = view.findViewById(R.id.update);
        save = view.findViewById(R.id.save);

        textView.setText(brooder_tag);

        Cursor cursor = myDb.getDataFromBrooderInventoryWherePenAndID(brooder_tag);
        cursor.moveToFirst();






        return view;
    }
}
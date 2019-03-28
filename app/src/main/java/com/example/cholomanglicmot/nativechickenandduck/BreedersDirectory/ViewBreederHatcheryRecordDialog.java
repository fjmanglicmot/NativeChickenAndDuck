package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

public class ViewBreederHatcheryRecordDialog extends DialogFragment {

    DatabaseHelper myDb;
    TextView date_set, quantity, age_in_weeks, no_of_fertile, date_hatched, no_hatched ,textView;

    Button update, save;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_view_hatchery, container, false);
        final Integer breeder_inventory_idID = getArguments().getInt("Breeder Inventory ID");
        final Integer hatchery_id = getArguments().getInt("Breeder Hatchery ID");
        final String breeder_tag = getArguments().getString("Breeder Tag");



        myDb = new DatabaseHelper(getContext());


        date_set = view.findViewById(R.id.date_set);
        quantity = view.findViewById(R.id.quantity);
        age_in_weeks = view.findViewById(R.id.age_in_weeks); //galing sa brooder table
        no_of_fertile = view.findViewById(R.id.no_of_fertile);//galing sa brooder table
        date_hatched = view.findViewById(R.id.date_hatched);//galing sa brooder table
        no_hatched = view.findViewById(R.id.no_hatched);//galing sa brooder table
        textView = view.findViewById(R.id.textView);//galing sa brooder t
        update = view.findViewById(R.id.update);//galing sa brooder t
        save = view.findViewById(R.id.save);


        textView.setText(breeder_tag);
        Cursor cursor = myDb.getAllDataFromBreederHatcheryWhereID(hatchery_id);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            Integer egg_set = cursor.getInt(4);
            Float total_egg_weight_ = cursor.getFloat(4);
            Integer weeks = cursor.getInt(5);
            Integer fertile = cursor.getInt(6);
            Integer hatched = cursor.getInt(7);

////////////YUNG AGE IN WEEKS DAPAT ATA CINOCOMPUTE?????????
            date_set.setText(cursor.getString(2));
            quantity.setText(egg_set.toString());
            age_in_weeks.setText(weeks.toString());
            no_of_fertile.setText(fertile.toString());
            no_hatched.setText(hatched.toString());
            date_hatched.setText(cursor.getString(8));



        }



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDialog().dismiss();


            }
        });


        return view;
    }
}
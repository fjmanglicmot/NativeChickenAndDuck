package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

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

import java.util.ArrayList;
import java.util.List;

public class ViewReplacementInventoryDialog extends DialogFragment {

    DatabaseHelper myDb;
    TextView textView, family_number, line_number, generation_number, brooder_male_count, brooder_female_count, brooder_total, brooder_date_added,batching_date;
    Button update, save;
    List<String> famLineGen = new ArrayList<>();

    Integer fam_id = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_view_replacement_inventory, container, false);
        final String brooder_tag = getArguments().getString("Replacement Tag");
        final String brooder_pen = getArguments().getString("Replacement Pen");
        myDb = new DatabaseHelper(getContext());



        textView = view.findViewById(R.id.textView);
        family_number = view.findViewById(R.id.family_number); //galing sa brooder table
        line_number = view.findViewById(R.id.line_number);//galing sa brooder table
        generation_number = view.findViewById(R.id.generation_number);//galing sa brooder table
        batching_date = view.findViewById(R.id.batching_date);
        brooder_male_count = view.findViewById(R.id.brooder_male_count);
        brooder_female_count = view.findViewById(R.id.brooder_female_count);
        brooder_total = view.findViewById(R.id.brooder_total);
        brooder_date_added = view.findViewById(R.id.brooder_date_added);//galing sa brooder table


        update = view.findViewById(R.id.update);
        save = view.findViewById(R.id.save);

        textView.setText(brooder_tag);
//*   public static final String BROODER_INV_COL_0 = "ID";
//    public static final String BROODER_INV_COL_1 = "BROODER_INV_BROODER_ID";
//    public static final String BROODER_INV_COL_2 = "BROODER_INV_PEN_NUMBER"; //REFERENCES SA PEN
//    public static final String BROODER_INV_COL_3 = "BROODER_INV_BROODER_TAG";
//    public static final String BROODER_INV_COL_4 = "BROODER_INV_BATCHING_DATE";
//    public static final String BROODER_INV_COL_5 = "BROODER_INV_NUMBER_MALE";
//    public static final String BROODER_INV_COL_6 = "BROODER_INV_NUMBER_FEMALE";
//    public static final String BROODER_INV_COL_7 = "BROODER_INV_TOTAL";
//    public static final String BROODER_INV_COL_8 = "BROODER_INV_LAST_UPDATE";
//    public static final String BROODER_INV_COL_9 = "BROODER_INV_DELETED_AT";*/

/*        Cursor cursor_brooder_inventory = myDb.getAllDataFromBrooderInventory(); //para sa pagstore ng data sa arraylist
        cursor_brooder_inventory.moveToFirst();
        if(cursor_brooder_inventory.getCount() == 0){
            //show message
            do{
                Cursor cursor_brooder = myDb.getAllDataFromBrooders();
                cursor_brooder.moveToFirst();
                if(cursor_brooder.getCount() != 0){
                    fam_id = myDb.getFamIDFromBrooders(cursor_brooder_inventory.getInt(1));
                    famLineGen = myDb.getFamLineGen(fam_id);
                }
            }while (cursor_brooder_inventory.moveToNext());


        }*/

        Cursor cursor = myDb.getDataFromReplacementInventoryWherePenAndID(brooder_tag, brooder_pen);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            textView.setText(cursor.getString(3));
            batching_date.setText(cursor.getString(4));
//            family_number.setText(famLineGen.get(0));
            //line_number.setText(famLineGen.get(1));
           // generation_number.setText(famLineGen.get(2));

            brooder_male_count.setText(cursor.getString(5));
            brooder_female_count.setText(cursor.getString(6));
            brooder_total.setText(cursor.getString(7));

        }

       /* update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_female_count.getVisibility() == View.GONE){
                    edit_male_count.setVisibility(View.VISIBLE);
                    edit_female_count.setVisibility(View.VISIBLE);
                    brooder_male_count.setVisibility(View.GONE);
                    brooder_female_count.setVisibility(View.GONE);
                    edit_female_count.setText(brooder_female_count.getText().toString());
                    edit_male_count.setText(brooder_male_count.getText().toString());
                    update.setText("CANCEL");

                }else{
                    edit_male_count.setVisibility(View.GONE);
                    edit_female_count.setVisibility(View.GONE);
                    brooder_male_count.setVisibility(View.VISIBLE);
                    brooder_female_count.setVisibility(View.VISIBLE);
                    edit_female_count.setText(brooder_female_count.getText().toString());
                    edit_male_count.setText(brooder_male_count.getText().toString());
                    update.setText("UPDATE");

                }

            }
        });
*/
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if(edit_female_count.getVisibility() == View.VISIBLE){
                    boolean isUpdated = myDb.updateMaleFemaleCount(brooder_tag, Integer.parseInt(edit_male_count.getText().toString()),Integer.parseInt(edit_female_count.getText().toString()) );
                    if(isUpdated == true){
                        Toast.makeText(getContext(), "Updated male and female count", Toast.LENGTH_SHORT).show();
                        getDialog().dismiss();
                    }else{
                        Toast.makeText(getContext(), "Error updating male and female count", Toast.LENGTH_SHORT).show();
                    }
                }else{*/
                    getDialog().dismiss();
                //}

            }
        });


        return view;
    }
}
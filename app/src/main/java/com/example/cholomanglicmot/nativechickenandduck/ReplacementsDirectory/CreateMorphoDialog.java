package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

import android.app.AlertDialog;
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
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;

public class CreateMorphoDialog extends DialogFragment{
    private EditText morpho_height, morpho_weight, morpho_body_length,morpho_chest_circumference, morpho_wing_span, morpho_shank_length;
    private String  replacement_pen;
    private String replacement_inv_tag, pheno_record,pheno_sex,pheno_tag, pheno_date;
    TextView replacement_tag;
    private Button mActionOk;

    DatabaseHelper myDb;

    ArrayList<Replacement_PhenoMorphoRecords> arrayListPhenoMorpho = new ArrayList<>();
    ArrayList<Replacement_PhenoMorphoRecords>arrayList_temp = new ArrayList<>();
    ArrayList<Replacement_Inventory>arrayListReplacementInventory = new ArrayList<>();
    ArrayList<Replacement_Inventory>arrayListReplacementInventory1 = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create_morpho_dialog, container, false);
        pheno_record = getArguments().getString("Phenotypic Records");
        pheno_sex = getArguments().getString("Phenotypic Sex");
        pheno_tag = getArguments().getString("Phenotypic Tag");
        pheno_date = getArguments().getString("Phenotypic Date");
        replacement_pen = getArguments().getString("Replacement Pen");
        replacement_inv_tag = getArguments().getString("Replacement Tag");

        replacement_tag = view.findViewById(R.id.replacement_tag);
        replacement_tag.setText(pheno_record);




        mActionOk = view.findViewById(R.id.action_ok);

        morpho_height = view.findViewById(R.id.morpho_height);
        morpho_weight = view.findViewById(R.id.morpho_weight);
        morpho_body_length = view.findViewById(R.id.morpho_body_length);
        morpho_chest_circumference = view.findViewById(R.id.morpho_chest_circumference);
        morpho_wing_span = view.findViewById(R.id.morpho_wing_span);
        morpho_shank_length = view.findViewById(R.id.morpho_shank_length);


        myDb = new DatabaseHelper(getContext());




        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuffer morpho = new StringBuffer();
                StringBuffer buffer = new StringBuffer();
                if(!morpho_height.getText().toString().isEmpty() && !morpho_weight.getText().toString().isEmpty() && !morpho_body_length.getText().toString().isEmpty()&&!morpho_chest_circumference.getText().toString().isEmpty()&&!morpho_wing_span.getText().toString().isEmpty()&&!morpho_shank_length.getText().toString().isEmpty()){

                    morpho.append(morpho_height.getText().toString() +", ");
                    morpho.append(morpho_weight.getText().toString() +", ");
                    morpho.append(morpho_body_length.getText().toString() +", ");
                    morpho.append(morpho_chest_circumference.getText().toString() +", ");
                    morpho.append(morpho_wing_span.getText().toString() +", ");
                    morpho.append(morpho_shank_length.getText().toString());


                    ////DATABASE OPERATIONS
                    Cursor cursor_inventory = myDb.getAllDataFromReplacementInventory();
                    cursor_inventory.moveToFirst();

                    if (cursor_inventory.getCount() == 0) {

                    } else {
                        do {
                            Replacement_Inventory replacement_inventory = new Replacement_Inventory(cursor_inventory.getInt(0), cursor_inventory.getInt(1), null,null,null,cursor_inventory.getString(2), cursor_inventory.getString(3), cursor_inventory.getString(4), cursor_inventory.getInt(5), cursor_inventory.getInt(6), cursor_inventory.getInt(7), null, cursor_inventory.getString(8), cursor_inventory.getString(9));
                            arrayListReplacementInventory.add(replacement_inventory);
                        } while (cursor_inventory.moveToNext());
                    }

                    Cursor cursor_pheno_morpho = myDb.getAllDataFromPhenoMorphoRecords();
                    cursor_pheno_morpho.moveToFirst();

                    if(cursor_pheno_morpho.getCount()==0){
                        Toast.makeText(getContext(),"No data from pheno and morpho records", Toast.LENGTH_SHORT).show();
                    }else{
                        do{
                            Replacement_PhenoMorphoRecords replacement_phenoMorphoRecords = new Replacement_PhenoMorphoRecords(cursor_pheno_morpho.getInt(0), cursor_pheno_morpho.getInt(1), cursor_pheno_morpho.getString(2), cursor_pheno_morpho.getString(3), cursor_pheno_morpho.getString(4), cursor_pheno_morpho.getString(5),  null, null);
                            arrayList_temp.add(replacement_phenoMorphoRecords);
                        }while (cursor_pheno_morpho.moveToNext());

                    }
                    //get inventory given the tag
                    for (int i = 0; i<arrayListReplacementInventory.size();i++){
                        if (arrayListReplacementInventory.get(i).getReplacement_inv_replacement_tag().equals(replacement_inv_tag)){
                            arrayListReplacementInventory1.add(arrayListReplacementInventory.get(i));

                        }
                    }


                    //insert data sa brooderinv na given na tag

                    //Toast.makeText(getContext(), arrayListReplacementInventory1.get(0).getId().toString(), Toast.LENGTH_SHORT).show();
                    buffer.append(arrayListReplacementInventory1.get(0).getId().toString() + "\n");
                    buffer.append(pheno_date + "\n");
                    buffer.append(pheno_sex + "\n");
                    buffer.append(pheno_tag + "\n");
                    buffer.append(pheno_record + "\n");
                    buffer.append(morpho.toString() + "\n");
                    String morphos  = morpho.toString();
                    //boolean isInserted = myDb.insertDataGeneration(arrayListReplacementInventory1.get(0).getId());
                    Integer inv_id = arrayListReplacementInventory1.get(0).getId();
                    /**/
                    boolean isInserted = myDb.insertDataReplacementPhenoMorphoRecords(inv_id, pheno_date, pheno_sex, pheno_tag, pheno_record, morphos);
                    //

                    if(isInserted == true){
                        Toast.makeText(getContext(), "Added to database", Toast.LENGTH_SHORT).show();
                        getDialog().dismiss();
                    }else{
                        Toast.makeText(getContext(), "Error adding to database", Toast.LENGTH_SHORT).show();

                    }

                    showMessage("Pheno", buffer.toString());




                }else{
                    Toast.makeText(getContext(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                }

            }
        });









        return view;
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
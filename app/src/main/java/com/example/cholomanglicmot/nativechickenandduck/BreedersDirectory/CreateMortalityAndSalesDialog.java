package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateMortalityAndSalesDialog extends DialogFragment{
    private EditText date_eggs_set,    number_eggs_set,            fertile,    eggs_hatched,            date_eggs_hatched;
    Switch brooder_switch_other_day;
    LinearLayout yes;
    Spinner outside_generation_spinner,
    outside_line_spinner,
            outside_family_spinner,
    pen;
    private BottomNavigationView mMainNav;
    private Button mActionOk;
    DatabaseHelper myDb;
    Calendar calendar,calendar2;
    ArrayList<Breeder_Inventory> arrayListBrooderInventory = new ArrayList<>();
    ArrayList<Breeder_Inventory>arrayList_temp = new ArrayList<>();

    MortalityFragment mortFragment;
    EggsSalesFragment eggsSalesFragment;
    SalesFragment salesFragment;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create_mortality_and_sales_records, container, false);
        myDb = new DatabaseHelper(getContext());
        final String breeder_tag = getArguments().getString("Breeder Tag");



        mortFragment = new MortalityFragment();
        salesFragment = new SalesFragment();
        eggsSalesFragment = new EggsSalesFragment();


        date_eggs_set= view.findViewById(R.id.date_eggs_set);
        number_eggs_set= view.findViewById(R.id.number_eggs_set);
        fertile= view.findViewById(R.id.fertile);
        eggs_hatched= view.findViewById(R.id.eggs_hatched);
        brooder_switch_other_day= view.findViewById(R.id.brooder_switch_other_day);
        yes= view.findViewById(R.id.yes);
        outside_generation_spinner= view.findViewById(R.id.outside_generation_spinner);
        outside_line_spinner= view.findViewById(R.id.outside_line_spinner);
        outside_family_spinner= view.findViewById(R.id.outside_family_spinner);
        date_eggs_hatched= view.findViewById(R.id.date_eggs_hatched);
        mMainNav = view.findViewById(R.id.bottom_nav);
        pen= view.findViewById(R.id.pen);

        /*date_eggs_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        selectedMonth++;
                        date_eggs_set.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
                        calendar.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });
*/

       /* date_eggs_hatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar2 = Calendar.getInstance();
                int year = calendar2.get(Calendar.YEAR);
                int month = calendar2.get(Calendar.MONTH);
                int day = calendar2.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        selectedMonth++;
                        date_eggs_hatched.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
                        calendar2.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });*/


       /* mActionOk = view.findViewById(R.id.action_ok);
        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!date_eggs_set.getText().toString().isEmpty() && !date_eggs_hatched.getText().toString().isEmpty() && !number_eggs_set.getText().toString().isEmpty() && !fertile.getText().toString().isEmpty() && !eggs_hatched.getText().toString().isEmpty() ){
                    //kunin mo yung

                    //kunin mo muna yung id ng breeder_inv given yung tag

                    Cursor cursor = myDb.getAllDataFromBreederInventory();
                    cursor.moveToFirst();

                    if(cursor.getCount() != 0){
                        do{
                            Breeder_Inventory breeder_inventory = new  Breeder_Inventory(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8),cursor.getString(9),null,null,null);
                            arrayListBrooderInventory.add(breeder_inventory);

                        }while(cursor.moveToNext());
                    }


                    for(int i=0;i<arrayListBrooderInventory.size();i++){
                        if(arrayListBrooderInventory.get(i).getBrooder_inv_brooder_tag().equals(breeder_tag)){
                            arrayList_temp.add(arrayListBrooderInventory.get(i));
                        }
                    }

////////////////////////


                    boolean isInserted = myDb.insertHatcheryRecords(arrayList_temp.get(0).getId(), date_eggs_set.getText().toString(), null, Integer.parseInt(number_eggs_set.getText().toString()),null,Integer.parseInt(fertile.getText().toString()),Integer.parseInt(eggs_hatched.getText().toString()),date_eggs_hatched.getText().toString(),null);

                    if (isInserted == true){
                        Toast.makeText(getContext(),"Hatchery Record added to database", Toast.LENGTH_LONG).show();
                        Intent intent_line = new Intent(getActivity(), HatcheryRecords.class);
                        startActivity(intent_line);
                        getDialog().dismiss();

                    }else{
                        Toast.makeText(getContext(),"Error adding to database", Toast.LENGTH_LONG).show();
                    }







                }else{
                    Toast.makeText(getContext(),"Please fill any empty fields", Toast.LENGTH_LONG).show();
                }




            }

        });
*/      setFragment(mortFragment);
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_mort:
                        setFragment(mortFragment);
                        return true;

                    case R.id.nav_sales:
                        setFragment(salesFragment);
                        return true;

                    case R.id.nav_egg_sales:
                        setFragment(eggsSalesFragment);
                        return true;

                    default:
                        return false;
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
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mort_frame, fragment);
        fragmentTransaction.commit();
    }

}
package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;

public class RecyclerAdapter_Mortality_and_Sales extends RecyclerView.Adapter<RecyclerAdapter_Mortality_and_Sales.RecyclerViewHolder> {

    ArrayList<Mortality_Sales> arrayList = new ArrayList<>();

    RecyclerAdapter_Mortality_and_Sales(ArrayList<Mortality_Sales> arrayList){
        this.arrayList = arrayList;


    }

    Context context;

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mortality_row_layout,parent, false);
        context = parent.getContext();
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {



        final Mortality_Sales mortality_sales = arrayList.get(position);

        final Bundle args = new Bundle();
        //args.putString("Breeder Tag", mortality_sales.getBrooder_inv_brooder_tag());
        holder.date.setText(mortality_sales.getDate());
        //holder.tag.setText(mortality_sales.getBreeder_id().toString());
        holder.type.setText(mortality_sales.getType());
        holder.category.setText(mortality_sales.getCategory());
        holder.price.setText(mortality_sales.getPrice().toString());
        holder.male.setText(mortality_sales.getMale_count().toString());
        holder.female.setText(mortality_sales.getFemale_count().toString());
        holder.total.setText(mortality_sales.getTotal().toString());
        holder.reason.setText(mortality_sales.getReason());
        holder.remarks.setText(mortality_sales.getRemarks());
/*
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Deleted at " +position, Toast.LENGTH_SHORT).show();
            }
        });
*/





    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView date,tag, type, category, price, male, female,total, reason, remarks;

      //  ImageButton delete;

        RecyclerViewHolder(View view){
            super(view);
            date = view.findViewById(R.id.date);
            //tag = view.findViewById(R.id.tag);
            type = view.findViewById(R.id.type);
            category = view.findViewById(R.id.category);
            price = view.findViewById(R.id.price);
            male = view.findViewById(R.id.male);
            female = view.findViewById(R.id.female);
            total = view.findViewById(R.id.total);
            reason = view.findViewById(R.id.reason);
            remarks = view.findViewById(R.id.remarks);
         //   delete = view.findViewById(R.id.delete);





        }


    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}

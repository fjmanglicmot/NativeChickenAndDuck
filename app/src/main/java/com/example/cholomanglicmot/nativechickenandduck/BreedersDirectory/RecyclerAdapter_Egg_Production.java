package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.Brooder_FeedingRecords;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;

public class RecyclerAdapter_Egg_Production extends RecyclerView.Adapter<RecyclerAdapter_Egg_Production.RecyclerViewHolder> {


    ArrayList<Egg_Production> arrayListBrooderFeedingRecords = new ArrayList<>();

    RecyclerAdapter_Egg_Production(ArrayList<Egg_Production> arrayListBrooderFeedingRecords  ){
        this.arrayListBrooderFeedingRecords = arrayListBrooderFeedingRecords;
        this.context = context;

    }

    Context context;


    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.egg_prod_row_layout,parent, false);
        context = parent.getContext();
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);



        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {

        final Egg_Production egg_production = arrayListBrooderFeedingRecords.get(position);

        holder.date.setText(egg_production.getDate());
        holder.intact.setText(egg_production.getIntact().toString());
        //holder.average_weight.setText(egg_production.getWeight().toString());
        holder.weight.setText(egg_production.getWeight().toString());
        holder.broken.setText(egg_production.getBroken().toString());
        holder.rejected.setText(egg_production.getRejects().toString());
        holder.remarks.setText(egg_production.getRemarks());

        holder.deleted_at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Deleted at "+ position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListBrooderFeedingRecords.size();
    }



    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView date,intact, weight, average_weight, broken, rejected,remarks;
        ImageButton deleted_at;



        RecyclerViewHolder(View view){
            super(view);
            date = view.findViewById(R.id.date);
            intact = view.findViewById(R.id.intact);
            weight = view.findViewById(R.id.weight);
            average_weight = view.findViewById(R.id.average_weight);
            broken = view.findViewById(R.id.broken);
            rejected = view.findViewById(R.id.rejected);
            remarks = view.findViewById(R.id.remarks);
            deleted_at = view.findViewById(R.id.delete);



        }


    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void updateArray(ArrayList<Brooder_FeedingRecords> data)
    {
        data.clear();
        data.addAll(data);
        notifyDataSetChanged();
    }


}

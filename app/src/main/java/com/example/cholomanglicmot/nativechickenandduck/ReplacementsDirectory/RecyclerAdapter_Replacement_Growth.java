package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

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

import com.example.cholomanglicmot.nativechickenandduck.BroodersDirectory.Brooder_GrowthRecords;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;

public class RecyclerAdapter_Replacement_Growth extends RecyclerView.Adapter<RecyclerAdapter_Replacement_Growth.RecyclerViewHolder> {


    ArrayList<Replacement_GrowthRecords> arrayListBrooderGrowthRecords = new ArrayList<>();

    RecyclerAdapter_Replacement_Growth(ArrayList<Replacement_GrowthRecords> arrayListBrooderGrowthRecords){
        this.arrayListBrooderGrowthRecords = arrayListBrooderGrowthRecords;
        this.context = context;

    }

    Context context;


    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.replacement_growth_row_layout,parent, false);
        context = parent.getContext();
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {

        final Replacement_GrowthRecords brooder_growthRecords = arrayListBrooderGrowthRecords.get(position);

        holder.brooder_growth_date_added.setText(brooder_growthRecords.getReplacement_growth_date_collected());
        holder.brooder_growth_collection_day.setText(brooder_growthRecords.getReplacement_growth_collection_day().toString());
        //holder.brooder_growth_inventory_tag.setText(brooder_growthRecords.getReplacement_growth_tag().toString());
        holder.brooder_growth_male_count.setText(brooder_growthRecords.getReplacement_growth_male_quantity().toString());
        holder.brooder_growth_male_weight.setText(brooder_growthRecords.getReplacement_growth_male_weight().toString());
        holder.brooder_growth_female_count.setText(brooder_growthRecords.getReplacement_growth_female_quantity().toString());
        holder.brooder_growth_female_weight.setText(brooder_growthRecords.getReplacement_growth_female_weight().toString());
        holder.brooder_growth_total_count.setText(brooder_growthRecords.getReplacement_growth_total_quantity().toString());
        holder.brooder_growth_total_weight.setText(brooder_growthRecords.getReplacement_growth_total_weight().toString());

        holder.brooder_growth_deleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Deleted at "+position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListBrooderGrowthRecords.size();
    }



    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView brooder_growth_date_added;
        TextView brooder_growth_collection_day;
        TextView brooder_growth_inventory_tag;
        TextView brooder_growth_male_count;
        TextView brooder_growth_male_weight;
        TextView brooder_growth_female_count;
        TextView brooder_growth_female_weight;
        TextView brooder_growth_total_count;
        TextView brooder_growth_total_weight;
        ImageButton brooder_growth_deleted;






        RecyclerViewHolder(View view){
            super(view);


            brooder_growth_date_added = view.findViewById(R.id.replacement_growth_date_added);
            brooder_growth_collection_day = view.findViewById(R.id.replacement_growth_collection_day);
            brooder_growth_inventory_tag = view.findViewById(R.id.replacement_growth_inventory_tag);
            brooder_growth_male_count = view.findViewById(R.id.replacement_growth_male_count);
            brooder_growth_male_weight = view.findViewById(R.id.replacement_growth_male_weight);
            brooder_growth_female_count = view.findViewById(R.id.replacement_growth_female_count);
            brooder_growth_female_weight = view.findViewById(R.id.replacement_growth_female_weight);
            brooder_growth_total_count = view.findViewById(R.id.replacement_growth_total_count);
            brooder_growth_total_weight = view.findViewById(R.id.replacement_growth_total_weight);
            brooder_growth_deleted = view.findViewById(R.id.replacement_growth_delete);




        }


    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void updateArray(ArrayList<Brooder_GrowthRecords> data)
    {
        data.clear();
        data.addAll(data);
        notifyDataSetChanged();
    }


}

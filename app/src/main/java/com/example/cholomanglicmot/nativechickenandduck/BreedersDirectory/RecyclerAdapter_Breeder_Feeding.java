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

public class RecyclerAdapter_Breeder_Feeding extends RecyclerView.Adapter<RecyclerAdapter_Breeder_Feeding.RecyclerViewHolder> {


    ArrayList<Breeder_FeedingRecords> arrayListBrooderFeedingRecords = new ArrayList<>();

    RecyclerAdapter_Breeder_Feeding(ArrayList<Breeder_FeedingRecords> arrayListBrooderFeedingRecords  ){
        this.arrayListBrooderFeedingRecords = arrayListBrooderFeedingRecords;
        this.context = context;

    }

    Context context;


    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brooder_feeding_row_layout,parent, false);
        context = parent.getContext();
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {

        final Breeder_FeedingRecords brooder_feedingRecords = arrayListBrooderFeedingRecords.get(position);
        holder.brooder_feeding_date.setText(brooder_feedingRecords.getBrooder_feeding_date_collected());
       // holder.brooder_feeding_tag.setText(brooder_feedingRecords.); data not from brooder_feedingRecords
      //  holder.brooder_feeding_offered.setText(brooder_feedingRecords.getBrooder_feeding_offered().toString());
       // holder.brooder_feeding_refused.setText(brooder_feedingRecords.getBrooder_feeding_refused().toString());
     //   holder.brooder_feeding_remarks.setText(brooder_feedingRecords.getBrooder_feeding_remarks());

        holder.brooder_feeding_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Deleted at "+position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayListBrooderFeedingRecords.size();
    }



    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView brooder_feeding_date;
        TextView brooder_feeding_tag;
        TextView brooder_feeding_offered;
        TextView brooder_feeding_refused;
        TextView brooder_feeding_remarks;

        ImageButton brooder_feeding_delete;

        RecyclerViewHolder(View view){
            super(view);
            brooder_feeding_date = view.findViewById(R.id.brooder_feeding_date);
            brooder_feeding_tag = view.findViewById(R.id.brooder_feeding_tag);
            brooder_feeding_offered = view.findViewById(R.id.brooder_feeding_offered);
         //   brooder_feeding_refused= view.findViewById(R.id.brooder_feeding_refused);
           // brooder_feeding_remarks= view.findViewById(R.id.brooder_feeding_remarks);
            brooder_feeding_delete = view.findViewById(R.id.brooder_feeding_delete);


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

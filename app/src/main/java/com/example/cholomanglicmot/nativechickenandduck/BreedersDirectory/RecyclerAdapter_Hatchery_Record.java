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

public class RecyclerAdapter_Hatchery_Record extends RecyclerView.Adapter<RecyclerAdapter_Hatchery_Record.RecyclerViewHolder> {


    ArrayList<Hatchery_Records> arrayListBrooderFeedingRecords = new ArrayList<>();

    RecyclerAdapter_Hatchery_Record(ArrayList<Hatchery_Records> arrayListBrooderFeedingRecords  ){
        this.arrayListBrooderFeedingRecords = arrayListBrooderFeedingRecords;
        this.context = context;

    }

    Context context;


    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hatchery_row_layout,parent, false);
        context = parent.getContext();
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);



        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {

        final Hatchery_Records hatchery_records = arrayListBrooderFeedingRecords.get(position);

        holder.date_set.setText(hatchery_records.getDate());
        holder.quantity.setText(hatchery_records.getEggs_set().toString());
        holder.age.setText("ID "+hatchery_records.getId().toString());
        holder.fertile.setText(hatchery_records.getFertile().toString());
        holder.hatched.setText(hatchery_records.getDate_hatched().toString());
        holder.number_hatched.setText(hatchery_records.getHatched().toString());
        holder.update.setText(null);

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
        TextView date_set,quantity,age,fertile,hatched,number_hatched,update;
        ImageButton deleted_at;



        RecyclerViewHolder(View view){
            super(view);
            date_set = view.findViewById(R.id.date_set);
            quantity = view.findViewById(R.id.quantity);
            age = view.findViewById(R.id.age);
            fertile = view.findViewById(R.id.fertile);
            hatched = view.findViewById(R.id.hatched);
            number_hatched = view.findViewById(R.id.number_hatched);
            update = view.findViewById(R.id.update);
            date_set = view.findViewById(R.id.date_set);

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

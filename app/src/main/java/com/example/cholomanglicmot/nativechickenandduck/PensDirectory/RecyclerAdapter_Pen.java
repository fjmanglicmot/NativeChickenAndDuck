package com.example.cholomanglicmot.nativechickenandduck.PensDirectory;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.ArrayList;

public class RecyclerAdapter_Pen extends RecyclerView.Adapter<RecyclerAdapter_Pen.RecyclerViewHolder> {

    ArrayList<Pen> arrayList = new ArrayList<>();
    RecyclerAdapter_Pen(ArrayList<Pen> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pen_row_layout,parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Pen pen = arrayList.get(position);
        holder.pen_number.setText(pen.getPen_number());
        holder.pen_type.setText(pen.getPen_type());
        holder.pen_inventory.setText(pen.getPen_inventory().toString());
        holder.pen_capacity.setText(pen.getPen_capacity().toString());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView pen_number, pen_type, pen_inventory, pen_capacity;
        RecyclerViewHolder(View view){
            super(view);
            pen_number = (TextView)view.findViewById(R.id.pen_number);
            pen_type = (TextView)view.findViewById(R.id.pen_type);
            pen_inventory = (TextView)view.findViewById(R.id.pen_inventory);
            pen_capacity = (TextView)view.findViewById(R.id.pen_capacity);

        }
    }
}

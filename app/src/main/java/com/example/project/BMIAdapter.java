package com.example.project;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BMIAdapter extends RecyclerView.Adapter<BMIAdapter.BMIViewHolder> {

    private final List<BMIRecord> bmiRecords;

    public BMIAdapter(List<BMIRecord> bmiRecords) {
        this.bmiRecords = bmiRecords;
    }

    @NonNull
    @Override
    public BMIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bmi_record, parent, false);
        return new BMIViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BMIViewHolder holder, int position) {
        BMIRecord record = bmiRecords.get(position);

        holder.tvWeight.setText("Weight: " + record.getWeight() + " kg");
        holder.tvHeight.setText("Height: " + record.getHeight() + " m");
        holder.tvDate.setText("Date: " + record.getDate());

        double bmi = Double.parseDouble(record.getBmi());
        holder.tvBmi.setText("BMI: " + record.getBmi());

        // Apply color based on BMI value
        if (bmi < 18.5) {
            holder.tvBmi.setTextColor(Color.YELLOW); // Underweight
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            holder.tvBmi.setTextColor(Color.GREEN); // Normal
        } else if (bmi >= 25 && bmi <= 29.9) {
            holder.tvBmi.setTextColor(Color.parseColor("#FF9800")); // Overweight (Orange)
        } else {
            holder.tvBmi.setTextColor(Color.RED); // Obese
        }
    }

    @Override
    public int getItemCount() {
        return bmiRecords.size();
    }

    public static class BMIViewHolder extends RecyclerView.ViewHolder {
        TextView tvWeight, tvHeight, tvBmi, tvDate;

        public BMIViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWeight = itemView.findViewById(R.id.tv_weight);
            tvHeight = itemView.findViewById(R.id.tv_height);
            tvBmi = itemView.findViewById(R.id.tv_bmi);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}

package com.example.project;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.project.BMI_DATABASE.BMIDao;

public class BMIAdapter extends RecyclerView.Adapter<BMIAdapter.BMIViewHolder> {

    private final List<BMIRecord> bmiRecords;
    private final BMIDao bmiDao; // Add BMIDao reference

    public BMIAdapter(List<BMIRecord> bmiRecords, BMIDao bmiDao) {
        this.bmiRecords = bmiRecords;
        this.bmiDao = bmiDao; // Initialize BMIDao
    }

    @NonNull
    @Override
    public BMIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bmi_record, parent, false);
        return new BMIViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BMIViewHolder holder, int position) {
        holder.itemView.setBackgroundColor(Color.WHITE); // Reset background color
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

        // Long click to delete the record
        holder.itemView.setOnLongClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                // Change background color temporarily to show effect
                v.setBackgroundColor(Color.LTGRAY);

                // Remove the record from the database
                bmiDao.delete(record); // Delete the record from the database

                // Use postDelayed to allow the user to see the visual effect
                v.postDelayed(() -> {
                    // Remove the item from the list
                    bmiRecords.remove(adapterPosition);
                    // Notify the adapter that an item was removed
                    notifyItemRemoved(adapterPosition);
                    // Optionally, show a toast message
                    Toast.makeText(v.getContext(), "BMI Record Deleted", Toast.LENGTH_SHORT).show();
                }, 300); // Delay for 300ms to show the visual effect
            }
            return true; // Return true to indicate the click was handled
        });
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

    public void addBMIRecord(BMIRecord newRecord) {
        bmiRecords.add(0, newRecord); // Add to the top of the list
        notifyItemInserted(0); // Notify that the item was inserted at position 0
    }

}

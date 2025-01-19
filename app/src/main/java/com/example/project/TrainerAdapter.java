package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.TrainerViewHolder> {

    private List<Trainer> trainerList;

    public TrainerAdapter(List<Trainer> trainerList) {
        this.trainerList = trainerList;
    }

    @NonNull
    @Override
    public TrainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trainer, parent, false);
        return new TrainerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainerViewHolder holder, int position) {
        Trainer trainer = trainerList.get(position);
        holder.trainerName.setText("Name: " + trainer.getName());
        holder.trainerAge.setText("Age: " + trainer.getAge());
        holder.trainerExperience.setText("Experience: " + trainer.getExperience() + " years");
        holder.trainerPicture.setImageResource(trainer.getImageResId());
    }

    @Override
    public int getItemCount() {
        return trainerList.size();
    }

    static class TrainerViewHolder extends RecyclerView.ViewHolder {
        TextView trainerName, trainerAge, trainerExperience;
        ImageView trainerPicture;

        public TrainerViewHolder(@NonNull View itemView) {
            super(itemView);
            trainerName = itemView.findViewById(R.id.tv_trainer_name);
            trainerAge = itemView.findViewById(R.id.tv_trainer_age);
            trainerExperience = itemView.findViewById(R.id.tv_trainer_experience);
            trainerPicture = itemView.findViewById(R.id.iv_trainer_picture);
        }
    }
}

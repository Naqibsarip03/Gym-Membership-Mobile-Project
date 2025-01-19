package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PersonalTrainerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trainer, container, false);

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rv_trainers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create a list of trainers
        List<Trainer> trainerList = new ArrayList<>();
        trainerList.add(new Trainer("John Doe", 30, 5, R.drawable.profile));
        trainerList.add(new Trainer("Jane Smith", 28, 3, R.drawable.woman));
        trainerList.add(new Trainer("Mike Johnson", 35, 10, R.drawable.boy));

        // Set the adapter for the RecyclerView
        TrainerAdapter adapter = new TrainerAdapter(trainerList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}

package com.example.project;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BMIFragment extends Fragment {

    private RecyclerView recyclerView;
    private BMIAdapter bmiAdapter;
    private List<BMIRecord> bmiRecords;

    private EditText etWeight, etHeight, etDate;
    private TextView tvBmiResult, tvBmiInfoTitle;
    private LinearLayout layoutBmiInfoContent;
    private Button btnCalculateBmi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);

        // Initialize views
        etWeight = view.findViewById(R.id.et_weight);
        etHeight = view.findViewById(R.id.et_height);
        etDate = view.findViewById(R.id.et_date);
        tvBmiResult = view.findViewById(R.id.tv_bmi_result);
        btnCalculateBmi = view.findViewById(R.id.btn_calculate_bmi);
        tvBmiInfoTitle = view.findViewById(R.id.tv_bmi_info_title);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize sample data (or start with an empty list)
        bmiRecords = new ArrayList<>();
        bmiAdapter = new BMIAdapter(bmiRecords);
        recyclerView.setAdapter(bmiAdapter);

        // Set up date picker
        etDate.setOnClickListener(v -> showDatePickerDialog());

        // Set button click listener
        btnCalculateBmi.setOnClickListener(v -> calculateBMI());

        return view;
    }

    private void showDatePickerDialog() {
        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create and show the DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                    // Set the selected date in the EditText
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    etDate.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void calculateBMI() {
        // Get user input
        String weightStr = etWeight.getText().toString();
        String heightStr = etHeight.getText().toString();
        String dateStr = etDate.getText().toString();

        // Validate input
        if (TextUtils.isEmpty(weightStr) || TextUtils.isEmpty(heightStr) || TextUtils.isEmpty(dateStr)) {
            Toast.makeText(getContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Parse weight and height
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);

            if (weight <= 0 || height <= 0) {
                Toast.makeText(getContext(), "Please enter valid weight and height values.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calculate BMI
            double bmi = weight / (height * height);
            String bmiResult = String.format("%.2f", bmi);

            // Display the BMI result
            tvBmiResult.setText("Your BMI: " + bmiResult);

            // Add the result to the RecyclerView
            BMIRecord newRecord = new BMIRecord(weightStr, heightStr, bmiResult, dateStr);
            bmiRecords.add(0,newRecord);
            bmiAdapter.notifyItemInserted(0);
            recyclerView.scrollToPosition(0);

        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Invalid input format. Please enter numbers only.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        // Set a fixed height for RecyclerView based on item size
        int itemHeight = getResources().getDimensionPixelSize(R.dimen.recycler_item_height); // Define in dimens.xml
        int visibleItemCount = 3;
        ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
        params.height = itemHeight * visibleItemCount;
        recyclerView.setLayoutParams(params);
    }
}

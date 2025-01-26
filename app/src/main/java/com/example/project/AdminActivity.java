package com.example.project;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdminActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PaymentAdapter adapter;
    //private DBHelper dbHelper;
    private List<Payment> allPayments; // Full list of payments
    private List<Payment> filteredPayments; // Filtered list for the adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Initialize RecyclerView and DBHelper
        recyclerView = findViewById(R.id.recyclerView);
        //dbHelper = new DBHelper(this);
        //allPayments = dbHelper.getAllPayments();
        filteredPayments = new ArrayList<>(allPayments); // Initialize filtered list

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new PaymentAdapter(filteredPayments, payment -> approvePayment(payment));
        recyclerView.setAdapter(adapter);

        // Initialize Search Bar
        EditText searchBar = findViewById(R.id.searchBar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //filterPayments(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

//    private void approvePayment(Payment payment) {
//        String expirationDate = calculateExpirationDate();
//        dbHelper.updatePaymentStatus(payment.getId(), "Approved", expirationDate);
//        payment.setPaymentStatus("Approved");
//        payment.setExpirationDate(expirationDate);
//        adapter.notifyDataSetChanged();
//    }

//    private String calculateExpirationDate() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH, 1);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        return dateFormat.format(calendar.getTime());
//    }

//    private void filterPayments(String query) {
//        filteredPayments.clear();
//        if (query.isEmpty()) {
//            filteredPayments.addAll(allPayments);
//        } else {
//            for (Payment payment : allPayments) {
//                if (payment.getUserName().toLowerCase().contains(query.toLowerCase())) {
//                    filteredPayments.add(payment);
//                }
//            }
//        }
//        adapter.notifyDataSetChanged();
//    }
}

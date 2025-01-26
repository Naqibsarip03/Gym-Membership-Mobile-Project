package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {

    private final List<Payment> paymentList;
    private final OnApproveClickListener onApproveClickListener;

    public interface OnApproveClickListener {
        void onApproveClick(Payment payment);
    }

    public PaymentAdapter(List<Payment> paymentList, OnApproveClickListener onApproveClickListener) {
        this.paymentList = paymentList;
        this.onApproveClickListener = onApproveClickListener;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_item, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        Payment payment = paymentList.get(position);

        holder.userNameTextView.setText(payment.getUserName());
        holder.paymentAmountTextView.setText(String.format("Amount: $%.2f", payment.getPaymentAmount()));
        holder.paymentStatusTextView.setText(String.format("Status: %s", payment.getPaymentStatus()));

        if ("Approved".equals(payment.getPaymentStatus())) {
            // Hide the button and display the expiration date
            holder.approveButton.setVisibility(View.GONE);
            holder.expirationDateTextView.setVisibility(View.VISIBLE);
            holder.expirationDateTextView.setText(String.format("Expires: %s", payment.getExpirationDate()));
        } else {
            // Show the button for pending payments
            holder.approveButton.setVisibility(View.VISIBLE);
            holder.expirationDateTextView.setVisibility(View.GONE);

            holder.approveButton.setOnClickListener(v -> {
                onApproveClickListener.onApproveClick(payment);
            });
        }
    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    public static class PaymentViewHolder extends RecyclerView.ViewHolder {

        TextView userNameTextView, paymentAmountTextView, paymentStatusTextView, expirationDateTextView;
        Button approveButton;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            paymentAmountTextView = itemView.findViewById(R.id.paymentAmountTextView);
            paymentStatusTextView = itemView.findViewById(R.id.paymentStatusTextView);
            expirationDateTextView = itemView.findViewById(R.id.expirationDateTextView);
            approveButton = itemView.findViewById(R.id.approveButton);
        }
    }
}

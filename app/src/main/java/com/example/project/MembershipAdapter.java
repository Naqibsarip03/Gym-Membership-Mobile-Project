package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.sqlite.Membership;

import java.util.List;

public class MembershipAdapter extends RecyclerView.Adapter<MembershipAdapter.MembershipViewHolder>{
    private Context context;
    private List<Membership> memberships;

    public MembershipAdapter(List<Membership> memberships) {
        this.context = context;
        this.memberships = memberships;
    }

    @NonNull
    @Override
    public MembershipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_membership, parent, false);
        return new MembershipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MembershipViewHolder holder, int position) {
        Membership membership = memberships.get(position);

        // Set membership details
        holder.tvName.setText(membership.getName());
        holder.tvPrice.setText(membership.getPrice());
        holder.tvDuration.setText(membership.getDuration());

        // Set background color
        holder.itemView.setBackground(membership.getBackground());

        // Set button click listener
        holder.btnSelectMembership.setOnClickListener(v -> {
//            Intent intent = new Intent(context, SelectedMembershipActivity.class);
//            intent.putExtra("membershipId", membership.getId());
//            intent.putExtra("membershipName", membership.getName());
//            intent.putExtra("membershipPrice", membership.getPrice());
//            intent.putExtra("membershipDuration", membership.getDuration());
//            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return memberships.size();
    }

    static class MembershipViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvDuration;
        LinearLayout itemContainer;
        Button btnSelectMembership;

        public MembershipViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvMembershipName);
            tvPrice = itemView.findViewById(R.id.tvMembershipPrice);
            tvDuration = itemView.findViewById(R.id.tvMembershipDuration);
            itemContainer = itemView.findViewById(R.id.itemContainer);
            btnSelectMembership = itemView.findViewById(R.id.btnSelectMembership);
        }
    }
}
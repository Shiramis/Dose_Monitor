package com.example.dosemonitor.ui.tables;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosemonitor.R;

import java.util.List;
import java.util.Map;

public class MonthlySummaryAdapter extends RecyclerView.Adapter<MonthlySummaryAdapter.MonthlyViewHolder> {

    private final List<String> monthList; // e.g., ["2025-01", "2025-02"]
    private final Map<String, Double> doseMap; // e.g., {"2025-01": 2.1, ...}
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String month, double dose);
    }

    public MonthlySummaryAdapter(List<String> monthList, Map<String, Double> doseMap, OnItemClickListener listener) {
        this.monthList = monthList;
        this.doseMap = doseMap;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MonthlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_monthly_summary, parent, false);
        return new MonthlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyViewHolder holder, int position) {
        String month = monthList.get(position);
        double dose = doseMap.get(month);

        holder.textMonth.setText("Month: " + month);
        holder.textDose.setText("Total Dose: " + dose + " mSv");

        holder.itemView.setOnClickListener(v -> listener.onItemClick(month, dose));
    }

    @Override
    public int getItemCount() {
        return monthList.size();
    }

    static class MonthlyViewHolder extends RecyclerView.ViewHolder {
        TextView textMonth, textDose;

        MonthlyViewHolder(View itemView) {
            super(itemView);
            textMonth = itemView.findViewById(R.id.textMonth);
            textDose = itemView.findViewById(R.id.textDose);
        }
    }
}

package com.example.dosemonitor.ui.tables;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosemonitor.R;
import com.example.dosemonitor.data.DoseDatabase;
import com.example.dosemonitor.data.DoseEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyTldFragment extends Fragment {

    private RecyclerView recyclerView;

    public MonthlyTldFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewMonthly);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadMonthlyTldData();

        return view;
    }

    private void loadMonthlyTldData() {
        List<DoseEntry> tldEntries = DoseDatabase.getInstance(getContext())
                .doseDao().getDosesByType("TLD");

        Map<String, Double> monthlyTotals = new HashMap<>();
        for (DoseEntry entry : tldEntries) {
            String month = entry.month != null ? entry.month : entry.date.substring(0, 7); // "YYYY-MM"
            monthlyTotals.put(month, monthlyTotals.getOrDefault(month, 0.0) + entry.dose);
        }

        List<String> monthList = new ArrayList<>(monthlyTotals.keySet());
        Collections.sort(monthList); // optional: for chronological order

        MonthlySummaryAdapter adapter = new MonthlySummaryAdapter(
                monthList,
                monthlyTotals,
                new MonthlySummaryAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String month, double dose) {
                        Toast.makeText(getContext(), "Tapped " + month + ": " + dose + " mSv", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        recyclerView.setAdapter(adapter);
    }

}

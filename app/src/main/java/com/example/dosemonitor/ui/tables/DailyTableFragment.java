package com.example.dosemonitor.ui.tables;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosemonitor.R;
import com.example.dosemonitor.data.DoseDatabase;
import com.example.dosemonitor.data.DoseEntry;

import java.util.ArrayList;
import java.util.List;

public class DailyTableFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView textViewTotal;
    private DailyDoseAdapter adapter;
    private List<DoseEntry> doseEntries;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_table, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewDaily);
        textViewTotal = view.findViewById(R.id.textViewTotalDailyDose);

        doseEntries = new ArrayList<>(DoseDatabase.getInstance(getContext())
                .doseDao()
                .getDosesByType("Exam"));

        adapter = new DailyDoseAdapter(doseEntries, entry -> {
            // Delete from database
            DoseDatabase.getInstance(getContext()).doseDao().delete(entry);

            // Remove from adapter and update total
            adapter.removeEntry(entry);
            updateTotalDose();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        updateTotalDose();

        return view;
    }

    private void updateTotalDose() {
        double totalDose = 0.0;
        for (DoseEntry entry : adapter.getDoseList()) {
            totalDose += entry.dose;
        }
        textViewTotal.setText("Overall daily dose: " + totalDose + " mSv");
    }
}

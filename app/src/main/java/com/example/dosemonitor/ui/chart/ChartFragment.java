package com.example.dosemonitor.ui.chart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dosemonitor.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.*;

import java.util.ArrayList;

public class ChartFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chart, container, false);

        BarChart barChart = root.findViewById(R.id.barChart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 0.5f));  // January TLD dose
        entries.add(new BarEntry(1, 0.4f));  // February TLD dose
        entries.add(new BarEntry(2, 0.6f));  // March TLD dose

        BarDataSet dataSet = new BarDataSet(entries, "TLD Dose (mSv)");
        BarData barData = new BarData(dataSet);
        barChart.setData(barData);
        barChart.getDescription().setText("Monthly TLD Dose");
        barChart.animateY(1000);

        return root;
    }
}

package com.example.dosemonitor.ui.tables;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dosemonitor.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MonthlyTableFragment extends Fragment {

    public MonthlyTableFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly_table, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabLayoutMonthly);
        ViewPager2 viewPager = view.findViewById(R.id.viewPagerMonthly);

        MonthlyPagerAdapter adapter = new MonthlyPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Exams");
                    } else {
                        tab.setText("TLD");
                    }
                }
        ).attach();

        return view;
    }
}

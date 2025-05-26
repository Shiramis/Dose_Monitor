package com.example.dosemonitor.ui.tables;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new DailyTableFragment();
            case 1: return new MonthlyTableFragment();
            case 2: return new YearlyTableFragment();
            default: return new DailyTableFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Daily, Monthly, Yearly
    }
}

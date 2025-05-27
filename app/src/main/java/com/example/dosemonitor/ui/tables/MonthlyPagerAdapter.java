package com.example.dosemonitor.ui.tables;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MonthlyPagerAdapter extends FragmentStateAdapter {

    public MonthlyPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new MonthlyExamFragment();
        } else {
            return new MonthlyTldFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

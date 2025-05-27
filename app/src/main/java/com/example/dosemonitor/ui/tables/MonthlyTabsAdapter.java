package com.example.dosemonitor.ui.tables;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MonthlyTabsAdapter extends FragmentStateAdapter {

    public MonthlyTabsAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return position == 0 ? new MonthlyExamFragment() : new MonthlyTldFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

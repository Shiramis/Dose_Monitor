package com.example.dosemonitor.ui.adddose;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dosemonitor.R;

public class AddDoseFragment extends Fragment {

    private RadioGroup radioGroupType;
    private RadioButton radioExam, radioTLD;
    private LinearLayout examLayout, tldLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_adddose, container, false);

        // Link UI elements
        radioGroupType = view.findViewById(R.id.radioGroupType);
        radioExam = view.findViewById(R.id.radioExam);
        radioTLD = view.findViewById(R.id.radioTLD);
        examLayout = view.findViewById(R.id.examLayout);
        tldLayout = view.findViewById(R.id.tldLayout);
        Spinner spinnerTldMonth = view.findViewById(R.id.spinnerTldMonth);

        // List of months
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        // Create adapter and attach to spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(), android.R.layout.simple_spinner_item, months
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTldMonth.setAdapter(adapter);

        // Set default visibility
        examLayout.setVisibility(View.GONE);
        tldLayout.setVisibility(View.GONE);

        // Toggling logic
        radioGroupType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioExam) {
                examLayout.setVisibility(View.VISIBLE);
                tldLayout.setVisibility(View.GONE);
            } else if (checkedId == R.id.radioTLD) {
                tldLayout.setVisibility(View.VISIBLE);
                examLayout.setVisibility(View.GONE);
            }
        });

        return view;
    }
}

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dosemonitor.data.DoseDatabase;
import com.example.dosemonitor.data.DoseEntry;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dosemonitor.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddDoseFragment extends Fragment {

    private RadioGroup radioGroupType;
    private RadioButton radioExam, radioTLD;
    private LinearLayout examLayout, tldLayout;
    private Button buttonSaveDose;
    private EditText editTextExamDose, editTextTldDose, editTextExamType;
    private Spinner spinnerTldMonth;

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
        editTextExamType = view.findViewById(R.id.editTextExamType);
        editTextExamDose = view.findViewById(R.id.editTextExamDose);
        editTextTldDose = view.findViewById(R.id.editTextTldDose);
        spinnerTldMonth = view.findViewById(R.id.spinnerTldMonth);
        buttonSaveDose = view.findViewById(R.id.buttonSaveDose);

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
        buttonSaveDose.setOnClickListener(v -> {
            DoseEntry entry = new DoseEntry();
            entry.date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            if (radioExam.isChecked()) {
                entry.type = "Exam";
                String examType = editTextExamType.getText().toString();
                if (examType.isEmpty()) {
                    Toast.makeText(getContext(), "Enter an exam type", Toast.LENGTH_SHORT).show();
                    return;
                }
                entry.examType = examType;

                try {
                    entry.dose = Double.parseDouble(editTextExamDose.getText().toString());
                } catch (NumberFormatException | NullPointerException e) {
                    Toast.makeText(getContext(), "Enter a valid exam dose", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else if (radioTLD.isChecked()) {
                entry.type = "TLD";
                entry.month = spinnerTldMonth.getSelectedItem().toString();
                try {
                    entry.dose = Double.parseDouble(editTextTldDose.getText().toString());
                } catch (NumberFormatException | NullPointerException e) {
                    Toast.makeText(getContext(), "Enter a valid TLD dose", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else {
                Toast.makeText(getContext(), "Please select dose type", Toast.LENGTH_SHORT).show();
                return;
            }
            editTextExamType.setText("");
            editTextExamDose.setText("");
            editTextTldDose.setText("");
            radioGroupType.clearCheck();
            examLayout.setVisibility(View.GONE);
            tldLayout.setVisibility(View.GONE);

            new Thread(() -> {
                try {
                    DoseDatabase db = DoseDatabase.getInstance(getContext());
                    db.doseDao().insert(entry);

                    requireActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(), "Dose saved successfully", Toast.LENGTH_SHORT).show());
                } catch (Exception e) {
                    e.printStackTrace(); // Log the full stack trace
                    requireActivity().runOnUiThread(() ->
                            Toast.makeText(getContext(), "Error saving dose: " + e.getMessage(), Toast.LENGTH_LONG).show());
                }
            }).start();

        });

        return view;
    }
}

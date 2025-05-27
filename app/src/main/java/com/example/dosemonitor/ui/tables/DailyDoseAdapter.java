package com.example.dosemonitor.ui.tables;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosemonitor.R;
import com.example.dosemonitor.data.DoseEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DailyDoseAdapter extends RecyclerView.Adapter<DailyDoseAdapter.ViewHolder> {

    private List<DoseEntry> doseList;

    public List<DoseEntry> getDoseList() {
        return doseList;
    }
    private final OnDeleteClickListener deleteClickListener;

    public interface OnDeleteClickListener {
        void onDelete(DoseEntry entry);
    }

    public DailyDoseAdapter(List<DoseEntry> doseList, OnDeleteClickListener listener) {
        this.doseList = doseList;
        this.deleteClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewLine;
        Button buttonDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLine = itemView.findViewById(R.id.textViewLine);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_daily_dose, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoseEntry entry = doseList.get(position);
        String formattedDate = formatDate(entry.date);
        String line = (position + 1) + " - " + formattedDate + " - " + entry.examType + " - " + entry.dose + " mSv";
        holder.textViewLine.setText(line);

        holder.buttonDelete.setOnClickListener(v -> {
            deleteClickListener.onDelete(entry);
        });
    }

    @Override
    public int getItemCount() {
        return doseList.size();
    }

    private String formatDate(String isoDate) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(isoDate);
            return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date);
        } catch (ParseException e) {
            return isoDate;
        }
    }

    public void removeEntry(DoseEntry entry) {
        int position = doseList.indexOf(entry);
        if (position != -1) {
            doseList.remove(position);
            notifyItemRemoved(position);
        }
    }
}

package com.example.dosemonitor.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dose_entries")
public class DoseEntry {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String type; // "Exam" or "TLD"
    public String date; // ISO format e.g. "2025-05-26"
    public Double dose; // in mSv or whatever unit
    public String examType; // only for Exam
    public String month; // only for TLD
}

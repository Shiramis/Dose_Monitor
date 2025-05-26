package com.example.dosemonitor.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DoseDao {

    @Insert
    void insert(DoseEntry dose);

    @Query("SELECT * FROM dose_entries ORDER BY date DESC")
    List<DoseEntry> getAllDoses();

    @Query("SELECT * FROM dose_entries WHERE type = :type")
    List<DoseEntry> getDosesByType(String type);

    @Query("SELECT SUM(dose) FROM dose_entries WHERE strftime('%Y', date) = :year")
    double getTotalDoseForYear(String year);
}

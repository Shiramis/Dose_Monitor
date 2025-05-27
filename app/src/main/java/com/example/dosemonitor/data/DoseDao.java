package com.example.dosemonitor.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DoseDao {

    @Insert
    void insert(DoseEntry dose);

    @Delete
    void delete(DoseEntry entry);

    @Query("SELECT * FROM dose_entries ORDER BY date DESC")
    List<DoseEntry> getAllDoses();

    @Query("SELECT * FROM dose_entries WHERE type = :type")
    List<DoseEntry> getDosesByType(String type);

    @Query("SELECT strftime('%m', date) AS month, SUM(dose) AS totalDose FROM dose_entries WHERE type = 'Exam' GROUP BY month")
    List<MonthlyDose> getMonthlyExamDose();

    @Query("SELECT month, SUM(dose) AS totalDose FROM dose_entries WHERE type = 'TLD' GROUP BY month")
    List<MonthlyDose> getMonthlyTldDose();

    @Query("SELECT SUM(dose) FROM dose_entries WHERE strftime('%Y', date) = :year")
    double getTotalDoseForYear(String year);
}

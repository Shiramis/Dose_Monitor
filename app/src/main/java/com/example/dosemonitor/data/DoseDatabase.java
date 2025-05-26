package com.example.dosemonitor.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DoseEntry.class}, version = 1)
public abstract class DoseDatabase extends RoomDatabase {
    private static DoseDatabase instance;

    public abstract DoseDao doseDao();

    public static synchronized DoseDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DoseDatabase.class, "dose_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() // for simplicity; move to background threads in production
                    .build();
        }
        return instance;
    }
}

package com.example.project.BMI_DATABASE;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.project.BMIRecord;

@Database(entities = {BMIRecord.class}, version = 1, exportSchema = false)
public abstract class BMIDatabase extends RoomDatabase  {
    public abstract BMIDao bmiDao();
}

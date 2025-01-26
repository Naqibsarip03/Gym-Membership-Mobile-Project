package com.example.project.BMI_DATABASE;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.project.BMIRecord;

import java.util.List;

@Dao
public interface BMIDao {
    @Insert
    void insert(BMIRecord record);

    @Query("SELECT * FROM BMIRecord ORDER BY date DESC")
    List<BMIRecord> getAllRecords();

    @Delete
    void delete(BMIRecord record);
}

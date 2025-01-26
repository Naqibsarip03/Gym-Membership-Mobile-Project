package com.example.project;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BMIRecord") // Annotate as a Room entity
public class BMIRecord {

    @PrimaryKey(autoGenerate = true) // Auto-generate a primary key
    private int id;

    private final String weight;
    private final String height;
    private final String bmi;
    private final String date;

    // Constructor
    public BMIRecord(String weight, String height, String bmi, String date) {
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.date = date;
    }

    // Getters
    public String getWeight() { return weight; }
    public String getHeight() { return height; }
    public String getBmi() { return bmi; }
    public String getDate() { return date; }

    // Getter for the primary key
    public int getId() { return id; }

    // Setter for primary key (Room needs this setter to auto-generate ID)
    public void setId(int id) { this.id = id; }
}

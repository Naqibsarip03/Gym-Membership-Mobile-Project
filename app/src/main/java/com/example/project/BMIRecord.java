package com.example.project;

public class BMIRecord {
    private final String weight;
    private final String height;
    private final String bmi;
    private final String date;

    public BMIRecord(String weight, String height, String bmi, String date) {
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.date = date;
    }

    public String getWeight() { return weight; }
    public String getHeight() { return height; }
    public String getBmi() { return bmi; }
    public String getDate() { return date; }
}

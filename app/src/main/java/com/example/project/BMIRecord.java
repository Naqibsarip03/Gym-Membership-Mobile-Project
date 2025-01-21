package com.example.project;

public class BMIRecord {
    private String weight;
    private String height;
    private String bmi;
    private String date;

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

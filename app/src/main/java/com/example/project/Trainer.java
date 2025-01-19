package com.example.project;

public class Trainer {
    private String name;
    private int age;
    private int experience;
    private int imageResId;

    public Trainer(String name, int age, int experience, int imageResId) {
        this.name = name;
        this.age = age;
        this.experience = experience;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getExperience() {
        return experience;
    }

    public int getImageResId() {
        return imageResId;
    }
}

package com.example.project.sqlite;

import android.graphics.drawable.Drawable;

public class Membership {
    private int membershipId;
    private String name;
    private String price;
    private String duration;
    private Drawable background;

    public Membership(int membershipId, String name, String price, String duration, Drawable background) {
        this.membershipId = membershipId;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.background = background;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }
    public Drawable getBackground() {
        return background;
    }
}

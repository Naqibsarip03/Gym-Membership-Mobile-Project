package com.example.project;

public class Payment {
    private int id;
    private String userName;
    private double paymentAmount;
    private String paymentStatus;
    private String expirationDate;

    public Payment(int id, String userName, double paymentAmount, String paymentStatus, String expirationDate) {
        this.id = id;
        this.userName = userName;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
        this.expirationDate = expirationDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    // Setters
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}

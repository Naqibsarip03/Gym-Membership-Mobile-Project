package com.example.project.sqlite;

public class UserModel {
    public int userId;
    public String username;
    public String password;
    public String email;
    public String phoneNo;
    public String membershipId;
    public String status;

    public UserModel(int userId, String username, String password, String email, String phoneNo, String membershipId, String status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.membershipId = membershipId;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public String getStatus() {
        return status;
    }
}

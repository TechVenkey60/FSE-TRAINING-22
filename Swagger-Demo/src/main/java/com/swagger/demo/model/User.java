package com.swagger.demo.model;

public class User {

    private Integer userId;
    private String userName;
    private String phone;

    public User(Integer userId, String userName, String phone) {
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

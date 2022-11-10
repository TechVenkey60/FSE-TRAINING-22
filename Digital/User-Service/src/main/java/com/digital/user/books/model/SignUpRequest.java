package com.digital.user.books.model;

public class SignUpRequest {

    private String userName;
    private String email;
    private String role;
    private String password;

    public SignUpRequest() {
    }

    public SignUpRequest(String userName, String email, String role, String password) {
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}

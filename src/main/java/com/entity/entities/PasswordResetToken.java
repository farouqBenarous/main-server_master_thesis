package com.entity.entities;

import java.io.Serializable;

public class PasswordResetToken implements Serializable {


    private static final long serialVersionUID = 6904455093456256014L;

    String token;

    User User;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }
}
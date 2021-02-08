package com.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



public class User implements Serializable {

    private static final long serialVersionUID = -3187980103353374187L;

    @JsonProperty("userId")
    String userId;

    @JsonProperty("name")
    String name;

    @JsonProperty(value ="password", access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @JsonProperty("email")
    String email;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

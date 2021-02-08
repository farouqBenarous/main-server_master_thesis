package com.logic;


import com.entity.entities.User;

public interface UserService {
    User getUser(String email);

    User createUser(User user);

    User createUser(User newUser, String adminUsername);

}

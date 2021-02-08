package com.logic.impl;


import com.database.delegator.UserDelegator;
import com.entity.entities.PasswordResetToken;
import com.entity.entities.User;

import com.entity.exceptions.BadRequestException;
import com.logic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDelegator userDelegator;


    @Override
    public User getUser(String email) {
        User user = userDelegator.getUser(email);
        return user;
    }

    public User createUser(User usr) {
        return userDelegator.createUser(usr);
    }
    @Override
    public User createUser(User newUser, String adminUsername) {
        User adminUser = userDelegator.getUser(adminUsername);
        return userDelegator.createUser(newUser);
    }


}

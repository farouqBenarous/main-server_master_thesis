package com.database.delegator;


import com.database.converter.PasswordResetTokenConverter;
import com.database.converter.UserConverter;
import com.database.repository.PasswordResetTokenRepository;
import com.database.repository.UserRepository;
import com.database.table.PasswordResetTokenTbl;
import com.database.table.UserTbl;
import com.entity.entities.PasswordResetToken;
import com.entity.entities.User;
import com.entity.entities.UserSS;
import com.entity.exceptions.BadRequestException;
import com.entity.exceptions.NotValidException;
import com.entity.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
public class UserDelegator implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    PasswordEncoder pe;


    @Transactional(readOnly = true)
    public User getUser(String email) {
        Optional<UserTbl> userTblOptional = userRepository.findByUsr(email);
        return userTblOptional.map(UserConverter.convertToEntity::apply).orElse(null);
    }

    @Transactional
    public User createUser(User user) {

        if (!user.getEmail().equals(user.getEmail())) {
            throw new NotValidException(String.format("Email should be same as username.\n email: %s \t username: %s",
                user.getEmail(), user.getEmail()));
        }

        UserTbl newUser = UserConverter.convertToDBO.apply(user);

        newUser.setPassword(pe.encode(user.getPassword()));

        return saveUser(newUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserTbl> userTbl = userRepository.findByUsr(email);
        if (!userTbl.isPresent()) {
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(UserConverter.convertToEntity.apply(userTbl.get()));
    }

    public Boolean isCurrentPassword(String plainTextPassword, String oldEncodedPassword) {
        return pe.matches(plainTextPassword, oldEncodedPassword);
    }

    @Transactional
    public User updatePassword(String oldPassword, String newPassword, String verifiedPassword, String username) {
        Optional<UserTbl> userTbl = userRepository.findByUsr(username);
        if (!userTbl.isPresent()) {
            throw new ObjectNotFoundException(
                String.format("User not found: %s",
                    username));
        }

        if (!isCurrentPassword(oldPassword, userTbl.get().getPassword())) {
            throw new BadRequestException(
                String.format("Old password: %s is not correct",
                    oldPassword));
        }

        UserTbl userToSave = userTbl.get();
        String encodedPassword = pe.encode(newPassword);
        userToSave.setPassword(encodedPassword);
        return saveUser(userToSave);
    }

    @Transactional
    public PasswordResetToken forgotPassword(String userEmail) {
        Optional<UserTbl> userTbl = userRepository.findByUsr(userEmail);
        if (!userTbl.isPresent()) {
            throw new BadRequestException("Bad request");
        }

        String token = UUID.randomUUID().toString();
        PasswordResetTokenTbl passwordResetTokenTbl = new PasswordResetTokenTbl();
        passwordResetTokenTbl.setToken(token);
        passwordResetTokenTbl.setUser(userTbl.get());
        passwordResetTokenRepository.save(passwordResetTokenTbl);
        return PasswordResetTokenConverter.convertToEntity.apply(passwordResetTokenTbl);

    }

    @Transactional
    public User resetPassword(String userEmail, String newPassword, String repeatPassword, String token) {
        Optional<UserTbl> userTbl = userRepository.findByUsr(userEmail);
        if (!userTbl.isPresent()) {
            throw new BadRequestException("Bad request");
        }

        Optional<PasswordResetTokenTbl> optional = passwordResetTokenRepository.findByTokenWithUser(token);

        if (!optional.isPresent()) {
            throw new ObjectNotFoundException(
                String.format("Token not found: %s",
                    token));
        }

        PasswordResetTokenTbl passwordResetTokenTbl = optional.get();

        UserTbl userFromToken = passwordResetTokenTbl.getUser();
        if (!userFromToken.getEmail().equals(userEmail)) {
            throw new BadRequestException(
                String.format("Invalid token: %s",
                    token));
        }

        Long timeNow = Calendar.getInstance().getTime()
            .getTime();
        long limitTime = passwordResetTokenTbl.getCreatedAt()
            .getTime() + (PasswordResetTokenTbl.EXPIRATION * 60 * 1000);
        if ((limitTime - timeNow) <= 0) {
            throw new BadRequestException(
                String.format("Token expired: %s",
                    token));
        }

        String encodedPassword = pe.encode(newPassword);
        userFromToken.setPassword(encodedPassword);
        return saveUser(userFromToken);
    }

    public User saveUser(UserTbl user) {
        user.setEmail(user.getEmail().toLowerCase());
        user.setName(user.getName().toLowerCase());
        UserTbl userTbl = userRepository.save(user);
        return UserConverter.convertToEntity.apply(userTbl);
    }

}

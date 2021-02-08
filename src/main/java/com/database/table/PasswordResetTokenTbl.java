package com.database.table;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "password_reset_token")
public class PasswordResetTokenTbl extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 7345278644045156619L;

    //in minutes
    public static final int EXPIRATION = 10;

    @Column(name = "token", nullable = false, length = 255)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    UserTbl user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserTbl getUser() {
        return user;
    }

    public void setUser(UserTbl user) {
        this.user = user;
    }
}

package com.database.table;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "users")
public class UserTbl extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8906230891732131915L;

    @Column(name = "name",  nullable = false, length = 32)
    String name;

    @Column(name = "email",  nullable = false, length = 32)
    String email;

    @Column(name = "password",  nullable = false, length = 128)
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
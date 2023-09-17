package com.example.client.Logic.Accounts;

import java.io.Serializable;

public class User implements Serializable {
    String key;
    String username;
    String email;

    public User(LoginResult loginResult){
        this.key = loginResult.getKey();
        this.username = null;
        this.email = null;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

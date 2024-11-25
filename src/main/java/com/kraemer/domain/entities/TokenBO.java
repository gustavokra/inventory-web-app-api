package com.kraemer.domain.entities;

public class TokenBO {

    private String token;

    private boolean admin;


    public TokenBO(String token, boolean admin) {
        this.token = token;
        this.admin = admin;
    }

    public String getToken() {
        return this.token;
    }

    public boolean isAdmin() {
        return admin;
    }
}

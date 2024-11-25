package com.kraemer.domain.entities.dto;

public class TokenDTO {
    
    private String token;

    private boolean admin;

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    
}

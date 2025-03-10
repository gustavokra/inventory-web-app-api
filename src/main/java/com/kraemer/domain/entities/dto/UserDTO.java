package com.kraemer.domain.entities.dto;

import java.util.Set;

import com.kraemer.domain.entities.enums.EnumRole;

public class UserDTO {

    private Long id;

    private String name;

    private String email;
    
    private String password;

    private String confirmPassword;

    private Long idLoja;

    private Set<EnumRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(Long idLoja) {
        this.idLoja = idLoja;
    }

    public Set<EnumRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<EnumRole> roles) {
        this.roles = roles;
    }
    
}

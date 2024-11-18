package com.kraemer.domain.entities;

import java.util.Set;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.enums.EnumRole;
import com.kraemer.domain.utils.EmailUtil;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.utils.PasswordUtils;
import com.kraemer.domain.utils.StringUtil;
import com.kraemer.domain.utils.exception.InvetoryAppException;

public class UserBO {

    String name;

    String email;

    String password;

    String confirmPassword;

    Set<EnumRole> roles;

    public UserBO(String name, String email, String password, String confirmPassword, Set<EnumRole> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.roles = roles;
        validate();
    }

    public UserBO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void validateEmail() {
        if (!EmailUtil.isValidEmail(email)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_INVALIDO, "E-mail");
        }
    }

    public void validatePassword() {
        if (StringUtil.isNullOrEmpty(confirmPassword)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Confirmar senha");
        }

        if (!confirmPassword.equals(password)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_INVALIDO, "Confirmar senha");
        }
    }

    public void validateRoles() {
        if (ListUtil.isNotNullOrEmpty(roles)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Cargo");
        }
    }

    public void encriptPassword() {
        this.password = PasswordUtils.encryptPassword(this.password);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public Set<EnumRole> getRoles() {
        return roles;
    }

    private void validate() {

        if (StringUtil.isNullOrEmpty(name)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Nome");
        }

        if (StringUtil.isNullOrEmpty(email)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "E-mail");
        }

        if (StringUtil.isNullOrEmpty(password)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Senha");
        }

        if (ListUtil.isNullOrEmpty(roles)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Cargo");
        }
    }

}

package com.kraemer.domain.entities;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.utils.EmailUtil;
import com.kraemer.domain.utils.PasswordUtils;
import com.kraemer.domain.utils.StringUtil;
import com.kraemer.domain.utils.exception.InvetoryAppException;

public class UserBO {

    String name;

    String email;
    
    String password;

    String confirmPassword;

    public UserBO(String name, String email, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;

        validate();
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

    private void validate() {
    
        if(StringUtil.isNullOrEmpty(name)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Nome");
        }
    
        if(StringUtil.isNullOrEmpty(email)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "E-mail");
        }
    
        if(StringUtil.isNullOrEmpty(password)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Senha");
        }
    }

    public void validateEmail() {
        if(!EmailUtil.isValidEmail(email)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_INVALIDO, "E-mail");
        }
    }

    public void validatePassword() {
        if(StringUtil.isNullOrEmpty(confirmPassword)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Confirmar senha");
        }

        if(!confirmPassword.equals(password)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_INVALIDO, "Confirmar senha");
        }
    }

    public void encriptPassword() {
        this.password = PasswordUtils.encryptPassword(this.password);
    }

}
 
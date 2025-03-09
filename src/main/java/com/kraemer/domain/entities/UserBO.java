package com.kraemer.domain.entities;

import java.util.Set;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.enums.EnumRole;
import com.kraemer.domain.utils.EmailUtil;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.utils.PasswordUtils;
import com.kraemer.domain.utils.StringUtil;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class UserBO {

    Long id;

    String name;

    String email;

    String password;

    String confirmPassword;

    Set<EnumRole> roles;
    
    private Long idLoja;

    public UserBO(Long id, String name, String email, String password, String confirmPassword,  Long idLoja, Set<EnumRole> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.idLoja = idLoja;
        this.roles = roles;
        validate();
    }

    public UserBO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void validateEmail() {
        if (!EmailUtil.isValidEmail(email)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_INVALIDO, "E-mail");
        }
    }

    public void validatePassword() {
        if (StringUtil.isNullOrEmpty(confirmPassword)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Confirmar senha");
        }

        if (!confirmPassword.equals(password)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_INVALIDO, "Confirmar senha");
        }
    }

    public void validateRoles() {
        if (ListUtil.isNotNullOrEmpty(roles)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Cargo");
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

    public Long getIdLoja() {
        return idLoja;
    }

    public Long getId() {
        return id;
    }

    private void validate() {

        if (StringUtil.isNullOrEmpty(name)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Nome");
        }

        if (StringUtil.isNullOrEmpty(email)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "E-mail");
        }

        if (StringUtil.isNullOrEmpty(password)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Senha");
        }

        if (ListUtil.isNullOrEmpty(roles)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Cargo");
        }
    }

}

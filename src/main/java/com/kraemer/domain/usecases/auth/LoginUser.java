package com.kraemer.domain.usecases.auth;

import java.util.List;

import com.kraemer.domain.entities.UserBO;
import com.kraemer.domain.entities.dto.UserCredentialsDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.repositories.IUserRepository;
import com.kraemer.domain.utils.PasswordUtils;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class LoginUser {

    private IUserRepository repository;

    public LoginUser(IUserRepository repository) {
        this.repository = repository;
    }

    public UserBO execute(UserCredentialsDTO credentials) {
        QueryFieldInfoVO fieldName = new QueryFieldInfoVO("name", credentials.getEmail());
        QueryFieldInfoVO fieldPassword = new QueryFieldInfoVO("password",
                PasswordUtils.encryptPassword(credentials.getPassword()));
        var user = repository.findFirstBy(List.of(fieldName, fieldPassword));

        if (user == null) {
            user = findByEmail(credentials, fieldPassword);
        }

        return user;
    }

    private UserBO findByEmail(UserCredentialsDTO credentials, QueryFieldInfoVO fieldPassword) {
        QueryFieldInfoVO fieldEmail = new QueryFieldInfoVO("email", credentials.getEmail());
        var user = repository.findFirstBy(List.of(fieldEmail, fieldPassword));

        if (user == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_INVALIDO, "Usuário ou senha inválidos");
        }

        return user;
    }

}

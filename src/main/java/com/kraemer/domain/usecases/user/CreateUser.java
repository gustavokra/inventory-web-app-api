package com.kraemer.domain.usecases.user;

import java.util.List;

import com.kraemer.domain.entities.dto.UserDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.UserMapper;
import com.kraemer.domain.entities.repositories.IUserRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class CreateUser {
    
    private IUserRepository repository;

    public CreateUser(IUserRepository repository) {
        this.repository = repository;
    }

    public UserDTO execute(UserDTO dto) {

        var userBO = UserMapper.toBO(dto);

        userBO.validateEmail();

        verifyExistingUser(userBO.getName(), userBO.getEmail());

        userBO.validatePassword();

        userBO.encriptPassword();

        userBO = repository.create(userBO);

        return UserMapper.toDTO(userBO);
    }

    public void verifyExistingUser(String name, String email) {
        verifyExistingUserByName(name);
        verifyExistingUserByEmail(email);
    }

    public void verifyExistingUserByName(String name) {
        QueryFieldInfoVO nameField = new QueryFieldInfoVO("name", name);
        var existentUser = repository.findFirstBy(List.of(nameField));
        if(existentUser != null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_CADASTRADA, "Usuário com nome " + name );
        }
    }

    public void verifyExistingUserByEmail(String email) {
        QueryFieldInfoVO emailField = new QueryFieldInfoVO("email", email);
        var existentUser = repository.findFirstBy(List.of(emailField));
        if(existentUser != null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_CADASTRADA, "Usuário com email " + email);
        }
    }

}

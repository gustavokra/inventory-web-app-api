package com.kraemer.service;

import com.kraemer.domain.entities.dto.UserDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.usecases.user.CreateUser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService extends AbstractService {

    @Transactional

    public UserDTO create(UserDTO dto, EnumDBImpl dbImpl) {
        var repository = dbFactory.getImpl(dbImpl);
        var createUser = new CreateUser(repository);
        return createUser.execute(dto);
    }

}

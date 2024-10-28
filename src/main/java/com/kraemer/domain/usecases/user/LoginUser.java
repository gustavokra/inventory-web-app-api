package com.kraemer.domain.usecases.user;

import java.util.List;

import com.kraemer.domain.entities.dto.UserDTO;
import com.kraemer.domain.entities.repositories.IUserRepository;
import com.kraemer.domain.utils.PasswordUtils;
import com.kraemer.domain.utils.TokenUtil;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class LoginUser {
    
    private IUserRepository repository;

    public LoginUser(IUserRepository repository) {
        this.repository = repository;
    }

    public String execute(UserDTO dto) {
        QueryFieldInfoVO fieldName = new QueryFieldInfoVO("name", dto.getName());
        QueryFieldInfoVO fieldPassword = new QueryFieldInfoVO("password", PasswordUtils.encryptPassword(dto.getPassword()));
        var user = repository.findFirstBy(List.of(fieldName, fieldPassword));
        return  user != null ? TokenUtil.generateToken() : null;
    }

}

package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.UserBO;
import com.kraemer.domain.entities.dto.UserCredentialsDTO;
import com.kraemer.domain.entities.dto.UserDTO;

public class UserMapper {

    public static UserBO toBO(UserCredentialsDTO dto) {
        return new UserBO(dto.getEmail(), dto.getPassword());
    }

    public static UserBO toBO(UserDTO dto) {
        return new UserBO(dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getConfirmPassword(),
                dto.getRoles()
                );
    }

    public static UserDTO toDTO(UserBO bo) {
        UserDTO dto = new UserDTO();
        dto.setId(bo.getId());
        dto.setName(bo.getName());
        dto.setEmail(bo.getEmail());
        dto.setPassword(bo.getPassword());
        dto.setConfirmPassword(bo.getConfirmPassword());
        return dto;
    }

}

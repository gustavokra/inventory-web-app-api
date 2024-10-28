package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.UserBO;
import com.kraemer.domain.entities.dto.UserDTO;

public class UserMapper {

    public static UserBO toBO(UserDTO dto) {
        return new UserBO(dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getConfirmPassword());
    }

    public static UserDTO toDTO(UserBO bo) {
        UserDTO dto = new UserDTO();
        dto.setName(bo.getName());
        dto.setEmail(bo.getEmail());
        dto.setPassword(bo.getPassword());
        dto.setConfirmPassword(bo.getConfirmPassword());
        return dto;
    }

}

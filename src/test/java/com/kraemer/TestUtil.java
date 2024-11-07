package com.kraemer;

import com.kraemer.domain.entities.dto.UserDTO;

public class TestUtil {

    public static UserDTO createUserDTO() {
        var userDTO = new UserDTO();
        userDTO.setName("john");
        userDTO.setEmail("jhondoe@gmail.com");
        userDTO.setPassword("123");
        userDTO.setConfirmPassword("123");
        return userDTO;
    }
    
}

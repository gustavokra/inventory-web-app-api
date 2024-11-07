package com.kraemer.usecases.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kraemer.TestUtil;
import com.kraemer.domain.entities.dto.UserDTO;
import com.kraemer.domain.entities.mappers.UserMapper;
import com.kraemer.domain.entities.repositories.IUserRepository;
import com.kraemer.domain.usecases.user.CreateUser;
import com.kraemer.domain.utils.exception.InvetoryAppException;

@ExtendWith(MockitoExtension.class)
public class CreateUserTest {

    @Mock
    private IUserRepository repository;

    @InjectMocks
    private CreateUser createUser;

    @Test
    void testExecuteIfUserNotExists() {

        var userDTO = TestUtil.createUserDTO();
        var userBO = UserMapper.toBO(userDTO);

        when(repository.findFirstBy(anyList())).thenReturn(null);
        when(repository.create(any())).thenReturn(userBO);

        UserDTO result = createUser.execute(userDTO);

        assertEquals(userDTO.getName(), result.getName());
        assertEquals(userDTO.getEmail(), result.getEmail());
        verify(repository, times(2)).findFirstBy(anyList());
        verify(repository).create(any());
    }

    @Test
    void testExecuteIfUserExists() {

        var userDTO = TestUtil.createUserDTO();
        var userBO = UserMapper.toBO(userDTO);
        when(repository.findFirstBy(anyList())).thenReturn(userBO);

        Assertions.assertThrows(
                InvetoryAppException.class,
                () -> createUser.execute(userDTO));
                
    }
}

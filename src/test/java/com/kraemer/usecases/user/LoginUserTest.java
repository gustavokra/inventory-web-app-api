package com.kraemer.usecases.user;



import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kraemer.TestUtil;
import com.kraemer.domain.entities.mappers.UserMapper;
import com.kraemer.domain.entities.repositories.IUserRepository;
import com.kraemer.domain.usecases.user.LoginUser;

@ExtendWith(MockitoExtension.class)
public class LoginUserTest {

    @Mock
    private IUserRepository repository;

    @InjectMocks
    private LoginUser loginUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSucessLoginUser() {
        var userDTO = TestUtil.createUserDTO();
        var userBO = UserMapper.toBO(userDTO);

        when(repository.findFirstBy(anyList())).thenReturn(userBO);

        var token = loginUser.execute(userDTO);

        assertNotNull(token);
    }

    @Test
    void testFailLoginUser() {
        var userDTO = TestUtil.createUserDTO();

        when(repository.findFirstBy(anyList())).thenReturn(null);

        var token = loginUser.execute(userDTO);

        assertNull(token);
    }

}

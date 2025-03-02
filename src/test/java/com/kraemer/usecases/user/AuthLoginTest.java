package com.kraemer.usecases.user;



import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kraemer.TestUtil;
import com.kraemer.domain.entities.mappers.UserMapper;
import com.kraemer.domain.entities.repositories.IUserRepository;
import com.kraemer.domain.usecases.auth.LoginUser;

@ExtendWith(MockitoExtension.class)
public class AuthLoginTest {

    @Mock
    private IUserRepository repository;

    @InjectMocks
    private LoginUser loginUser;

    @Test
    void testSucessLoginUser() {
        var userCredentials = TestUtil.createUserCredentialsDTO();
        var userBO = UserMapper.toBO(userCredentials);

        when(repository.findFirstBy(anyList())).thenReturn(userBO);

        var token = loginUser.execute(userCredentials);

        assertNotNull(token);
    }

    @Test
    void testFailLoginUser() {
        var userCredentials = TestUtil.createUserCredentialsDTO();

        when(repository.findFirstBy(anyList())).thenReturn(null);

        assertThrows(Exception.class, () -> loginUser.execute(userCredentials));
    }

}

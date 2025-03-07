package com.kraemer.service;

import com.kraemer.domain.entities.UserBO;
import com.kraemer.domain.entities.dto.TokenDTO;
import com.kraemer.domain.entities.dto.UserCredentialsDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.enums.EnumRole;
import com.kraemer.domain.usecases.auth.LoginUser;
import com.kraemer.infra.auth.jwt.JwtService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AuthService extends AbstractService {

    @Inject
    private JwtService jwtService;

    @Transactional
    public TokenDTO login(UserCredentialsDTO credentials, EnumDBImpl dbImpl) {
        var repository = dbFactory.getUserRepositoryImpl(dbImpl);
        var loginUser = new LoginUser(repository);
        var userLoged = loginUser.execute(credentials);

        return  generateToken(userLoged);
    }

    private TokenDTO generateToken(UserBO userLoged) {
        var tokenDTO = new TokenDTO();

        if (!userLoged.getRoles().contains(EnumRole.ADMIN)) {
            tokenDTO.setToken(jwtService.generateUserJwt());
            tokenDTO.setAdmin(false);
            tokenDTO.setUserId(userLoged.getId());
            return tokenDTO;
        }

        tokenDTO.setToken(jwtService.generateAdminJwt());
        tokenDTO.setAdmin(true);
        tokenDTO.setUserId(userLoged.getId());

        return tokenDTO;
    }

}

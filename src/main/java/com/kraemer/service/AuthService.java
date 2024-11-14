package com.kraemer.service;

import com.kraemer.domain.entities.dto.TokenDTO;
import com.kraemer.domain.entities.dto.UserCredentialsDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.usecases.auth.LoginUser;
import com.kraemer.infra.jwtService.InventoryAppJwtService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthService extends AbstractService {
    
    @Inject 
    private InventoryAppJwtService jwtService;

    public TokenDTO login(UserCredentialsDTO credentials, EnumDBImpl dbImpl) {
        var repository = dbFactory.getImpl(dbImpl);
        var loginUser = new LoginUser(repository);
        var userLoged = loginUser.execute(credentials);
        return userLoged != null ? new TokenDTO(jwtService.generateJwt()) : null;
    }

}

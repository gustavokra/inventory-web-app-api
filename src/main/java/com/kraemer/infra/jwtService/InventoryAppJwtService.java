package com.kraemer.infra.jwtService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

@Singleton
public class InventoryAppJwtService {
    public String generateAdminJwt() {
        Set<String> roles = new HashSet<>(
                Arrays.asList("ADMIN", "USER")
        );

        return Jwt.issuer("IventoryAppApi")
                .subject("IventoryAppApi")
                .groups(roles)
                .expiresAt(
                        System.currentTimeMillis() + 3600
                )
                .sign();
    }

    public String generateUserJwt() {
        Set<String> roles = new HashSet<>(
                Arrays.asList("USER")
        );

        return Jwt.issuer("IventoryAppApi")
                .subject("IventoryAppApi")
                .groups(roles)
                .expiresAt(
                        System.currentTimeMillis() + 3600
                )
                .sign();

    }
}

package com.kraemer.infra.jwtService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

@Singleton
public class InventoryAppJwtService {
    public String generateJwt() {
        Set<String> roles = new HashSet<>(
                Arrays.asList("admin", "user")
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

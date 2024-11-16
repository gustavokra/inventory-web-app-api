package com.kraemer.service;

import com.kraemer.infra.database.sqlite.model.SqliteRole;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RoleInitializer {

    @PostConstruct
    @Transactional
    public void init() {
        if (SqliteRole.count() == 0) {
            createFixedRoles();
        }
    }

    private void createFixedRoles() {
        SqliteRole adminRole = new SqliteRole();
        adminRole.setDescription("ADMIN");
        adminRole.persist();

        SqliteRole userRole = new SqliteRole();
        userRole.setDescription("USER");
        userRole.persist();
    }

}

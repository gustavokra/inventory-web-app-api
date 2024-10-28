package com.kraemer.service;

import com.kraemer.infra.database.sqlite.repositories.SqliteRepository;

import jakarta.inject.Inject;

public abstract class AbstractService {
    
    @Inject
    SqliteRepository sqliteRepository;

    @Inject
    DbFactory dbFactory;

}

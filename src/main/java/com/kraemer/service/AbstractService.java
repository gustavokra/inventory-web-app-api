package com.kraemer.service;

import jakarta.inject.Inject;

public abstract class AbstractService {
    
    // @Inject
    // SqliteUserRepository sqliteUserRepository;
    
    // @Inject
    // SqliteClientRepository sqliteClientRepository;

    // @Inject
    // SqliteSupplierRepository sqliteSupplierRepository;

    // @Inject
    // SqliteProductRepository sqliteProductRepository;

    @Inject
    DbFactory dbFactory;

}

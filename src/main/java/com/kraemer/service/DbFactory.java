package com.kraemer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.repositories.IClientRepository;
import com.kraemer.domain.entities.repositories.IProductRepository;
import com.kraemer.domain.entities.repositories.ISupplierRepository;
import com.kraemer.domain.entities.repositories.IUserRepository;
import com.kraemer.domain.utils.exception.InvetoryAppException;
import com.kraemer.infra.database.sqlite.repositories.SqliteProductRepository;

import io.quarkus.arc.All;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DbFactory {

    @All
    private List<IUserRepository> userImplementations;

    @All
    private List<IClientRepository> clientImplementations;

    @All
    private List<ISupplierRepository> supplierImplementations;

    @All
    private List<SqliteProductRepository> productImplementations;

    private static final Map<EnumDBImpl, IUserRepository> userServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, IClientRepository> clientServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, ISupplierRepository> supplierServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, IProductRepository> productServiceCache = new HashMap<>();

    @PostConstruct
    public void init() {
        System.out.println(userImplementations);
        
        for (IUserRepository impl : userImplementations) {
            userServiceCache.put(impl.getType(), impl);
        }

        for (IClientRepository impl : clientImplementations) {
            clientServiceCache.put(impl.getType(), impl);
        }

        for (ISupplierRepository impl : supplierImplementations) {
            supplierServiceCache.put(impl.getType(), impl);
        }
        
        for (IProductRepository impl : productImplementations) {
            productServiceCache.put(impl.getType(), impl);
        }
    }

    public IUserRepository getUserImpl(EnumDBImpl impl) {
        IUserRepository repository = userServiceCache.get(impl);

        if (repository == null) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }

    public IClientRepository getClientImpl(EnumDBImpl impl) {
        IClientRepository repository = clientServiceCache.get(impl);

        if (repository == null) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }

    public ISupplierRepository getSupplierImpl(EnumDBImpl impl) {
        ISupplierRepository repository = supplierServiceCache.get(impl);

        if (repository == null) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }

    public IProductRepository getProductImpl(EnumDBImpl impl) {
        IProductRepository repository = productServiceCache.get(impl);

        if (repository == null) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }
}

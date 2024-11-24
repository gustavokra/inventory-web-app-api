package com.kraemer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.repositories.IClientRepository;
import com.kraemer.domain.entities.repositories.IOrderRepository;
import com.kraemer.domain.entities.repositories.IProductRepository;
import com.kraemer.domain.entities.repositories.ISupplierRepository;
import com.kraemer.domain.entities.repositories.IUserRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.infra.database.sqlite.repositories.SqliteOrderRepository;
import com.kraemer.infra.database.sqlite.repositories.SqliteProductRepository;

import io.quarkus.arc.All;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DbFactory {

    @All
    private List<IUserRepository> userRepositoryImplementations;

    @All
    private List<IClientRepository> clientRepositoryImplementations;

    @All
    private List<ISupplierRepository> supplierRepositoryImplementations;

    @All
    private List<SqliteProductRepository> productRepositoryImplementations;

    @All
    private List<SqliteOrderRepository> orderRepositoryImplementations;

    private static final Map<EnumDBImpl, IUserRepository> userRepositoryServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, IClientRepository> clientRepositoryServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, ISupplierRepository> supplierRepositoryServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, IProductRepository> productRepositoryServiceCache = new HashMap<>();
    
    private static final Map<EnumDBImpl, IOrderRepository> orderRepositoryServiceCache = new HashMap<>();

    @PostConstruct
    public void init() {
        System.out.println(userRepositoryImplementations);
        
        for (IUserRepository impl : userRepositoryImplementations) {
            userRepositoryServiceCache.put(impl.getType(), impl);
        }

        for (IClientRepository impl : clientRepositoryImplementations) {
            clientRepositoryServiceCache.put(impl.getType(), impl);
        }

        for (ISupplierRepository impl : supplierRepositoryImplementations) {
            supplierRepositoryServiceCache.put(impl.getType(), impl);
        }
        
        for (IProductRepository impl : productRepositoryImplementations) {
            productRepositoryServiceCache.put(impl.getType(), impl);
        }
        
        for (IOrderRepository impl : orderRepositoryImplementations) {
            orderRepositoryServiceCache.put(impl.getType(), impl);
        }
    }

    public IUserRepository getUserRepositoryImpl(EnumDBImpl impl) {
        IUserRepository repository = userRepositoryServiceCache.get(impl);

        if (repository == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }

    public IClientRepository getClientRepositoryImpl(EnumDBImpl impl) {
        IClientRepository repository = clientRepositoryServiceCache.get(impl);

        if (repository == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }

    public ISupplierRepository getSupplierRepositoryImpl(EnumDBImpl impl) {
        ISupplierRepository repository = supplierRepositoryServiceCache.get(impl);

        if (repository == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }

    public IProductRepository getProductRepositoryImpl(EnumDBImpl impl) {
        IProductRepository repository = productRepositoryServiceCache.get(impl);

        if (repository == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }

    public IOrderRepository getOrderRepositoryImpl(EnumDBImpl impl) {
        IOrderRepository repository = orderRepositoryServiceCache.get(impl);

        if (repository == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }
}

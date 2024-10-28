package com.kraemer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.repositories.IUserRepository;
import com.kraemer.domain.utils.exception.InvetoryAppException;

import io.quarkus.arc.All;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DbFactory {

    @All
    private List<IUserRepository> implementations;

    private static final Map<EnumDBImpl, IUserRepository> serviceCache = new HashMap<>();

    @PostConstruct
    public void init() {
        System.out.println(implementations);
        for (IUserRepository impl : implementations) {
            serviceCache.put(impl.getType(), impl);
        }
    }

    public IUserRepository getImpl(EnumDBImpl impl) {
        IUserRepository repository = serviceCache.get(impl);

        if (repository == null) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }
}

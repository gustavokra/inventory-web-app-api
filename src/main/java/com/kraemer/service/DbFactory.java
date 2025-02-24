package com.kraemer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.repositories.IClientRepository;
import com.kraemer.domain.entities.repositories.IFormaPagamentoRepository;
import com.kraemer.domain.entities.repositories.IGrupoRepository;
import com.kraemer.domain.entities.repositories.IMarcaRepository;
import com.kraemer.domain.entities.repositories.IOrderRepository;
import com.kraemer.domain.entities.repositories.IProductRepository;
import com.kraemer.domain.entities.repositories.ISupplierRepository;
import com.kraemer.domain.entities.repositories.ITituloRepository;
import com.kraemer.domain.entities.repositories.IUserRepository;
import com.kraemer.domain.entities.repositories.IOperacaoCaixaRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;

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
    private List<IProductRepository> productRepositoryImplementations;

    @All
    private List<IOrderRepository> orderRepositoryImplementations;

    @All
    private List<IMarcaRepository> marcaRepositoryImplementations;

    @All
    private List<IGrupoRepository> grupoRepositoryImplementations;
    
    @All
    private List<IFormaPagamentoRepository> formaPagamentoRepositoryImplementations;
    
    @All
    private List<ITituloRepository> tituloRepositoryImplementations;
    
    @All
    private List<IOperacaoCaixaRepository> operacaoCaixaRepositoryImplementations;
    
    private static final Map<EnumDBImpl, IUserRepository> userRepositoryServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, IClientRepository> clientRepositoryServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, ISupplierRepository> supplierRepositoryServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, IProductRepository> productRepositoryServiceCache = new HashMap<>();
    
    private static final Map<EnumDBImpl, IOrderRepository> orderRepositoryServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, IMarcaRepository> marcaRepositoryServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, IGrupoRepository> grupoRepositoryServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, IFormaPagamentoRepository> formaRepositoryServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, ITituloRepository> tituloServiceCache = new HashMap<>();

    private static final Map<EnumDBImpl, IOperacaoCaixaRepository> operacaoCaixaRepositoryServiceCache = new HashMap<>();

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

        for(IMarcaRepository impl : marcaRepositoryImplementations) {
            marcaRepositoryServiceCache.put(impl.getType(), impl);
        }

        for(IGrupoRepository impl : grupoRepositoryImplementations) {
            grupoRepositoryServiceCache.put(impl.getType(), impl);
        }

        for(IFormaPagamentoRepository impl : formaPagamentoRepositoryImplementations) {
            formaRepositoryServiceCache.put(impl.getType(), impl);
        }

        for(ITituloRepository impl : tituloRepositoryImplementations) {
            tituloServiceCache.put(impl.getType(), impl);
        }

        for(IOperacaoCaixaRepository impl : operacaoCaixaRepositoryImplementations) {
            operacaoCaixaRepositoryServiceCache.put(impl.getType(), impl);
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

    public IMarcaRepository getMarcaRepositoryImpl(EnumDBImpl impl) {
        IMarcaRepository repository = marcaRepositoryServiceCache.get(impl);

        if (repository == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }

    public IGrupoRepository getGrupoRepositoryImpl(EnumDBImpl impl) {
        IGrupoRepository repository = grupoRepositoryServiceCache.get(impl);

        if (repository == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }

    public IFormaPagamentoRepository getFormaPagamentoRepositoryImpl(EnumDBImpl impl) {
        IFormaPagamentoRepository repository = formaRepositoryServiceCache.get(impl);

        if (repository == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }

    public ITituloRepository getTituloRepositoryImpl(EnumDBImpl impl) {
        ITituloRepository repository = tituloServiceCache.get(impl);

        if (repository == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }

        return repository;
    }

    public IOperacaoCaixaRepository getOperacaoCaixaRepositoryImpl(EnumDBImpl impl) {
        IOperacaoCaixaRepository repository = operacaoCaixaRepositoryServiceCache.get(impl);
        
        if (repository == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "dbImpl");
        }
        
        return repository;
    }

}

package com.kraemer.service;

import java.util.List;

import com.kraemer.domain.entities.dto.SupplierDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.usecases.supplier.CreateSupplier;
import com.kraemer.domain.usecases.supplier.DeleteSupplier;
import com.kraemer.domain.usecases.supplier.FindAllSuppliers;
import com.kraemer.domain.usecases.supplier.UpdateSupplierInfo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SupplierService extends AbstractService {

    @Transactional
    public SupplierDTO create(SupplierDTO dto, EnumDBImpl dbImpl) {
        var repository = dbFactory.getSupplierRepositoryImpl(dbImpl);

        var createSupplier = new CreateSupplier(repository);

        return createSupplier.execute(dto);
    }

    public List<SupplierDTO> findAll(EnumDBImpl dbImpl) {
        var repository = dbFactory.getSupplierRepositoryImpl(dbImpl);

        var findAllSuppliers = new FindAllSuppliers(repository);

        return findAllSuppliers.execute();
    }

    @Transactional
    public SupplierDTO updateSupplierInfo(SupplierDTO dto, Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getSupplierRepositoryImpl(dbImpl);

        var updateSupplierInfo = new UpdateSupplierInfo(repository);

        return updateSupplierInfo.execute(dto, id);
    }

    @Transactional
    public void delete(Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getSupplierRepositoryImpl(dbImpl);

        var deleteSupplier = new DeleteSupplier(repository);

        deleteSupplier.execute(id);
    }
}

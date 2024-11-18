package com.kraemer.domain.usecases.supplier;

import java.util.List;

import com.kraemer.domain.entities.dto.SupplierDTO;
import com.kraemer.domain.entities.mappers.SupplierMapper;
import com.kraemer.domain.entities.repositories.ISupplierRepository;

import jakarta.inject.Inject;

public class FindAllSuppliers {

    @Inject
    private ISupplierRepository repository;

    public FindAllSuppliers(ISupplierRepository repository) {
        this.repository = repository;
    }

    public List<SupplierDTO> execute() {
        return repository.findAll()
                .stream()
                .map(supplier -> SupplierMapper.toDTO(supplier))
                .toList();
    }
}

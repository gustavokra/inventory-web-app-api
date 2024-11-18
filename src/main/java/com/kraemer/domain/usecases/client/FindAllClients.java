package com.kraemer.domain.usecases.client;

import java.util.List;

import com.kraemer.domain.entities.dto.ClientDTO;
import com.kraemer.domain.entities.mappers.ClientMapper;
import com.kraemer.domain.entities.repositories.IClientRepository;

import jakarta.inject.Inject;

public class FindAllClients {

    @Inject
    private IClientRepository repository;

    public FindAllClients(IClientRepository repository) {
        this.repository = repository;
    }

    public List<ClientDTO> execute() {
        return repository.findAll()
                .stream()
                .map(client -> ClientMapper.toDTO(client))
                .toList();
    }
}

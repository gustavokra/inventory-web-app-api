package com.kraemer.domain.usecases.marca;

import java.util.List;

import com.kraemer.domain.entities.dto.MarcaDTO;
import com.kraemer.domain.entities.mappers.MarcaMapper;
import com.kraemer.domain.entities.repositories.IMarcaRepository;

public class EncontrarTodasMarcas {
    
    private IMarcaRepository repository;

    public EncontrarTodasMarcas(IMarcaRepository repository) {
        this.repository = repository;
    }

    public List<MarcaDTO> execute() {
        return repository.findAll().stream().map(MarcaMapper::toDTO).toList();
    }

}

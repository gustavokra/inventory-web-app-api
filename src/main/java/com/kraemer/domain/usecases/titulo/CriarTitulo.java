package com.kraemer.domain.usecases.titulo;

import com.kraemer.domain.entities.dto.TituloDTO;
import com.kraemer.domain.entities.mappers.TituloMapper;
import com.kraemer.domain.entities.repositories.ITituloRepository;

public class CriarTitulo {
    
    private ITituloRepository repository;

    public CriarTitulo(ITituloRepository repository) {
        this.repository = repository;
    }

    public TituloDTO execute(TituloDTO dto) {
        repository.criar(TituloMapper.toBO(dto));

        return dto;
    }


}

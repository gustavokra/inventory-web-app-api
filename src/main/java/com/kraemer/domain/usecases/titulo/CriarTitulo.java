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
        var tituloBO = TituloMapper.toBO(dto);
        
        tituloBO.handleCreate();

        repository.criar(tituloBO);

        return dto;
    }


}

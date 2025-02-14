package com.kraemer.domain.usecases.grupo;

import java.util.List;

import com.kraemer.domain.entities.dto.GrupoDTO;
import com.kraemer.domain.entities.mappers.GrupoMapper;
import com.kraemer.domain.entities.repositories.IGrupoRepository;

public class EncontrarTodosGrupos {
    
    private IGrupoRepository repository;

    public EncontrarTodosGrupos(IGrupoRepository repository) {
        this.repository = repository;
    }

    public List<GrupoDTO> execute() {
        return repository.findAll().stream().map(GrupoMapper::toDTO).toList();
    }

}

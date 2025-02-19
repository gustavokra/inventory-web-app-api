package com.kraemer.domain.usecases.titulo;

import java.util.List;

import com.kraemer.domain.entities.dto.TituloDTO;
import com.kraemer.domain.entities.mappers.TituloMapper;
import com.kraemer.domain.entities.repositories.ITituloRepository;

public class EncontrarTodosTitulos {
    
    private ITituloRepository repository;

    public EncontrarTodosTitulos(ITituloRepository repository) {
        this.repository = repository;
    }

    public List<TituloDTO> execute() {
        return repository.encontrarTodos().stream().map(TituloMapper::toDTO).toList();
    }

}

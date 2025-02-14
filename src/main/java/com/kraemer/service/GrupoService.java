package com.kraemer.service;

import java.util.List;

import com.kraemer.domain.entities.dto.GrupoDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.usecases.grupo.CriarGrupo;
import com.kraemer.domain.usecases.grupo.EncontrarTodosGrupos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GrupoService extends AbstractService {
    
    public List<GrupoDTO> encontrarTodos(EnumDBImpl dbImpl) {
        var repository = dbFactory.getGrupoRepositoryImpl(dbImpl);

        var encontrarTodosGrupos = new EncontrarTodosGrupos(repository);

        return encontrarTodosGrupos.execute();
    } 

    @Transactional
    public GrupoDTO criar(GrupoDTO dto, EnumDBImpl dbImpl) {
        var repository = dbFactory.getGrupoRepositoryImpl(dbImpl);

        var criarMarca = new CriarGrupo(repository);

        return criarMarca.execute(dto);
    }

}
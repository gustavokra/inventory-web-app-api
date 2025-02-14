package com.kraemer.domain.usecases.grupo;

import java.util.List;

import com.kraemer.domain.entities.dto.GrupoDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.GrupoMapper;
import com.kraemer.domain.entities.repositories.IGrupoRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class CriarGrupo {
    
    private IGrupoRepository repository;

    public CriarGrupo(IGrupoRepository repository) {
        this.repository = repository;
    }

    public GrupoDTO execute(GrupoDTO dto) {
        validarGrupoExistente(dto.getNome());

        repository.create(GrupoMapper.toBO(dto));

        return dto;
    }

    private void validarGrupoExistente(String nome) {
        var fieldProductName = new QueryFieldInfoVO("nome", nome);
        var existentProduct = repository.findFirstBy(List.of(fieldProductName));
        
        if(existentProduct != null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_CADASTRADA, "Grupo com esse nome");
        }
    }

}

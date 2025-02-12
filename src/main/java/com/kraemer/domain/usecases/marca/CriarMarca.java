package com.kraemer.domain.usecases.marca;

import java.util.List;

import com.kraemer.domain.entities.dto.MarcaDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.MarcaMapper;
import com.kraemer.domain.entities.repositories.IMarcaRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class CriarMarca {
    
    private IMarcaRepository repository;

    public CriarMarca(IMarcaRepository repository) {
        this.repository = repository;
    }

    public MarcaDTO execute(MarcaDTO dto) {
        validarMarcaExistente(dto.getNome());

        repository.create(MarcaMapper.toBO(dto));

        return dto;
    }

    private void validarMarcaExistente(String nome) {
        var fieldProductName = new QueryFieldInfoVO("nome", nome);
        var existentProduct = repository.findFirstBy(List.of(fieldProductName));
        
        if(existentProduct != null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_CADASTRADA, "Marca com esse nome");
        }
    }

}

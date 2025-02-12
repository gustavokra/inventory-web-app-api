package com.kraemer.service;

import java.util.List;

import com.kraemer.domain.entities.dto.MarcaDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.usecases.marca.CriarMarca;
import com.kraemer.domain.usecases.marca.EncontrarTodasMarcas;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MarcaService extends AbstractService {
    
    public List<MarcaDTO> encontrarTodas(EnumDBImpl dbImpl) {
        var repository = dbFactory.getMarcaRepositoryImpl(dbImpl);

        var encontrarTodasMarcas = new EncontrarTodasMarcas(repository);

        return encontrarTodasMarcas.execute();
    } 

    @Transactional
    public MarcaDTO criar(MarcaDTO dto, EnumDBImpl dbImpl) {
        var repository = dbFactory.getMarcaRepositoryImpl(dbImpl);

        var criarMarca = new CriarMarca(repository);

        return criarMarca.execute(dto);
    }

}

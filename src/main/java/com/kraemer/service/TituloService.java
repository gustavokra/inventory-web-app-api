package com.kraemer.service;

import java.util.List;

import com.kraemer.domain.entities.dto.TituloDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.usecases.titulo.AtualizarTitulo;
import com.kraemer.domain.usecases.titulo.CriarTitulo;
import com.kraemer.domain.usecases.titulo.EncontrarTodosTitulos;
import com.kraemer.domain.usecases.titulo.ExcluirTitulo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TituloService extends AbstractService {
    public List<TituloDTO> encontrarTodos(EnumDBImpl dbImpl) {
        var repository = dbFactory.getTituloRepositoryImpl(dbImpl);

        var encontrarTodosTitulos = new EncontrarTodosTitulos(repository);

        return encontrarTodosTitulos.execute();
    }

    @Transactional
    public TituloDTO criar(TituloDTO dto, EnumDBImpl dbImpl) {
        var repository = dbFactory.getTituloRepositoryImpl(dbImpl);

        var criarMarca = new CriarTitulo(repository);

        return criarMarca.execute(dto);
    }

    @Transactional
    public TituloDTO atualizar(TituloDTO dto, Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getTituloRepositoryImpl(dbImpl);

        var updateTituloInfo = new AtualizarTitulo(repository);

        return updateTituloInfo.execute(dto, id);
    }

    @Transactional
    public void excluir(Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getTituloRepositoryImpl(dbImpl);

        var deleteTitulo = new ExcluirTitulo(repository);

        deleteTitulo.execute(id);
    }
}

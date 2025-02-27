package com.kraemer.service;

import java.util.List;

import com.kraemer.domain.entities.dto.OperacaoCaixaDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.usecases.operacaocaixa.AtualizarOperacaoCaixa;
import com.kraemer.domain.usecases.operacaocaixa.CriarOperacaoCaixa;
import com.kraemer.domain.usecases.operacaocaixa.EncontrarTodasOperacoesCaixa;
import com.kraemer.domain.usecases.operacaocaixa.ExcluirOperacaoCaixa;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OperacaoCaixaService extends AbstractService {

    @Transactional
    public OperacaoCaixaDTO criar(OperacaoCaixaDTO dto, EnumDBImpl dbImpl) {
        var repository = dbFactory.getOperacaoCaixaRepositoryImpl(dbImpl);

        var criarOperacaoCaixa = new CriarOperacaoCaixa(repository);

        return criarOperacaoCaixa.execute(dto);
    }

    @Transactional
    public OperacaoCaixaDTO atualizar(OperacaoCaixaDTO dto, Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getOperacaoCaixaRepositoryImpl(dbImpl);

        var atualizarOperacaoCaixa = new AtualizarOperacaoCaixa(repository);

        return atualizarOperacaoCaixa.execute(dto, id);
    }

    public List<OperacaoCaixaDTO> buscarTodos(EnumDBImpl dbImpl) {
        var repository = dbFactory.getOperacaoCaixaRepositoryImpl(dbImpl);

        var encontrarTodasOperacoesCaixa = new EncontrarTodasOperacoesCaixa(repository);

        return encontrarTodasOperacoesCaixa.execute();
    }

    @Transactional
    public void excluir(Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getOperacaoCaixaRepositoryImpl(dbImpl);

        var excluirOperacaoCaixa = new ExcluirOperacaoCaixa(repository);

        excluirOperacaoCaixa.execute(id);
    }

}
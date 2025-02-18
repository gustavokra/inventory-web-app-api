package com.kraemer.service;

import java.util.List;

import com.kraemer.domain.entities.dto.FormaPagamentoDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.usecases.formaPagamento.AtualizarFormaPagamento;
import com.kraemer.domain.usecases.formaPagamento.CriarFormaPagamento;
import com.kraemer.domain.usecases.formaPagamento.EncontrarTodasFormasPagamento;
import com.kraemer.domain.usecases.formaPagamento.ExcluirFormaPagamento;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FormaPagamentoService extends AbstractService {

    public List<FormaPagamentoDTO> encontrarTodas(EnumDBImpl dbImpl) {
        var repository = dbFactory.getFormaPagamentoRepositoryImpl(dbImpl);

        var encontrarTodosFormaPagamentos = new EncontrarTodasFormasPagamento(repository);

        return encontrarTodosFormaPagamentos.execute();
    }

    @Transactional
    public FormaPagamentoDTO criar(FormaPagamentoDTO dto, EnumDBImpl dbImpl) {
        var repository = dbFactory.getFormaPagamentoRepositoryImpl(dbImpl);

        var criarMarca = new CriarFormaPagamento(repository);

        return criarMarca.execute(dto);
    }

    @Transactional
    public FormaPagamentoDTO atualizar(FormaPagamentoDTO dto, Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getFormaPagamentoRepositoryImpl(dbImpl);

        var updateFormaPagamentoInfo = new AtualizarFormaPagamento(repository);

        return updateFormaPagamentoInfo.execute(dto, id);
    }

    @Transactional
    public void excluir(Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getFormaPagamentoRepositoryImpl(dbImpl);

        var deleteFormaPagamento = new ExcluirFormaPagamento(repository);

        deleteFormaPagamento.execute(id);
    }

}

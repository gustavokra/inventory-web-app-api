package com.kraemer.domain.usecases.formaPagamento;

import java.util.List;

import com.kraemer.domain.entities.FormaPagamentoBO;
import com.kraemer.domain.entities.dto.FormaPagamentoDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.FormaPagamentoMapper;
import com.kraemer.domain.entities.repositories.IFormaPagamentoRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

import jakarta.inject.Inject;

public class AtualizarFormaPagamento {

    @Inject
    private IFormaPagamentoRepository repository;

    public AtualizarFormaPagamento(IFormaPagamentoRepository repository) {
        this.repository = repository;
    }

    public FormaPagamentoDTO execute(FormaPagamentoDTO dto, Long id) {
        var formaPagamentoAtualizar = verifyExistingFormaPagamento(id);
        verifyDuplicateName(dto.getNome(), id);

        formaPagamentoAtualizar.verificarAtualizacao(dto.getNome(), dto.getnumeroMaxParcelas());
        repository.atualizar(formaPagamentoAtualizar);

        return FormaPagamentoMapper.toDTO(formaPagamentoAtualizar);
    }

    private FormaPagamentoBO verifyExistingFormaPagamento(Long id) {
        verifyId(id);
        var idField = new QueryFieldInfoVO("id", String.valueOf(id));
        var formaPagamentoBO = repository.encontrarPrimeiroPor(List.of(idField));

        if (formaPagamentoBO == null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "Fornecedor", "id", id);
        }

        return formaPagamentoBO;
    }

    private void verifyId(Long id) {
        if (id == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id");
        }
    }

    private void verifyDuplicateName(String nome, Long id) {
        var nomeField = new QueryFieldInfoVO("nome", nome);
        var existingFormaPagamento = repository.encontrarPrimeiroPor(List.of(nomeField));

        if (existingFormaPagamento != null && !existingFormaPagamento.getId().equals(id)) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_CADASTRADA, "Forma de Pagamento com nome '" + nome + "'");
        }
    }
}

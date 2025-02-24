package com.kraemer.domain.usecases.operacaocaixa;

import java.util.List;
import java.util.Objects;

import com.kraemer.domain.entities.OperacaoCaixaBO;
import com.kraemer.domain.entities.dto.OperacaoCaixaDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.OperacaoCaixaMapper;
import com.kraemer.domain.entities.repositories.IOperacaoCaixaRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class AtualizarOperacaoCaixa {

    private final IOperacaoCaixaRepository operacaoCaixaRepository;

    public AtualizarOperacaoCaixa(IOperacaoCaixaRepository operacaoCaixaRepository) {
        this.operacaoCaixaRepository = Objects.requireNonNull(operacaoCaixaRepository, "Repository cannot be null");
    }

    public OperacaoCaixaDTO execute(OperacaoCaixaDTO operacaoCaixaDTO, Long operacaoId) {
        validarParametros(operacaoCaixaDTO, operacaoId);

        var operacaoExistente = buscarOperacaoExistente(operacaoId);
        var operacaoAtualizada = atualizarOperacao(operacaoExistente, operacaoCaixaDTO);

        return OperacaoCaixaMapper.toDTO(operacaoAtualizada);
    }

    private void validarParametros(OperacaoCaixaDTO operacaoCaixaDTO, Long operacaoId) {
        if (operacaoCaixaDTO == null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "operacaoCaixa", "dto", null);
        }
        if (operacaoId == null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "operacaoCaixa", "id", null);
        }
    }

    private OperacaoCaixaBO buscarOperacaoExistente(Long operacaoId) {
        var operacaoExistente = operacaoCaixaRepository.encontrarPrimeiroPor(List.of(
                new QueryFieldInfoVO("id", String.valueOf(operacaoId))));

        if (operacaoExistente == null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "operacaoCaixa", "id", operacaoId);
        }

        return operacaoExistente;
    }

    private OperacaoCaixaBO atualizarOperacao(
            OperacaoCaixaBO operacaoExistente,
            OperacaoCaixaDTO operacaoCaixaDTO) {
        operacaoExistente.handleUpdate(
                operacaoCaixaDTO.getDataFechamento(),
                operacaoCaixaDTO.getSaldoFinal(),
                operacaoCaixaDTO.getTotalVendas(),
                operacaoCaixaDTO.getSituacao(),
                operacaoCaixaDTO.getObservacoes(),
                operacaoCaixaDTO.getUsuarioId());

        var operacaoAtualizada = operacaoCaixaRepository.atualizar(operacaoExistente);

        if (operacaoAtualizada == null) {
            throw new InventoryAppException(EnumErrorCode.ERRO_AO_EDITAR, "operacaoCaixa");
        }

        return operacaoAtualizada;
    }
}
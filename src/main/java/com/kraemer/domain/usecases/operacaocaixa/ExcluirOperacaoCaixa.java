package com.kraemer.domain.usecases.operacaocaixa;

import java.util.List;

import com.kraemer.domain.entities.OperacaoCaixaBO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.repositories.IOperacaoCaixaRepository;
import com.kraemer.domain.vo.QueryFieldInfoVO;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class ExcluirOperacaoCaixa {
    private static final String ENTIDADE_NOME = "OperacaoCaixa";
    private static final String CAMPO_ID = "id";
    
    private final IOperacaoCaixaRepository operacaoCaixaRepository;

    public ExcluirOperacaoCaixa(IOperacaoCaixaRepository operacaoCaixaRepository) {
        this.operacaoCaixaRepository = operacaoCaixaRepository;
    }
    
    public void execute(Long id) {
        validarId(id);
        encontrarOperacaoCaixa(id);
        excluirOperacaoCaixa(id);
    }

    private void validarId(Long id) {
        if (id == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, CAMPO_ID);
        }
    }

    private void excluirOperacaoCaixa(Long id) {
        if (!operacaoCaixaRepository.excluir(id)) {
            throw new InventoryAppException(EnumErrorCode.ERRO_AO_EXCLUIR, ENTIDADE_NOME, CAMPO_ID, id);
        }
    }

    private void encontrarOperacaoCaixa(Long id) {
        var criterio = criarCriterioBuscaPorId(id);
        var operacaoCaixa = operacaoCaixaRepository.encontrarPrimeiroPor(criterio);

        if (operacaoCaixa == null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, ENTIDADE_NOME, CAMPO_ID, id);
        }
    }

    private List<QueryFieldInfoVO> criarCriterioBuscaPorId(Long id) {
        return List.of(new QueryFieldInfoVO(CAMPO_ID, String.valueOf(id)));
    }
}

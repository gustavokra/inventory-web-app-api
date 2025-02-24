package com.kraemer.domain.entities.repositories;

import java.util.List;

import com.kraemer.domain.entities.OperacaoCaixaBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public interface IOperacaoCaixaRepository {
    OperacaoCaixaBO criar(OperacaoCaixaBO bo);
    
    OperacaoCaixaBO atualizar(OperacaoCaixaBO bo);
    
    List<OperacaoCaixaBO> encontrarTodos();
    
    OperacaoCaixaBO encontrarPrimeiroPor(List<QueryFieldInfoVO> queryFields);
    
    List<OperacaoCaixaBO> encontrarTodosPor(List<QueryFieldInfoVO> queryFieldsInfoVO);
    
    boolean excluir(Long id);
    
    EnumDBImpl getType();
} 
package com.kraemer.domain.entities.repositories;

import java.util.List;

import com.kraemer.domain.entities.FormaPagamentoBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public interface IFormaPagamentoRepository {
    FormaPagamentoBO criar(FormaPagamentoBO bo);

    FormaPagamentoBO atualizar(FormaPagamentoBO bo);

    List<FormaPagamentoBO> encontrarTodos();

    FormaPagamentoBO encontrarPrimeiroPor(List<QueryFieldInfoVO> queryFields);

    List<FormaPagamentoBO> encontrarTodosPor(List<QueryFieldInfoVO> queryFieldsInfoVO);

    boolean excluir(Long id);

    EnumDBImpl getType();
}

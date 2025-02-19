package com.kraemer.domain.entities.repositories;

import java.util.List;

import com.kraemer.domain.entities.TituloBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public interface ITituloRepository {
    TituloBO criar(TituloBO bo);

    TituloBO atualizar(TituloBO bo);

    List<TituloBO> encontrarTodos();

    TituloBO encontrarPrimeiroPor(List<QueryFieldInfoVO> queryFields);

    List<TituloBO> encontrarTodosPor(List<QueryFieldInfoVO> queryFieldsInfoVO);

    boolean excluir(Long id);

    EnumDBImpl getType();
}

package com.kraemer.domain.entities.repositories;

import java.util.List;

import com.kraemer.domain.entities.GrupoBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public interface IGrupoRepository {
    GrupoBO create(GrupoBO bo);

    List<GrupoBO> findAll();

    GrupoBO findFirstBy(List<QueryFieldInfoVO> queryFields);

    List<GrupoBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO);

    boolean delete(Long id);

    EnumDBImpl getType();
}

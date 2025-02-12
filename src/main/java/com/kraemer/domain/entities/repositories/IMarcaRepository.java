package com.kraemer.domain.entities.repositories;

import java.util.List;

import com.kraemer.domain.entities.MarcaBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public interface IMarcaRepository {

    MarcaBO create(MarcaBO bo);

    List<MarcaBO> findAll();

    MarcaBO findFirstBy(List<QueryFieldInfoVO> queryFields);

    List<MarcaBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO);

    boolean delete(Long id);

    EnumDBImpl getType();

}

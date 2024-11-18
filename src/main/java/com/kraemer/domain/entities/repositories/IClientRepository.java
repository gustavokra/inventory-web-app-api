package com.kraemer.domain.entities.repositories;

import java.util.List;

import com.kraemer.domain.entities.ClientBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public interface IClientRepository {

    ClientBO create(ClientBO bo);

    ClientBO merge(ClientBO bo);

    ClientBO findFirstBy(List<QueryFieldInfoVO> queryFields);

    List<ClientBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO);

    List<ClientBO> findAll();

    boolean delete(Long id);

    EnumDBImpl getType();

}

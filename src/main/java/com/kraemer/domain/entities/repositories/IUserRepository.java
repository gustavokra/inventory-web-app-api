package com.kraemer.domain.entities.repositories;

import java.util.List;

import com.kraemer.domain.entities.UserBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public interface IUserRepository {
    
    UserBO create(UserBO bo);

    UserBO findFirstBy(List<QueryFieldInfoVO> queryFields);

    List<UserBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO);

    EnumDBImpl getType();

}

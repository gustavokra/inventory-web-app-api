package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.TransactionBO;
import com.kraemer.domain.entities.dto.TransactionDTO;
import com.kraemer.domain.entities.vo.CreatedAtVO;

public class TransactionMapper {

    public static TransactionBO toBO(TransactionDTO dto) {
        if (dto == null) {
            return null;
        }

        return new TransactionBO(
                dto.getId(),
                new CreatedAtVO(dto.getCreatedAt()),
                dto.getTransactionType(),
                dto.getValue(),
                dto.getProduct(),
                dto.getOrder());
    }

    public static TransactionDTO toDTO(TransactionBO bo) {
        if (bo == null) {
            return null;
        }

        TransactionDTO dto = new TransactionDTO();
        dto.setId(bo.getId());
        dto.setCreatedAt(bo.getCreatedAt().getValue());
        dto.setTransactionType(bo.getTransactionType());
        dto.setValue(bo.getValue());
        dto.setProduct(bo.getProduct());
        dto.setOrder(bo.getOrder());
        return dto;
    }

}

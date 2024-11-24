package com.kraemer.domain.usecases.order;

import java.util.List;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.repositories.IOrderRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class DeleteOrder {

    private IOrderRepository repository;

    public DeleteOrder(IOrderRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        if (id == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id");
        }

        var idField = new QueryFieldInfoVO("id", String.valueOf(id));
        var existingOrder = repository.findFirstBy(List.of(idField));

        if (existingOrder == null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "pedido", "id", id);
        }

        if(!repository.delete(id)) {
            throw new InventoryAppException(EnumErrorCode.ERRO_AO_EXCLUIR, "pedido", "id", id);
        }
    }

}

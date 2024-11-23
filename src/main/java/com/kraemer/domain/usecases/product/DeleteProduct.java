package com.kraemer.domain.usecases.product;

import java.util.List;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.repositories.IProductRepository;
import com.kraemer.domain.utils.NumericUtil;
import com.kraemer.domain.utils.exception.InvetoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class DeleteProduct {

    private IProductRepository repository;

    public DeleteProduct(IProductRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        if (NumericUtil.isNullOrZero(id)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id");
        }

        var idField = new QueryFieldInfoVO("id", String.valueOf(id));
        var productToDelete = repository.findFirstBy(List.of(idField));

        if (productToDelete == null) {
            throw new InvetoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "produto", "id", id);
        }

        if (!repository.delete(id)) {
            throw new InvetoryAppException(EnumErrorCode.ERRO_AO_EXCLUIR, "produto", "id", id);
        }
    }
}

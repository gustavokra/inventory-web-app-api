package com.kraemer.infra.database.sqlite.repositories;

import java.util.ArrayList;
import java.util.List;

import com.kraemer.domain.entities.ProductBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.repositories.IProductRepository;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.vo.QueryFieldInfoVO;
import com.kraemer.infra.database.sqlite.mappers.SqliteProductMapper;
import com.kraemer.infra.database.sqlite.model.SqliteProduct;
import com.kraemer.infra.database.sqlite.model.SqliteSupplier;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SqliteProductRepository implements IProductRepository {

    public ProductBO create(ProductBO bo) {
        var panacheProduct = SqliteProductMapper.toEntity(bo);

        if (bo.getSupplier() != null) {
            SqliteSupplier productSupplier = SqliteSupplier.findById(bo.getSupplier().getId());

            panacheProduct.setSupplier(productSupplier);
        }

        panacheProduct.persist();

        return SqliteProductMapper.toDomain(panacheProduct);
    }

    public ProductBO merge(ProductBO bo) {
        var entity = SqliteProductMapper.toEntity(bo);

        SqliteProduct.getEntityManager().merge(entity);

        return bo;
    }

    @Override
    public ProductBO findFirstBy(List<QueryFieldInfoVO> queryFieldInfoVO) {
        return ListUtil.first(findAllBy(queryFieldInfoVO));
    }

    @Override
    public List<ProductBO> findAll() {
        return ListUtil.stream(SqliteProduct.listAll(Sort.ascending("name", "quantity")))
                .map(value -> SqliteProductMapper.toDomain((SqliteProduct) value))
                .toList();
    }

    @Override
    public List<ProductBO> findAllBy(List<QueryFieldInfoVO> queryFieldsVO) {
        var params = new ArrayList<>();
        var query = new StringBuilder();

        int paramIndex = 1;

        if (queryFieldsVO == null) {
            return ListUtil.stream(SqliteProduct.listAll(Sort.ascending("name", "document")))
                    .map(value -> SqliteProductMapper.toDomain((SqliteProduct) value))
                    .toList();
        }

        for (var val : queryFieldsVO) {
            String formattedCondition;
            if (val.getFieldValue() != null) {
                formattedCondition = val.getFieldName() + " = ?" + paramIndex++;
                params.add(val.getFieldValue());
            } else {
                formattedCondition = val.getFieldName() + " IS NULL";
            }

            if (query.length() > 0) {
                query.append(" AND ");
            }
            query.append(formattedCondition);
        }

        return ListUtil.stream(SqliteProduct.list(query.toString(), params.toArray()))
                .map(value -> SqliteProductMapper.toDomain((SqliteProduct) value))
                .toList();
    }

    @Override
    public EnumDBImpl getType() {
        return EnumDBImpl.SQLITE;
    }

    @Override
    public boolean delete(Long id) {
        return SqliteProduct.deleteById(id);
    }

}

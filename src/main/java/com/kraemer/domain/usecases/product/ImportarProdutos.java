package com.kraemer.domain.usecases.product;

import java.util.ArrayList;
import java.util.List;

import com.kraemer.domain.entities.GrupoBO;
import com.kraemer.domain.entities.dto.ModeloImportacaoProdutosDTO;
import com.kraemer.domain.entities.dto.ProductDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.GrupoMapper;
import com.kraemer.domain.entities.mappers.ProductMapper;
import com.kraemer.domain.entities.repositories.IGrupoRepository;
import com.kraemer.domain.entities.repositories.IProductRepository;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.utils.StringUtil;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class ImportarProdutos {

    private IProductRepository productRepository;
    private IGrupoRepository grupoRepository;

    public ImportarProdutos(IProductRepository productRepository, IGrupoRepository grupoRepository) {
        this.productRepository = productRepository;
        this.grupoRepository = grupoRepository;
    }

    public void execute(List<ModeloImportacaoProdutosDTO> dtos) {
        var products = new ArrayList<ProductDTO>();

        if (ListUtil.isNullOrEmpty(dtos)) {
            throw new InventoryAppException(EnumErrorCode.ERRO_AO_CRIAR, "Lista de produtos vazia");
        }

        for (var dto : dtos) {
            var product = new ProductDTO();
            product.setName(dto.getNome());
            product.setPrice(dto.getPrecoVenda());
            product.setQuantity(dto.getEstoqueAtual());
            product.setCostPrice(dto.getCusto());
            product.setActive(true);

            if (StringUtil.isNotNullOrEmpty(dto.getCategoria())) {
                GrupoBO grupo = new GrupoBO(null, dto.getCategoria());

                var grupoCriado = grupoRepository.create(grupo);

                product.setGrupo(GrupoMapper.toDTO(grupoCriado));

            }
            
            products.add(product);
        }

        products.stream()
                .map(ProductMapper::toBO)
                .forEach(product -> {
                    productRepository.create(product);
                });
    }

}

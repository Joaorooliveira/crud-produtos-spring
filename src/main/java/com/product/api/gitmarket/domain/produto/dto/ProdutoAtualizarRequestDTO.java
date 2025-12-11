package com.product.api.crud_produtos.domain.produto.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoAtualizarRequestDTO(
        String nome,
        BigDecimal preco,
        Integer quantidade,
        UUID categoriaId
) {
}
package com.product.api.crud_produtos.domain.produto.dto;

import java.util.UUID;

public record ProdutoAtualizarRequestDto(String nome, Float preco, Integer quantidade, UUID categoriaId) {
}
package com.product.api.crud_produtos.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.product.api.crud_produtos.entities.Produto}
 */
public record ProdutoAtualizarRequestDto(String nome, Float preco, Integer quantidade){
}
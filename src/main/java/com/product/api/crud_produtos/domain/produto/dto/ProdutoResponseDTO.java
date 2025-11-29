package com.product.api.crud_produtos.domain.produto.dto;

import com.product.api.crud_produtos.domain.produto.Produto;

import java.util.UUID;


public record ProdutoResponseDTO(
        UUID id,
        String nome,
        float preco,
        int quantidade
) {

    public static ProdutoResponseDTO fromEntity(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade()
        );
    }
}


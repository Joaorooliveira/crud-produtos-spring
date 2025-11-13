package com.product.api.crud_produtos.dto;

import com.product.api.crud_produtos.entity.Produto;


public record ProdutoResponseDTO(
        String nome,
        float preco,
        int quantidade
) {

    public static ProdutoResponseDTO fromEntity(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade()
        );
    }
}


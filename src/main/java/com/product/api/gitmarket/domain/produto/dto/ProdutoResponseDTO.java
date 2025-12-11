package com.product.api.gitmarket.domain.produto.dto;

import com.product.api.gitmarket.domain.categoria.dto.CategoriaResponseDTO;
import com.product.api.gitmarket.domain.produto.Produto;

import java.math.BigDecimal;
import java.util.UUID;


public record ProdutoResponseDTO(
        UUID id,
        String nome,
        BigDecimal preco,
        int quantidade,
        CategoriaResponseDTO categoria
) {

    public static ProdutoResponseDTO fromEntity(Produto produto) {
        CategoriaResponseDTO categoriaDTO = new CategoriaResponseDTO(
                produto.getCategoria().getId(),
                produto.getCategoria().getNome()
        );

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade(),
                categoriaDTO
        );
    }
}


package com.product.api.gitmarket.domain.produto.dto;

import com.product.api.gitmarket.domain.categoria.Categoria;
import com.product.api.gitmarket.domain.produto.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoRequestDTO(
        @NotNull
        @NotBlank
        String nome,

        @NotNull
        BigDecimal preco,

        @NotNull
        int quantidade,

        @NotNull
        UUID categoriaId

) {
    public Produto toEntity(Categoria categoriaEncontrada) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setQuantidade(quantidade);
        produto.setCategoria(categoriaEncontrada);
        return produto;
    }
}
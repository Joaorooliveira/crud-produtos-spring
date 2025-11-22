package com.product.api.crud_produtos.dto.request;

import com.product.api.crud_produtos.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequestDTO(
        @NotNull
        @NotBlank
        String nome,

        @NotNull
        float preco,

        @NotNull
        int quantidade


){ public Produto toEntity() {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setQuantidade(quantidade);
        return produto;
}}

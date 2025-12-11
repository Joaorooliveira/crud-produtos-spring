package com.product.api.gitmarket.domain.categoria.dto;


import com.product.api.gitmarket.domain.categoria.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaRequestDTO(

        @NotNull
        @NotBlank
        String nome
) {
    public Categoria toEntity() {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        return categoria;
    }
}
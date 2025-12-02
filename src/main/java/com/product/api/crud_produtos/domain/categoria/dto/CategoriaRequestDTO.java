package com.product.api.crud_produtos.domain.categoria.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.product.api.crud_produtos.domain.categoria.Categoria}
 */
public record CategoriaResponseDTO(@NotNull UUID id, String nome) implements Serializable {
}
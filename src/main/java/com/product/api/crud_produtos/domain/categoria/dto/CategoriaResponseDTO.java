package com.product.api.crud_produtos.domain.categoria;


import java.util.UUID;

public record CategoriaResponseDTO(
        UUID id,
        String nome)
{
}
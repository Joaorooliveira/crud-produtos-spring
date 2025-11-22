package com.product.api.crud_produtos.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(

        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}

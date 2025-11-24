package com.product.api.crud_produtos.dto.response;

import com.product.api.crud_produtos.entity.Usuario;

public record UsuarioResponseDTO(


        Long id,
        String login
) {
    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getId(), usuario.getLogin());
    }
}

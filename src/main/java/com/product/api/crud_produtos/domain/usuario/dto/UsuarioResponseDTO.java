package com.product.api.crud_produtos.domain.usuario.dto;

import com.product.api.crud_produtos.domain.usuario.Usuario;

public record UsuarioResponseDTO(


        Long id,
        String login
) {
    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getId(), usuario.getLogin());
    }
}

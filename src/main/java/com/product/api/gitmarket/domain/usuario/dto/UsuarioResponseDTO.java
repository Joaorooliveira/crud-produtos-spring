package com.product.api.gitmarket.domain.usuario.dto;

import com.product.api.gitmarket.domain.usuario.Usuario;

public record UsuarioResponseDTO(


        Long id,
        String login
) {
    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getId(), usuario.getLogin());
    }
}

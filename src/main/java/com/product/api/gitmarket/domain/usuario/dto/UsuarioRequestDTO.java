package com.product.api.gitmarket.domain.usuario.dto;

import com.product.api.gitmarket.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(

        @NotBlank
        String login,
        @NotBlank
        String senha
) {
    public Usuario toEntity(String login, String senha) {
        var usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha(senha);
        return usuario;
    }
}

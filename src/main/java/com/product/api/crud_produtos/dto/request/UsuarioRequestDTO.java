package com.product.api.crud_produtos.dto.request;

import com.product.api.crud_produtos.entity.Usuario;
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

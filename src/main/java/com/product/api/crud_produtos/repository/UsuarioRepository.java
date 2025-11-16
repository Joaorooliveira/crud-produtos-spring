package com.product.api.crud_produtos.repository;

import com.product.api.crud_produtos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
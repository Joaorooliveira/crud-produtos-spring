package com.product.api.crud_produtos.service;

import com.product.api.crud_produtos.dto.response.UsuarioResponseDTO;
import com.product.api.crud_produtos.entity.Usuario;
import com.product.api.crud_produtos.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }


    public Page<UsuarioResponseDTO> listarUsuarios (Pageable pageable) {
        System.out.println("Testando");
        Page<Usuario> listaPaginada= repository.findAll(pageable);
        return listaPaginada.map(UsuarioResponseDTO::fromEntity);
    }

    public Optional<Usuario> buscarUsuarioPorId (Long id) {
        return repository.findById(id);
    }

}

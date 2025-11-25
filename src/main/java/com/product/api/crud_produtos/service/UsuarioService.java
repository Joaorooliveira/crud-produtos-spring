package com.product.api.crud_produtos.service;

import com.product.api.crud_produtos.dto.request.UsuarioAtualizarRequestDTO;
import com.product.api.crud_produtos.dto.response.ProdutoResponseDTO;
import com.product.api.crud_produtos.dto.response.UsuarioResponseDTO;
import com.product.api.crud_produtos.entity.Usuario;
import com.product.api.crud_produtos.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    public UsuarioResponseDTO atualizarUsuarioPorId  (Long id, UsuarioAtualizarRequestDTO dto ) {
        var UsuarioEntity = buscarUsuarioPorId(id).orElseThrow(()-> new EntityNotFoundException("Usuario nao encontrado " +
                "com o ID:"+id));

        var usuario = Usuario.builder()
                .id(UsuarioEntity.getId())
                .login(dto.login()!=null?dto.login():UsuarioEntity.getLogin())
                .senha(dto.senha()!=null?dto.senha():UsuarioEntity.getSenha())
                .build();
        repository.saveAndFlush(usuario);
        return UsuarioResponseDTO.fromEntity(usuario);
    }

}

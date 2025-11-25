package com.product.api.crud_produtos.service;

import com.product.api.crud_produtos.dto.request.UsuarioAtualizarRequestDTO;
import com.product.api.crud_produtos.dto.response.UsuarioResponseDTO;
import com.product.api.crud_produtos.entity.Usuario;
import com.product.api.crud_produtos.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository repository;

    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository repository) {
        this.passwordEncoder = passwordEncoder;
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

    @Transactional
    public UsuarioResponseDTO atualizarUsuarioPorId  (Long id, UsuarioAtualizarRequestDTO dto ) {
        var UsuarioEntity = buscarUsuarioPorId(id).orElseThrow(()-> new EntityNotFoundException("Usuario nao encontrado " +
                "com o ID:"+id));

        var usuario = Usuario.builder()
                .id(UsuarioEntity.getId())
                .login(dto.login()!=null?dto.login():UsuarioEntity.getLogin())
                .senha(dto.senha()!=null?passwordEncoder.encode(dto.senha()):UsuarioEntity.getSenha())
                .build();
        repository.saveAndFlush(usuario);
        return UsuarioResponseDTO.fromEntity(usuario);
    }

    @Transactional
    public void excluirUsuarioPorID (Long id) {
        var UsuarioEntity = buscarUsuarioPorId(id).orElseThrow(()-> new EntityNotFoundException("Usuario nao encontrado " +
                "com o ID:"+id));

        repository.delete(UsuarioEntity);
    }

}

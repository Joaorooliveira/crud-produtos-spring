package com.product.api.crud_produtos.controller;

import com.product.api.crud_produtos.dto.request.UsuarioAtualizarRequestDTO;
import com.product.api.crud_produtos.dto.response.UsuarioResponseDTO;
import com.product.api.crud_produtos.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public Page<UsuarioResponseDTO> listarUsuarios (Pageable pageable) {
        return service.listarUsuarios(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId (@PathVariable Long id) {
        return service.buscarUsuarioPorId(id)
                .map(usuario -> ResponseEntity.ok(UsuarioResponseDTO.fromEntity(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuarioPorId (@PathVariable Long id,
                                                                     @RequestBody UsuarioAtualizarRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarUsuarioPorId(id, dto));
    }
}

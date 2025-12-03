package com.product.api.crud_produtos.domain.usuario;

import com.product.api.crud_produtos.domain.usuario.dto.UsuarioAtualizarRequestDTO;
import com.product.api.crud_produtos.domain.usuario.dto.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/usuarios")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Usuarios", description = "Gerenciador de Usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar Usuarios", description = "Lista todos os usuarios")
    public Page<UsuarioResponseDTO> listarUsuarios(Pageable pageable) {
        return service.listarUsuarios(pageable);
    }

    @GetMapping("{id}")
    @Operation(summary = "Listar Usuario", description = "Lista Usuario especifico pelo ID")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id) {
        return service.buscarUsuarioPorId(id)
                .map(usuario -> ResponseEntity.ok(UsuarioResponseDTO.fromEntity(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("{id}")
    @Operation(summary = "Atualizar Usuario", description = "Atualiza Usuario especifico pelo ID")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuarioPorId(@PathVariable Long id,
                                                                    @RequestBody UsuarioAtualizarRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarUsuarioPorId(id, dto));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Excluir Usuario", description = "Excluir Usuario especifico pelo ID informado")
    public ResponseEntity excluirUsuarioPorId(@PathVariable Long id) {
        service.excluirUsuarioPorID(id);
        return ResponseEntity.noContent().build();
    }
}

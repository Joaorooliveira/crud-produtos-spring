package com.product.api.crud_produtos.domain.categoria;

import com.product.api.crud_produtos.domain.categoria.dto.CategoriaRequestDTO;
import com.product.api.crud_produtos.domain.categoria.dto.CategoriaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criarCategoria(@RequestBody @Valid CategoriaRequestDTO dto, UriComponentsBuilder builder) {
        var categoria = service.criarCategoria(dto);
        var uri = builder
                .path("/categorias/{id}")
                .buildAndExpand(categoria.id())
                .toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @GetMapping
    public Page<CategoriaResponseDTO> listarCategorias(Pageable pageable) {
        return service.listarCategorias(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(categoria -> ResponseEntity.ok(CategoriaResponseDTO.fromEntity(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizarCategoria(@PathVariable UUID id, @RequestBody
    CategoriaRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarCategoria(id, dto));
    }
}

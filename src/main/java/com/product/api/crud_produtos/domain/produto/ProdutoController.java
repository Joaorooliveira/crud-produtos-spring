package com.product.api.crud_produtos.domain.produto;

import com.product.api.crud_produtos.domain.produto.dto.ProdutoAtualizarRequestDTO;
import com.product.api.crud_produtos.domain.produto.dto.ProdutoRequestDTO;
import com.product.api.crud_produtos.domain.produto.dto.ProdutoResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {


    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody @Valid ProdutoRequestDTO dto,
                                                           UriComponentsBuilder builder) {
        var produto = service.criarProduto(dto);
        var uri = builder
                .path("/produtos/{id}")
                .buildAndExpand(produto.id())
                .toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoResponseDTO>> listarProdutos(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(required = false) UUID categoriaId) {
        return ResponseEntity.ok(service.listarProdutos(pageable, categoriaId));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(produto -> ResponseEntity.ok(ProdutoResponseDTO.fromEntity(produto))) // converte entity para DTO
                .orElse(ResponseEntity.notFound().build()); // se não achar → 404
    }

    @PatchMapping("{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable UUID id, @RequestBody
    ProdutoAtualizarRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarProduto(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID id) {
        service.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}







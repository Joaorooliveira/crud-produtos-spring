package com.product.api.crud_produtos.controller;

import com.product.api.crud_produtos.dto.ProdutoAtualizarRequestDto;
import com.product.api.crud_produtos.dto.ProdutoRequestDTO;
import com.product.api.crud_produtos.dto.ProdutoResponseDTO;
import com.product.api.crud_produtos.entity.Produto;
import com.product.api.crud_produtos.repository.ProdutosRepository;
import com.product.api.crud_produtos.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")// rota base
@RequiredArgsConstructor // Dependencia Lombok
public class ProdutoController {


    private final ProdutoService  service;
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody @Valid ProdutoRequestDTO dto){
        Produto produto = service.criarProduto(dto.toEntity());
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public Page<ProdutoResponseDTO> listarProdutos(Pageable pageable) {
         return service.listarProdutos(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable UUID id){
        return service.buscarPorId(id)
                .map(produto -> ResponseEntity.ok(ProdutoResponseDTO.fromEntity(produto))) // converte entity para DTO
                .orElse(ResponseEntity.notFound().build()); // se não achar → 404
    }

    @PatchMapping("{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable UUID id, @RequestBody
     ProdutoAtualizarRequestDto dto) {
        return ResponseEntity.ok(service.atualizarProduto(id,dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID id){
        service.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

}







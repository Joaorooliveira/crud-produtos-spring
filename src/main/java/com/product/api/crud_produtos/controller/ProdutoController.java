package com.product.api.crud_produtos.controller;

import com.product.api.crud_produtos.dto.ProdutoRequestDTO;
import com.product.api.crud_produtos.dto.ProdutoResponseDTO;
import com.product.api.crud_produtos.entities.Produto;
import com.product.api.crud_produtos.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")// rota base
@RequiredArgsConstructor // Dependencia Lombok
public class ProdutoController {


    private final ProdutoService  service;

    @PostMapping
    public ResponseEntity<Void> criarProduto(@RequestBody @Valid ProdutoRequestDTO dto){
        service.criarProduto(dto.toEntity());
        return ResponseEntity.created(URI.create("/api/produtos")).build();
    }

    @GetMapping
    public List<ProdutoResponseDTO> listarProdutos() {

        return service.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable UUID id){
        return service.buscarPorId(id)
                .map(produto -> ResponseEntity.ok(ProdutoResponseDTO.fromEntity(produto))) // converte entity para DTO
                .orElse(ResponseEntity.notFound().build()); // se não achar → 404
    }
}







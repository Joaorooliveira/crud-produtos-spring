package com.product.api.crud_produtos.controller;

import com.product.api.crud_produtos.dto.ProdutoRequestDTO;
import com.product.api.crud_produtos.dto.ProdutoResponseDTO;
import com.product.api.crud_produtos.entities.Produto;
import com.product.api.crud_produtos.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")// rota base
@RequiredArgsConstructor // Dependencia Lombok
public class ProdutoController {


    private final ProdutoService  service;

    @PostMapping("/create")
    public ResponseEntity<Produto> criarProduto(@RequestBody @Valid ProdutoRequestDTO dto){
        Produto produto = service.criarProduto(dto.toEntity());
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/list")
    public List<ProdutoResponseDTO> listarProdutos() {

        return service.listarProdutos();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable UUID id){
        return service.buscarPorId(id)
                .map(produto -> ResponseEntity.ok(ProdutoResponseDTO.fromEntity(produto))) // converte entity para DTO
                .orElse(ResponseEntity.notFound().build()); // se não achar → 404
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(
            @PathVariable UUID id,
            @RequestBody @Valid ProdutoRequestDTO dto) {

        Produto produtoAtualizado = service.atualizarProduto(
                id,
                dto.nome(),
                dto.preco(),
                dto.quantidade()
        );

        return ResponseEntity.ok(ProdutoResponseDTO.fromEntity(produtoAtualizado));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID id){
        service.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

}







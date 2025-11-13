package com.product.api.crud_produtos.service;

import com.product.api.crud_produtos.dto.ProdutoAtualizarRequestDto;
import com.product.api.crud_produtos.dto.ProdutoResponseDTO;
import com.product.api.crud_produtos.entity.Produto;
import com.product.api.crud_produtos.repository.ProdutosRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutosRepository repository;

    @Transactional
    public Produto criarProduto(Produto produto){

        repository.saveAndFlush(produto);
        return produto;
    }

    public Page<ProdutoResponseDTO> listarProdutos(Pageable  pageable) {
        Page<Produto> listaPaginada= repository.findAll(pageable);
        Page<ProdutoResponseDTO> paginaDeDTOs = listaPaginada.map(produto -> ProdutoResponseDTO.fromEntity(produto));
        return paginaDeDTOs;
        //return repository.findAll(pageable);
//        return produtos.stream()
//                .map(produto -> new ProdutoResponseDTO(
//                        produto.getNome(),
//                        produto.getPreco(),
//                        produto.getQuantidade()
//                ))
//                .toList();
    }

    public Optional<Produto> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public Produto atualizarProduto(UUID id, ProdutoAtualizarRequestDto dto) {
        var produtoEntity = repository.findById(id).orElseThrow(()-> new RuntimeException("Produto nao encontrado"));
        Produto produto = Produto.builder()
                .id(produtoEntity.getId())
                .nome(dto.nome()!=null?dto.nome():produtoEntity.getNome())
                .preco(dto.preco()!=null?dto.preco():produtoEntity.getPreco())
                .quantidade(dto.quantidade()!=null?dto.quantidade():produtoEntity.getQuantidade())
                .build();
        return repository.saveAndFlush(produto);

    }

    @Transactional
    public void deletarProduto(UUID id) {
        var produto = buscarPorId(id).orElseThrow(()-> new EntityNotFoundException("Produto nao encontrado com o ID: "
                +id));
        repository.delete(produto);
    }


}

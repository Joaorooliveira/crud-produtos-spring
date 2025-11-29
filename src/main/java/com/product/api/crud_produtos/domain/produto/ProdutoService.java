package com.product.api.crud_produtos.domain.produto;

import com.product.api.crud_produtos.domain.produto.dto.ProdutoAtualizarRequestDto;
import com.product.api.crud_produtos.domain.produto.dto.ProdutoRequestDTO;
import com.product.api.crud_produtos.domain.produto.dto.ProdutoResponseDTO;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {


    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto){

        var produto = repository.saveAndFlush(dto.toEntity());
        return ProdutoResponseDTO.fromEntity(produto);
    }

    public Page<ProdutoResponseDTO> listarProdutos(Pageable  pageable) {
        Page<Produto> listaPaginada= repository.findAll(pageable);
        return listaPaginada.map(ProdutoResponseDTO::fromEntity);
    }

    public Optional<Produto> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    @Transactional
    public ProdutoResponseDTO atualizarProduto(UUID id, ProdutoAtualizarRequestDto dto) {
        var produtoEntity = repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Produto nao encontrado com o ID: "
                +id));
        Produto produto = Produto.builder()
                .id(produtoEntity.getId())
                .nome(dto.nome()!=null?dto.nome():produtoEntity.getNome())
                .preco(dto.preco()!=null?dto.preco():produtoEntity.getPreco())
                .quantidade(dto.quantidade()!=null?dto.quantidade():produtoEntity.getQuantidade())
                .build();
        repository.saveAndFlush(produto);
        return ProdutoResponseDTO.fromEntity(produto);
    }

    @Transactional
    public void deletarProduto(UUID id) {
        var produto = buscarPorId(id).orElseThrow(()-> new EntityNotFoundException("Produto nao encontrado com o ID: "
                +id));
        repository.delete(produto);
    }


}

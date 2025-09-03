package com.product.api.crud_produtos.service;

import com.product.api.crud_produtos.dto.ProdutoResponseDTO;
import com.product.api.crud_produtos.entities.Produto;
import com.product.api.crud_produtos.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutosRepository repository;


    public void criarProduto(Produto produto){

        repository.saveAndFlush(produto);
    }

    public List<ProdutoResponseDTO> listarProdutos() {
        List<Produto> produtos = repository.findAll();
        return produtos.stream()
                .map(produto -> new ProdutoResponseDTO(
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidade()
                ))
                .toList();
    }

    public Optional<Produto> buscarPorId(UUID id) {
        return repository.findById(id);
    }





}

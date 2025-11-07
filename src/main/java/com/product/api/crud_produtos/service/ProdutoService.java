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


    public Produto criarProduto(Produto produto){

        repository.saveAndFlush(produto);
        return produto;
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

    public Produto atualizarProduto(UUID id, String nome, float preco, Integer quantidade) {
        return repository.findById(id)
                .map(produto -> {
                    produto.setNome(nome);
                    produto.setPreco(preco);
                    produto.setQuantidade(quantidade);
                    return repository.save(produto);
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
    }




}

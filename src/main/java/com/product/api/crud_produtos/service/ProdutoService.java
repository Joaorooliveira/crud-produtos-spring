package com.product.api.crud_produtos.service;

import com.product.api.crud_produtos.dto.ProdutoResponseDTO;
import com.product.api.crud_produtos.entities.Produto;
import com.product.api.crud_produtos.repository.ProdutosRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional
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

    @Transactional
    public void deletarProduto(UUID id) {
        var produto = buscarPorId(id).orElseThrow(()-> new EntityNotFoundException("Produto nao encontrado com o ID: "
                +id));
        repository.delete(produto);
    }


}

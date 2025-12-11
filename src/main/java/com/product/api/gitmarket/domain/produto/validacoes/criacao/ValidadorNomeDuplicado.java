package com.product.api.gitmarket.domain.produto.validacoes.criacao;

import com.product.api.gitmarket.domain.produto.ProdutoRepository;
import com.product.api.gitmarket.domain.produto.dto.ProdutoRequestDTO;
import com.product.api.gitmarket.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorNomeDuplicado implements ValidadorProduto {

    private final ProdutoRepository produtoRepository;

    public ValidadorNomeDuplicado(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void validar(ProdutoRequestDTO dados) {

        if (produtoRepository.existsByNome(dados.nome())) {
            throw new ValidacaoException("Já existe um produto com este nome!");
        }
    }
}

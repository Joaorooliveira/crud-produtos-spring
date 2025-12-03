package com.product.api.crud_produtos.domain.produto.validacoes;

import com.product.api.crud_produtos.domain.produto.ProdutoRepository;
import com.product.api.crud_produtos.domain.produto.dto.ProdutoRequestDTO;
import com.product.api.crud_produtos.infra.exception.ValidacaoException;
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

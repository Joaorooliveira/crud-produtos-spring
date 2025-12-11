package com.product.api.gitmarket.domain.produto.validacoes.criacao;

import com.product.api.gitmarket.domain.categoria.CategoriaRepository;
import com.product.api.gitmarket.domain.produto.dto.ProdutoRequestDTO;
import com.product.api.gitmarket.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCategoriaInativa implements ValidadorProduto {

    private final CategoriaRepository categoriaRepository;

    public ValidadorCategoriaInativa(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void validar(ProdutoRequestDTO dados) {
        if (!categoriaRepository.existsById(dados.categoriaId())) {
            throw new ValidacaoException("Categoria não encontrada ou inativa!");
        }
    }
}

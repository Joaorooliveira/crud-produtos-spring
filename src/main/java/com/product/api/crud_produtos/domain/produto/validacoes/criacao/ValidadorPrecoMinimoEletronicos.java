package com.product.api.crud_produtos.domain.produto.validacoes;

import com.product.api.crud_produtos.domain.categoria.CategoriaRepository;
import com.product.api.crud_produtos.domain.produto.dto.ProdutoRequestDTO;
import com.product.api.crud_produtos.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPrecoMinimoEletronicos implements ValidadorProduto {

    private final CategoriaRepository categoriaRepository;

    public ValidadorPrecoMinimoEletronicos(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void validar(ProdutoRequestDTO dados) {
        var categoria = categoriaRepository.findById(dados.categoriaId()).orElse(null);

        if (categoria != null
                && categoria.getNome().equalsIgnoreCase("Eletrônicos")
                && dados.preco() < 50.0) {

            throw new ValidacaoException("Produtos da categoria Eletrônicos não podem custar menos de R$ 20,00");
        }
    }
}

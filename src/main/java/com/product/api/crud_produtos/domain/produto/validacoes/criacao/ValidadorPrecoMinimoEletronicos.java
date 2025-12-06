package com.product.api.crud_produtos.domain.produto.validacoes.criacao;

import com.product.api.crud_produtos.domain.categoria.CategoriaRepository;
import com.product.api.crud_produtos.domain.produto.dto.ProdutoRequestDTO;
import com.product.api.crud_produtos.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidadorPrecoMinimoEletronicos implements ValidadorProduto {

    private final CategoriaRepository categoriaRepository;

    public ValidadorPrecoMinimoEletronicos(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void validar(ProdutoRequestDTO dados) {
        var categoria = categoriaRepository.findById(dados.categoriaId()).orElse(null);

        BigDecimal precoMinimo = new BigDecimal("20.0");

        if (categoria != null
                && categoria.getNome().equalsIgnoreCase("Eletrônicos")
                && dados.preco().compareTo(precoMinimo) < 0) { // < 0 significa "menor que"

            throw new ValidacaoException(
                    "Produtos da categoria Eletrônicos não podem custar menos de R$ " + precoMinimo
            );
        }
    }
}
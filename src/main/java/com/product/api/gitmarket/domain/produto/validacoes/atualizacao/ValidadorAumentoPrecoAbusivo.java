package com.product.api.gitmarket.domain.produto.validacoes.atualizacao;

import com.product.api.gitmarket.domain.produto.ProdutoRepository;
import com.product.api.gitmarket.domain.produto.dto.ProdutoAtualizarRequestDTO;
import com.product.api.gitmarket.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ValidadorAumentoPrecoAbusivo implements ValidadorAtualizacaoProduto {

    private final ProdutoRepository produtoRepository;

    public ValidadorAumentoPrecoAbusivo(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void validar(UUID id, ProdutoAtualizarRequestDTO dados) {
        if (dados.preco() == null) {
            return;
        }

        var produtoNoBanco = produtoRepository.findById(id).orElse(null);

        if (produtoNoBanco == null) {
            return;
        }

        BigDecimal precoAntigo = produtoNoBanco.getPreco();
        BigDecimal precoNovo = dados.preco();
        BigDecimal limiteMaximo = precoAntigo.multiply(new BigDecimal("1.5"));
        limiteMaximo = limiteMaximo.setScale(2, java.math.RoundingMode.HALF_UP);

        if (precoNovo.compareTo(limiteMaximo) > 0) {
            throw new ValidacaoException(
                    "Aumento abusivo! O preço não pode subir mais que 50% de uma vez. " +
                            "Máximo permitido: " + limiteMaximo
            );
        }
    }
}
package com.product.api.gitmarket.domain.produto.validacoes.atualizacao;

import com.product.api.gitmarket.domain.produto.ProdutoRepository;
import com.product.api.gitmarket.domain.produto.dto.ProdutoAtualizarRequestDTO;
import com.product.api.gitmarket.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ValidadorReducaoDrasticaPreco implements ValidadorAtualizacaoProduto {

    private final ProdutoRepository produtoRepository;

    public ValidadorReducaoDrasticaPreco(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void validar(UUID id, ProdutoAtualizarRequestDTO dados) {
        if (dados.preco() == null) return;

        var produtoAtual = produtoRepository.findById(id).orElse(null);
        if (produtoAtual == null) return;

        BigDecimal precoAntigo = produtoAtual.getPreco();
        BigDecimal novoPreco = dados.preco();

        BigDecimal limiteMinimo = precoAntigo.multiply(new BigDecimal("0.10"));

        if (novoPreco.compareTo(limiteMinimo) < 0) {
            throw new ValidacaoException(
                    "Erro de segurança: Não é permitido reduzir o preço em mais de 90% de uma vez. " +
                            "O menor valor permitido seria: " + limiteMinimo
            );
        }
    }
}
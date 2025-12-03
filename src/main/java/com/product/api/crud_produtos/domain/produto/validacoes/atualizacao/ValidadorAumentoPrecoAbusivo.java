package com.product.api.crud_produtos.domain.produto.validacoes;

import com.product.api.crud_produtos.domain.produto.ProdutoRepository;
import com.product.api.crud_produtos.domain.produto.dto.ProdutoAtualizarRequestDTO;
import com.product.api.crud_produtos.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

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

        double precoAntigo = produtoNoBanco.getPreco();
        double precoNovo = dados.preco();
        double limiteMaximo = precoAntigo * 1.5;

        if (precoNovo > limiteMaximo) {
            throw new ValidacaoException(
                    "Aumento abusivo! O preço não pode subir mais que 50% de uma vez. " +
                            "Máximo permitido: " + limiteMaximo
            );
        }
    }
}
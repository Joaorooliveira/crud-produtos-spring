package com.product.api.crud_produtos.domain.produto.validacoes.atualizacao;

import com.product.api.crud_produtos.domain.produto.ProdutoRepository;
import com.product.api.crud_produtos.domain.produto.dto.ProdutoAtualizarRequestDTO;
import com.product.api.crud_produtos.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

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

        double precoAntigo = produtoAtual.getPreco();
        double novoPreco = dados.preco();

        if (novoPreco < (precoAntigo * 0.10)) {
            throw new ValidacaoException(
                    "Erro de segurança: Não é permitido reduzir o preço em mais de 90% de uma vez."
            );
        }
    }
}

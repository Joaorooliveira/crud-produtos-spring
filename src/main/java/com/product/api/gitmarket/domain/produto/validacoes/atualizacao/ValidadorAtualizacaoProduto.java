package com.product.api.gitmarket.domain.produto.validacoes.atualizacao;

import com.product.api.gitmarket.domain.produto.dto.ProdutoAtualizarRequestDTO;

import java.util.UUID;

public interface ValidadorAtualizacaoProduto {
    void validar(UUID id, ProdutoAtualizarRequestDTO dados);
}

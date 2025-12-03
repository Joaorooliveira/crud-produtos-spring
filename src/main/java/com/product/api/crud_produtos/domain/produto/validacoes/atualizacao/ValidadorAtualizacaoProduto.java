package com.product.api.crud_produtos.domain.produto.validacoes;

import com.product.api.crud_produtos.domain.produto.dto.ProdutoAtualizarRequestDTO;

import java.util.UUID;

public interface ValidadorAtualizacaoProduto {
    void validar(UUID id, ProdutoAtualizarRequestDTO dados);
}

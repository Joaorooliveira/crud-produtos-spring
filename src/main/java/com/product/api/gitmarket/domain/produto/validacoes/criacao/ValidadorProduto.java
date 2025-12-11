package com.product.api.gitmarket.domain.produto.validacoes.criacao;

import com.product.api.gitmarket.domain.produto.dto.ProdutoRequestDTO;

public interface ValidadorProduto {

    void validar(ProdutoRequestDTO dados);
}

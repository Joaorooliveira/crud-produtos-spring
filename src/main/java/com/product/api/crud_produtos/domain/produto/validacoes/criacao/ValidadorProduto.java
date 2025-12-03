package com.product.api.crud_produtos.domain.produto.validacoes.criacao;

import com.product.api.crud_produtos.domain.produto.dto.ProdutoRequestDTO;

public interface ValidadorProduto {

    void validar(ProdutoRequestDTO dados);
}

package com.product.api.crud_produtos.domain.produto.validacoes.atualizacao;

import com.product.api.crud_produtos.domain.categoria.CategoriaRepository;
import com.product.api.crud_produtos.domain.produto.dto.ProdutoAtualizarRequestDTO;
import com.product.api.crud_produtos.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ValidadorTrocaCategoria implements ValidadorAtualizacaoProduto {

    private final CategoriaRepository categoriaRepository;

    public ValidadorTrocaCategoria(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void validar(UUID id, ProdutoAtualizarRequestDTO dados) {
        if (dados.categoriaId() == null) return;

        if (!categoriaRepository.existsById(dados.categoriaId())) {
            throw new ValidacaoException("A nova categoria selecionada não existe ou está inativa.");
        }
    }
}

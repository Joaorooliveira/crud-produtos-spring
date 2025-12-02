package com.product.api.crud_produtos.domain.produto;

import com.product.api.crud_produtos.domain.categoria.CategoriaRepository;
import com.product.api.crud_produtos.domain.produto.dto.ProdutoAtualizarRequestDTO;
import com.product.api.crud_produtos.domain.produto.dto.ProdutoRequestDTO;
import com.product.api.crud_produtos.domain.produto.dto.ProdutoResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {


    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto) {

        var categoria = categoriaRepository.findById(dto.categoriaId()).orElseThrow(() -> new EntityNotFoundException(
                "Categoria nao encontrado com o ID: "
                        + dto.categoriaId()));

        var produto = produtoRepository.saveAndFlush(dto.toEntity(categoria));
        return ProdutoResponseDTO.fromEntity(produto);
    }

    public Page<ProdutoResponseDTO> listarProdutos(Pageable pageable, UUID categoriaId) {
        if (categoriaId != null) {
            if (!categoriaRepository.existsById(categoriaId)) {
                throw new EntityNotFoundException("Categoria não encontrada com o ID: " + categoriaId);
            }
            return produtoRepository.findByCategoriaId(categoriaId, pageable)
                    .map(ProdutoResponseDTO::fromEntity);
        }
        return produtoRepository.findAll(pageable)
                .map(ProdutoResponseDTO::fromEntity);
    }

    public Optional<Produto> buscarPorId(UUID id) {
        return produtoRepository.findById(id);
    }

    @Transactional
    public ProdutoResponseDTO atualizarProduto(UUID id, ProdutoAtualizarRequestDTO dto) {
        var produtoEntity = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + id));

        if (dto.nome() != null) {
            produtoEntity.setNome(dto.nome());
        }
        if (dto.preco() != null) {
            produtoEntity.setPreco(dto.preco());
        }
        if (dto.quantidade() != null) {
            produtoEntity.setQuantidade(dto.quantidade());
        }

        if (dto.categoriaId() != null) {
            var novaCategoria = categoriaRepository.findById(dto.categoriaId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " +
                            dto.categoriaId()));

            produtoEntity.setCategoria(novaCategoria);
        }

        produtoRepository.save(produtoEntity);

        return ProdutoResponseDTO.fromEntity(produtoEntity);
    }

    @Transactional
    public void deletarProduto(UUID id) {
        var produto = buscarPorId(id).orElseThrow(() -> new EntityNotFoundException("Produto nao encontrado com o ID: "
                + id));
        produtoRepository.delete(produto);
    }

    public Page<ProdutoResponseDTO> listarProdutosPorCategoria(Pageable pageable, UUID categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new EntityNotFoundException("Categoria não encontrada com o ID: " + categoriaId);
        }
        var produtosPaginados = produtoRepository.findByCategoriaId(categoriaId, pageable);
        return produtosPaginados.map(ProdutoResponseDTO::fromEntity);
    }

}

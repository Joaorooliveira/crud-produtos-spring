package com.product.api.crud_produtos.domain.categoria;

import com.product.api.crud_produtos.domain.categoria.dto.CategoriaRequestDTO;
import com.product.api.crud_produtos.domain.categoria.dto.CategoriaResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public CategoriaResponseDTO criarCategoria(CategoriaRequestDTO dto) {
        var categoria = categoriaRepository.save(dto.toEntity());
        return CategoriaResponseDTO.fromEntity(categoria);
    }

    public Page<CategoriaResponseDTO> listarCategorias(Pageable pageable) {
        Page<Categoria> listaPaginada = categoriaRepository.findAll(pageable);
        return listaPaginada.map(CategoriaResponseDTO::fromEntity);
    }

    public Optional<Categoria> buscarPorId(UUID id) {
        return categoriaRepository.findById(id);
    }

    @Transactional
    public CategoriaResponseDTO atualizarCategoria(UUID id, CategoriaRequestDTO dto) {
        var categoriaEntity = categoriaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Categoria nao encontrada com ID:" + id));

        if (dto.nome() != null) {
            categoriaEntity.setNome(dto.nome());
        }
        categoriaRepository.save(categoriaEntity);
        return CategoriaResponseDTO.fromEntity(categoriaEntity);
    }

    @Transactional
    public void deletarCategoria(UUID id) {
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + id));
        categoriaRepository.delete(categoria);
    }
}

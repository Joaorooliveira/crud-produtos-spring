package com.product.api.gitmarket.domain.produto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    Page<Produto> findByCategoriaId(UUID categoriaId, Pageable pageable);

    boolean existsByNome(String nome);
}

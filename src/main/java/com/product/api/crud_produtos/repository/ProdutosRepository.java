package com.product.api.crud_produtos.repository;


import com.product.api.crud_produtos.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutosRepository extends JpaRepository <Produto, UUID>{
}

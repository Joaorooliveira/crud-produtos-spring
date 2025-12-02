package com.product.api.crud_produtos.domain.categoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

}

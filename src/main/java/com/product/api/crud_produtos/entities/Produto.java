package com.product.api.crud_produtos.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@Entity
@Table(name ="Produtos")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private float preco;
    @Column(nullable = false)
    private int quantidade;

}

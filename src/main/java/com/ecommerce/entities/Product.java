package com.ecommerce.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity // Indica uma classe sendo entidade JPA
@Table(name = "products") // define o nome da tabela no banco de dados
@Data // gera getters, setters, toString, equals e hashCode
@AllArgsConstructor // cria construtor com todos os campos
@NoArgsConstructor // construtor vazio
@Builder //permite criar objetos
public class Product {

    @Id // define como chave primaria no bd
    @GeneratedValue(strategy = GenerationType.IDENTITY) // valor do id gerado automaticamente, auto-incremento
    private Long id;

    @Column(nullable = false) // coluna obrigatória, não aceita nulo
    private String name;

    private String description;

    @Column(nullable = false, precision = 19, scale = 2) // coluna obrigatória, coom 2 casas decimais e 19 digitos
    private BigDecimal price;

    private Integer stock;


}

package com.ecommerce.repositories;

import com.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository traz implementações prontas para CRUD
    // @Repository marca a interface como componente de persistência

    // busca por nome (para endpoint de list/search)
    /*
      Busca produtos cujo nome contenha a string passada (case-insensitive).
      Utiliza paginação via Pageable (page, size, sort).

      Equivalente JPQL: SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
     */
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}

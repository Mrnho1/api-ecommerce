package com.ecommerce.repositories;

import com.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // busca por nome (para endpoint de list/search)
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}

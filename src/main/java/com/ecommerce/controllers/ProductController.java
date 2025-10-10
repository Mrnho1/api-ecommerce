package com.ecommerce.controllers;

import com.ecommerce.dto.ProductInputDTO;
import com.ecommerce.dto.ProductOutputDTO;
import com.ecommerce.services.ProductService;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;

@RestController // retorna json ou outro body
@RequestMapping("/api/products") // caminho base para todos os endpoints
@Validated // ativa suporte para validação
public class ProductController {
    // Injeção via construtor
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ProductOutputDTO> createProduct(@Valid @RequestBody ProductInputDTO dto) {
        ProductOutputDTO created = productService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.id()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    // LIST + search?q=... + pagination params ?page=0&size=10&sort=price,asc
    @GetMapping
    public Page<ProductOutputDTO> listProducts(
            @RequestParam(required = false) String q,
            Pageable pageable
    ) {
        return productService.list(q, pageable);
    }

    // GET by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductOutputDTO> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ProductOutputDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductInputDTO dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

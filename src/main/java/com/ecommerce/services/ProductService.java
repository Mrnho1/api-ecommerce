package com.ecommerce.services;

import com.ecommerce.dto.ProductInputDTO;
import com.ecommerce.dto.ProductOutputDTO;
import com.ecommerce.entities.Product;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.repositories.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional(readOnly = true)
    public Page<ProductOutputDTO> list(String q, Pageable pageable) {
        Page<Product> page;
        if (q == null || q.isBlank()) {
            page = productRepository.findAll(pageable);
        } else {
            page = productRepository.findByNameContainingIgnoreCase(q, pageable);
        }
        return page.map(productMapper::toDTO);
    }

    @Transactional
    public ProductOutputDTO create(ProductInputDTO dto) {
        Product product = productMapper.toEntity(dto);
        Product saved = productRepository.save(product);
        return productMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public ProductOutputDTO getById(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return productMapper.toDTO(p);
    }

    @Transactional
    public ProductOutputDTO update(Long id, ProductInputDTO dto) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        productMapper.updateEntityFromDto(dto, p);
        Product saved = productRepository.save(p);
        return productMapper.toDTO(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }
}

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


//Classe de serviço
@Service
public class ProductService {
    //Injeção de dependências
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    //acesso ao banco de dados
    //conversão entre DTOs e entidades
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    //Lista produtos por nome, transação apenas para leitura, + segurança e performance
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
    //cria um novo produto, rollback em caso de erro
    @Transactional
    public ProductOutputDTO create(ProductInputDTO dto) {
        Product product = productMapper.toEntity(dto);
        Product saved = productRepository.save(product);
        return productMapper.toDTO(saved);
    }
    //busca produto por id, retorna exceção caso não encontrada
    @Transactional(readOnly = true)
    public ProductOutputDTO getById(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return productMapper.toDTO(p);
    }
    //atualiza um produto existente
    @Transactional
    public ProductOutputDTO update(Long id, ProductInputDTO dto) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        productMapper.updateEntityFromDto(dto, p);
        Product saved = productRepository.save(p);
        return productMapper.toDTO(saved);
    }
    //exclui um produto por ID
    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }
}

package com.ecommerce.dto;

import java.math.BigDecimal;

// DTO de saída
public record ProductOutputDTO(Long id, String name, String description, BigDecimal price, Integer stock) {}

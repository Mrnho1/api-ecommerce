package com.ecommerce.dto;

import java.math.BigDecimal;

public record ProductOutputDTO(Long id, String name, String description, BigDecimal price, Integer stock) {}

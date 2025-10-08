package com.ecommerce.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

// record imutável para representar dados, gera getters, setters ... automaticamente
// DTO de entrada
/*
    Dados:
        name: obrigatório
        descripition: opcional
        price: obrigatório maior ou igual a zero
        stock: obrigatório maior ou igual a zero
 */
public record ProductInputDTO(
        @NotBlank(message = "name is required") String name,
        String description,
        @NotNull(message = "price is required") @DecimalMin(value = "0.0", inclusive = true, message = "price must be >= 0") BigDecimal price,
        @NotNull(message = "stock is required") @Min(value = 0, message = "stock must be >= 0") Integer stock
) {}

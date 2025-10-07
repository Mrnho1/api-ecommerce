package com.ecommerce.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductInputDTO(
        @NotBlank(message = "name is required") String name,
        String description,
        @NotNull(message = "price is required") @DecimalMin(value = "0.0", inclusive = true, message = "price must be >= 0") BigDecimal price,
        @NotNull(message = "stock is required") @Min(value = 0, message = "stock must be >= 0") Integer stock
) {}

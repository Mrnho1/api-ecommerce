package com.ecommerce.mapper;

import com.ecommerce.dto.*;
import com.ecommerce.entities.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductInputDTO dto);

    ProductOutputDTO toDTO(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProductInputDTO dto, @MappingTarget Product product);
}

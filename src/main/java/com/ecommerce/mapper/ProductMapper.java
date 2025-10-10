package com.ecommerce.mapper;

import com.ecommerce.dto.*;
import com.ecommerce.entities.Product;
import org.mapstruct.*;


//MapStruct mapper - converte entre ProductInputDTO/ProductOutputDTO e Product.
//componentModel = "spring" -> implementação gerada é um bean spring
@Mapper(componentModel = "spring")
public interface ProductMapper {
    // Converte ProductInputDTO -> Product (novo objeto para persistência).
    // MapStruct mapeia campos por nome automaticamente.
    Product toEntity(ProductInputDTO dto);
    // Converte Product -> ProductOutputDTO (dados prontos para resposta ao cliente).
    ProductOutputDTO toDTO(Product product);
    // Atualiza a entidade existente com os valores do DTO.
    // Campos nulos no DTO são ignorados (não sobrescrevem a entidade).
    // Útil para update parcial; evita product.setX(null) indesejado.
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProductInputDTO dto, @MappingTarget Product product);
}

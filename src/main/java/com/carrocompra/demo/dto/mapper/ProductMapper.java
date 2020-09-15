package com.carrocompra.demo.dto.mapper;

import com.carrocompra.demo.domain.Product;
import com.carrocompra.demo.dto.ProductDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Product and its DTO ProductDto.
 */
@Mapper( componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Product> {

}

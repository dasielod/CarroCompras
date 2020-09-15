package com.carrocompra.demo.dto.mapper;

import com.carrocompra.demo.domain.Product;
import com.carrocompra.demo.dto.ProductDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-15T00:38:09-0400",
    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_162 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( dto.getId() );
        product.setName( dto.getName() );
        product.setPrice( dto.getPrice() );

        return product;
    }

    @Override
    public ProductDto toDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( entity.getId() );
        productDto.setName( entity.getName() );
        productDto.setPrice( entity.getPrice() );

        return productDto;
    }

    @Override
    public List<Product> toEntity(List<ProductDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>();
        for ( ProductDto productDto : dtoList ) {
            list.add( toEntity( productDto ) );
        }

        return list;
    }

    @Override
    public List<ProductDto> toDto(List<Product> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>();
        for ( Product product : entityList ) {
            list.add( toDto( product ) );
        }

        return list;
    }
}

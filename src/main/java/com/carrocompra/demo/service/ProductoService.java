package com.carrocompra.demo.service;

import com.carrocompra.demo.dto.ProductDto;
import com.carrocompra.demo.exceptions.ProductException;

import java.util.List;

/**
 * Service interface for the Client entity.
 */
public interface ProductoService {

    ProductDto save(ProductDto productDto) throws ProductException;

    List<ProductDto> findAll() throws ProductException;

    ProductDto findOne(Long id) throws ProductException;

    void delete(Long id) throws ProductException;
}

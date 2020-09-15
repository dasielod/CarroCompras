package com.carrocompra.demo.service;

import com.carrocompra.demo.dto.ProductDto;
import com.carrocompra.demo.dto.SaleDto;
import rx.Single;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for the Sale entity.
 */
public interface VentaService {

    SaleDto save(SaleDto clientDto);

    Optional<SaleDto> findOne(Long id);

    List<SaleDto> findAll();

    Single<List<SaleDto>> findAllSalesById(Long id);

    SaleDto createSaleDetail(Long clientId, List<ProductDto> products);
}

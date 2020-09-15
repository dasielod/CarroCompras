package com.carrocompra.demo.service.impl;

import com.carrocompra.demo.EntityNotFoundException;
import com.carrocompra.demo.domain.Sale;
import com.carrocompra.demo.domain.SaleDetail;
import com.carrocompra.demo.dto.ProductDto;
import com.carrocompra.demo.dto.SaleDto;
import com.carrocompra.demo.service.VentaService;
import com.carrocompra.demo.dto.mapper.ProductMapper;
import com.carrocompra.demo.dto.mapper.SaleMapper;
import com.carrocompra.demo.repository.DetalleVentaRepository;
import com.carrocompra.demo.repository.VentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import rx.Single;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for the Sale entity.
 */
@Service
@Transactional
@Slf4j
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;

    private SaleMapper saleMapper;
    private ProductMapper productMapper;

    public VentaServiceImpl(VentaRepository ventaRepository, DetalleVentaRepository detalleVentaRepository, SaleMapper saleMapper, ProductMapper productMapper) {
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.saleMapper = saleMapper;
        this.productMapper = productMapper;
    }

    /**
     * Save a sale.
     *
     * @param saleDto la entidad a salvar
     * @return la nueva entidad
     */
    public SaleDto save(SaleDto saleDto) {
        log.debug("Request para guardar la venta : {}", saleDto);
        Sale sale = saleMapper.toEntity(saleDto);
        sale = ventaRepository.save(sale);
        return saleMapper.toDto(sale);
    }

    /**
     * Get ventas por id
     *
     * @param id id de la venta a buscar
     * @return Optional de venta
     */
    @Transactional(readOnly = true)
    public Optional<SaleDto> findOne(Long id) {
        log.debug("Request to get Sale : {}", id);
        return ventaRepository.findById(id)
                .map(saleMapper::toDto);
    }

    /**
     * Get todas las ventas
     *
     * @return lista de ventas
     */
    @Transactional(readOnly = true)
    public List<SaleDto> findAll() {
        log.debug("Request para obtener todas las ventas");
        return ventaRepository.findAll().stream()
                .map(saleMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     * Create a nuevo detalle de ventas
     *
     * @param products lista de producto de la venta que se realiza
     * @param clientId id del cliente
     * @return la entidad salvada
     */
    @Override
    public SaleDto createSaleDetail(Long clientId, List<ProductDto> products) {
        SaleDto sale = save(SaleDto.builder().clientId(clientId).date(ZonedDateTime.now()).build());
        for (ProductDto product : products) {
            SaleDetail saleDetail =  new SaleDetail();
            saleDetail.setProduct(productMapper.toEntity(product));
            saleDetail.setSale(saleMapper.toEntity(sale));
            detalleVentaRepository.save(saleDetail);
        }
        return sale;
    }


    @Transactional(readOnly = true)
    public Single<List<SaleDto>> findAllSalesById(Long id) {
        log.debug("Request to get all sales from userId : {}", id);
        return findAllSalesInRepository(id)
                .map(this::toBookResponseList);
    }

    private Single<List<Sale>> findAllSalesInRepository(Long id) {
        return Single.create(singleSubscriber -> {
            List<Sale> sales = ventaRepository.findAllByClient_Id(id);

            if (!ObjectUtils.isEmpty(sales)) {
                singleSubscriber.onSuccess(sales);
            } else {
                singleSubscriber.onError(new EntityNotFoundException(Sale.class));
            }
        });
    }

    private List<SaleDto> toBookResponseList(List<Sale> bookList) {
        return bookList
                .stream()
                .map(saleMapper :: toDto)
                .collect(Collectors.toList());
    }
}

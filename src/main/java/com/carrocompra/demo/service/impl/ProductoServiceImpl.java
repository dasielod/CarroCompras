package com.carrocompra.demo.service.impl;

import com.carrocompra.demo.domain.Product;
import com.carrocompra.demo.dto.ProductDto;
import com.carrocompra.demo.service.ProductoService;
import com.carrocompra.demo.dto.mapper.ProductMapper;
import com.carrocompra.demo.exceptions.ProductException;
import com.carrocompra.demo.repository.ProductoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for the Product entity.
 */
@Service
@Transactional
@Log4j2
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductMapper productMapper;

    /**
     * Guardar Producto
     *
     * @param productDto entidad de prodcuto a guardar
     * @return entidad que se obtiene al guardar el prodcuto
     */
    public ProductDto save(ProductDto productDto) throws ProductException {
        log.debug("Request para salvar el prudcto : {}", productDto);

        try{
            Product product = productMapper.toEntity(productDto);
            product = productoRepository.save(product);
            return productMapper.toDto(product);
        } catch (Exception ex) {
            throw new ProductException(String.format("Ocurrió un error al salvar el producto: %s", ex.getMessage()));
        }
    }
    /**
     * Obtener por id
     *
     * @param id
     * @return entidad de producto
     */
    @Transactional(readOnly = true)
    public Product findById(Long id) throws ProductException {
        log.debug("[ProductoService] - findById {} " + "productId: " + id);

        try{
            return productoRepository.findById(id)
                    .orElseThrow(() -> new ProductException(String.format("no existe producto con ese id %s", id)));
        } catch (Exception ex) {
            throw ex;
        }
    }


    /**
     * Obtener producto por id
     *
     * @param id
     * @return la entidad producto
     */
    @Transactional(readOnly = true)
    public ProductDto findOne(Long id) throws ProductException {
        log.debug("[ProductoService] - findOne {} " + "productId: " + id);

        try{
            return productMapper.toDto(this.findById(id));
        } catch (ProductException e) {
            throw e;
        } catch (Exception ex) {
            throw new ProductException(String.format("Ocurrió un error al obtener un producto por id: %s", ex.getMessage()));
        }
    }

    /**
     * Obtener todos los productos
     *
     * @return lista de entidades de productos
     */
    @Transactional(readOnly = true)
    public List<ProductDto> findAll() throws ProductException {
        log.debug("[ProductoService] - getAll {}");

        try{
            return productMapper.toDto(productoRepository.findAll());
        } catch (Exception ex) {
            throw new ProductException(String.format("Error al obtener la lista de productos: %s", ex.getMessage()));
        }
    }

    /**
     * Eliminar un producto por su id
     *
     * @param id
     */
    public void delete(Long id) throws ProductException {
        log.info("[ProductoService] - delete {} " + "productId: " + id);

        try{
            productoRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ProductException(String.format("Ocurrio un error al eliminar el producto: %s", ex.getMessage()));
        }
    }
}

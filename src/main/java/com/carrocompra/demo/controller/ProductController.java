package com.carrocompra.demo.controller;

import com.carrocompra.demo.dto.ProductDto;
import com.carrocompra.demo.exceptions.ProductException;
import com.carrocompra.demo.service.ProductoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductoService productoService;

    public ProductController(ProductoService productoService) {
        this.productoService = productoService;
    }

    public static void writeLog(String text) {
        log.error(text);
    }

    /**
     * POST  /products : Crear Producto.
     *
     * @param productDTO producto que se va a crear
     * @return entidad de respuesta con status 201 (Created) y productDTO en el body
     * @throws URISyntaxException
     */
    @PostMapping("/producto")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDTO) throws URISyntaxException, ProductException {
        log.debug("Request para guardar el Producto : {}", productDTO);

        ProductDto result = productoService.save(productDTO);
        return ResponseEntity.created(new URI("/api/producto/" + result.getId()))
                .body(result);
    }

    /**
     * PUT  /producto : Actualiza un producto existente.
     *
     * @param productDTO producto a actualizar
     * @return Status 200 (OK) y productDTO en el body
     */
    @PutMapping("/product")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDTO) throws ProductException {
        log.debug("Request para actualizar el Producto : {}", productDTO);
        ProductDto result = productoService.save(productDTO);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * GET  /product : devuelve los productos
     *
     * @return Status 200 (OK) and la lista de productos en el body
     */
    @GetMapping("/producto")
    public List<ProductDto> getAllProducts() throws ProductException {
        log.debug("Request para devolver los Productos");
        return productoService.findAll();
    }

    /**
     * GET  /producto/:id : obtiene el producto dado el Id
     *
     * @param id identificador del producto que se quiere devolver
     * @return Status 200 (OK) y el producto en el body
     */
    @GetMapping("/producto/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) throws ProductException {
        log.debug("Request para obtener el Producto : {}", id);

        ProductDto productDTO = productoService.findOne(id);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }

    /**
     * DELETE  /products/:id : borra el producto dado el Id.
     *
     * @param id id dentro de productDTO para eliminar el producto
     * @return Status 200 (OK)
     */
    @DeleteMapping("/producto/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ProductException {
        log.debug("Request para eliminar el Producto : {}", id);
        productoService.delete(id);
        return ResponseEntity.ok().build();
    }

}

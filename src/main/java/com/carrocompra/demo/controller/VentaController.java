package com.carrocompra.demo.controller;

import com.carrocompra.demo.dto.ProductDto;
import com.carrocompra.demo.dto.SaleDto;
import com.carrocompra.demo.service.VentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rx.Single;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class VentaController {

	private final VentaService ventaService;

	public VentaController(VentaService ventaService) {
		this.ventaService = ventaService;
	}

    public static void writeLog(String text) {
        log.error(text);
    }

    /**
     * POST  /ventas : Crea una nueva venta.
     *
     * @param saleDTO la venta que se va a crear
     * @return Status 201 (Created) Y saleDTO creada en el body
     * @throws URISyntaxException
     */
    @PostMapping("/ventas")
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleDto saleDTO) throws URISyntaxException {
        log.debug("Request para salvar la Venta : {}", saleDTO);

        SaleDto result = ventaService.save(saleDTO);
        return ResponseEntity.created(new URI("/api/ventas/" + result.getId()))
                .body(result);
    }

	/**
     * GET  /ventas : obtener todas las ventas.
     *
     * @return Status 200 (OK) y la lista de las ventas en el body
     */
    @GetMapping("/ventas")
    public List<SaleDto> getAllSales() {
        log.debug("Request para obtener todas las ventas");
        return ventaService.findAll();
    }

    /**
     * POST  /ventas : Crear detalle de la venta
     *
     * @param products lista de productos para el cual se registra el detalle de la venta
     * @param clientId id del cliente
     * @return the Status 201 (Created) y el objeto saleDTO en el body
     * @throws URISyntaxException
     */

    @PostMapping("/ventas/detalle/{clientId}")
    public ResponseEntity<SaleDto> createSaleDetail(@RequestBody List<ProductDto> products, @PathVariable Long clientId) throws URISyntaxException {
        log.debug("Request para guardar el detalle de la venta : {}", clientId);
        SaleDto result = ventaService.createSaleDetail(clientId, products);
        return ResponseEntity.created(new URI("/api/ventas/detalle/" + result.getId()))
                .body(result);
    }

    /**
     * GET /ventas/{id} : obtener las ventas por id de cliente
     * @param id Id del cliente por el que se consutlan las ventas
     * @return Status 200 (OK) y la venta en el body
     */
    @GetMapping("/ventas/{id}")
    public Single<List<SaleDto>> getAllSalesByUserId(@PathVariable Long id) {
        log.debug("Request para obtener las ventas por userId : {}", id);
        return ventaService.findAllSalesById(id);
    }


}

package com.carrocompra.demo.controller;

import com.carrocompra.demo.dto.ClientDto;
import com.carrocompra.demo.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/cliente")
@Slf4j
public class ClientController {

	private final ClienteService clienteService;

	public ClientController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

    public static void writeLog(String text) {

        log.error(text);

    }


    /**
     * POST  /cliente : Crea un nuevo cliente
     *
     * @param clientDTO el DTO del cliente que se va a crear
     * @return responde la entidad con status 201 (Created) y en el body clientDTO
     * @throws URISyntaxException si la sintaxis de la URI no es correcta
     */
    @PostMapping("")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDTO) throws URISyntaxException {
        log.debug("REST request para salvar el cliente : {}", clientDTO);

        ClientDto result = clienteService.save(clientDTO);
        return ResponseEntity.created(new URI("/api/cliente/" + result.getId()))
                .body(result);
    }

}

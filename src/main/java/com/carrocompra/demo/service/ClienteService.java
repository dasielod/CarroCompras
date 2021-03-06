package com.carrocompra.demo.service;

import com.carrocompra.demo.dto.ClientDto;
import rx.Single;

import java.util.List;

/**
 * Service interface for the Client entity.
 */
public interface ClienteService {

    ClientDto save(ClientDto clientDto);

    List<ClientDto> findAll();

    Single<Object> findAllById(Long id);
}

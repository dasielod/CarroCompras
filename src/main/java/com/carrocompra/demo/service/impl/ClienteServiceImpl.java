package com.carrocompra.demo.service.impl;


import com.carrocompra.demo.apierror.MyError;
import com.carrocompra.demo.domain.Client;
import com.carrocompra.demo.dto.ClientDto;
import com.carrocompra.demo.service.ClienteService;
import com.carrocompra.demo.dto.mapper.ClientMapper;
import com.carrocompra.demo.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Single;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for the Client entity.
 */
@Service
@Transactional
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    private ClientMapper clientMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClientMapper clientMapper) {
        this.clienteRepository = clienteRepository;
        this.clientMapper = clientMapper;
    }

    /**
     * Guardar cliente
     *
     * @param clientDto objeto qeu se salva
     * @return the entidad que se guardó
     */
    public ClientDto save(ClientDto clientDto) {
        log.debug("Request para salver el Cliente : {}", clientDto);
        Client client = clientMapper.toEntity(clientDto);
        client = clienteRepository.save(client);
        return clientMapper.toDto(client);
    }




    /**
     * Obener todos los clientes por id
     *
     * @param id
     * @return Optional de Client
     */
    @Transactional(readOnly = true)
    public Single<Object> findAllById(Long id) {
        log.debug("Request obtener todos los clientes por id");
        return Single.create(singleSubscriber ->{
            final Optional<ClientDto> optionalClient = clienteRepository.findById(id).map(clientMapper::toDto);
            if (!optionalClient.isPresent()){
                singleSubscriber.onError(new MyError(HttpStatus.NO_CONTENT,
                        204,
                        "no se encontró el campo por ese id",
                        ""));
                throw new EntityNotFoundException("no se encontró el campo por ese id");

            }else {
                singleSubscriber.onSuccess(optionalClient);
            }
        });
    }
    /**
     * Obtener todos los clientes
     *
     * @return lista de clientes
     */
    @Transactional(readOnly = true)
    public List<ClientDto> findAll() {
        log.debug("Request to get all clients");
        return clienteRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

}

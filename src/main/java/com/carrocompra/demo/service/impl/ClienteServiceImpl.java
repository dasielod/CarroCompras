package com.carrocompra.demo.service.impl;


import com.carrocompra.demo.apierror.MyError;
import com.carrocompra.demo.domain.Client;
import com.carrocompra.demo.dto.ClientDto;
import com.carrocompra.demo.service.ClientService;
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
public class ClientServiceImpl implements ClientService {

    private final ClienteRepository clienteRepository;

    private ClientMapper clientMapper;

    public ClientServiceImpl(ClienteRepository clienteRepository, ClientMapper clientMapper) {
        this.clienteRepository = clienteRepository;
        this.clientMapper = clientMapper;
    }

    /**
     * Save a client.
     *
     * @param clientDto the entity to save
     * @return the persisted entity
     */
    public ClientDto save(ClientDto clientDto) {
        log.debug("Request to save Client : {}", clientDto);
        Client client = clientMapper.toEntity(clientDto);
        client = clienteRepository.save(client);
        return clientMapper.toDto(client);
    }


    /**
     * Get all clients.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ClientDto> findAll() {
        log.debug("Request to get all clients");
        return clienteRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all clients by id.
     * @param id
     * @return Optional of Client
     */
    @Transactional(readOnly = true)
    public Single<Object> findAllById(Long id) {
        log.debug("Request to get all clients by Id");
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


}

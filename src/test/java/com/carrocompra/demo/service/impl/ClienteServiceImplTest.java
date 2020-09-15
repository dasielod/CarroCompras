package com.carrocompra.demo.service.impl;

import com.carrocompra.demo.apierror.MyError;
import com.carrocompra.demo.domain.Client;
import com.carrocompra.demo.dto.ClientDto;
import com.carrocompra.demo.dto.mapper.ClientMapper;
import com.carrocompra.demo.repository.ClienteRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @Rule
    public ExpectedException fails = ExpectedException.none();

    @Mock
    ClienteRepository clienteRepository;
    @Mock
    ClientMapper clientMapper;
    @InjectMocks
    private ClientServiceImpl service;

    Client client = Client.newInstance()
            .id(TestConstants.CLIENT_ID)
            .email("correo@gmail.com")
            .name("correo")
            .lastName("de cuba")
            .dni("dni")
            .phone("000-00000")
            .build();

    ClientDto clientDto = ClientDto.newInstance()
            .id(TestConstants.CLIENT_ID)
            .email("correo@gmail.com")
            .name("correo")
            .lastName("de cuba")
            .dni("dni")
            .phone("000-00000")
            .build();

    @Before
    public void setUp() throws Exception {

        when(clientMapper.toEntity(clientDto)).thenReturn(client);
        when(clientMapper.toDto(client)).thenReturn(clientDto);

        when(clienteRepository.save(client)).thenReturn(client);
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(client));
    }

    @Test
    public void whenSaveProductAllParamsSet() {

        ClientDto clientdto = service.save(clientDto);

        verify(clienteRepository, times(1))
                .save(client);
        verify(clientMapper, times(1))
                .toEntity(clientDto);
        verify(clientMapper, times(1))
                .toDto(client);

        assertEquals(clientdto.getId(), TestConstants.CLIENT_ID);
    }

    @Test
    public void findAll() {

        List<ClientDto> clientDtoList = service.findAll();

        verify(clienteRepository, times(1))
                .findAll();

        verify(clientMapper, times(1))
                .toDto(client);

        assertTrue(!clientDtoList.isEmpty());
        assertTrue(clientDtoList.size() > 0);
    }

    @Test
    public void findAllById() {

        when(clienteRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(new Client()));

        service.findAllById(TestConstants.CLIENT_ID)
                .test()
                .assertNotCompleted()
                .assertError(MyError.class)
                .awaitTerminalEvent();

        verify(clienteRepository, times(1)).findById(any(Long.class));

    }

}
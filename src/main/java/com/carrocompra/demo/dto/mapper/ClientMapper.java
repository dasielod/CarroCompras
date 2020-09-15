package com.carrocompra.demo.dto.mapper;

import com.carrocompra.demo.domain.Client;
import com.carrocompra.demo.dto.ClientDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Client and its DTO ClientDTO.
 */
@Mapper( componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDto, Client> {

}

package com.shoppingcar.demo.dto.mapper;

import com.shoppingcar.demo.domain.Client;
import com.shoppingcar.demo.dto.ClientDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Client and its DTO ClientDTO.
 */
@Mapper( componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDto, Client> {

}

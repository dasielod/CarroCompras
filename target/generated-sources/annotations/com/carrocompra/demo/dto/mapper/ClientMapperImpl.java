package com.carrocompra.demo.dto.mapper;

import com.carrocompra.demo.domain.Client;
import com.carrocompra.demo.dto.ClientDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-15T00:38:09-0400",
    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_162 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client toEntity(ClientDto dto) {
        if ( dto == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( dto.getId() );
        client.setName( dto.getName() );
        client.setLastName( dto.getLastName() );
        client.setDni( dto.getDni() );
        client.setPhone( dto.getPhone() );
        client.setEmail( dto.getEmail() );

        return client;
    }

    @Override
    public ClientDto toDto(Client entity) {
        if ( entity == null ) {
            return null;
        }

        ClientDto clientDto = new ClientDto();

        clientDto.setId( entity.getId() );
        clientDto.setName( entity.getName() );
        clientDto.setLastName( entity.getLastName() );
        clientDto.setDni( entity.getDni() );
        clientDto.setPhone( entity.getPhone() );
        clientDto.setEmail( entity.getEmail() );

        return clientDto;
    }

    @Override
    public List<Client> toEntity(List<ClientDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Client> list = new ArrayList<Client>();
        for ( ClientDto clientDto : dtoList ) {
            list.add( toEntity( clientDto ) );
        }

        return list;
    }

    @Override
    public List<ClientDto> toDto(List<Client> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ClientDto> list = new ArrayList<ClientDto>();
        for ( Client client : entityList ) {
            list.add( toDto( client ) );
        }

        return list;
    }
}

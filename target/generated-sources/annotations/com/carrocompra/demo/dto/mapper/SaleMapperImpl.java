package com.carrocompra.demo.dto.mapper;

import com.carrocompra.demo.domain.Client;
import com.carrocompra.demo.domain.Sale;
import com.carrocompra.demo.dto.SaleDto;
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
public class SaleMapperImpl implements SaleMapper {

    @Override
    public List<Sale> toEntity(List<SaleDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Sale> list = new ArrayList<Sale>();
        for ( SaleDto saleDto : dtoList ) {
            list.add( toEntity( saleDto ) );
        }

        return list;
    }

    @Override
    public List<SaleDto> toDto(List<Sale> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SaleDto> list = new ArrayList<SaleDto>();
        for ( Sale sale : entityList ) {
            list.add( toDto( sale ) );
        }

        return list;
    }

    @Override
    public SaleDto toDto(Sale sale) {
        if ( sale == null ) {
            return null;
        }

        SaleDto saleDto_ = new SaleDto();

        saleDto_.setClientName( saleClientName( sale ) );
        saleDto_.setClientId( saleClientId( sale ) );
        saleDto_.setId( sale.getId() );
        saleDto_.setDate( sale.getDate() );

        return saleDto_;
    }

    @Override
    public Sale toEntity(SaleDto saleDTO) {
        if ( saleDTO == null ) {
            return null;
        }

        Sale sale_ = new Sale();

        Client client = new Client();
        sale_.setClient( client );

        client.setId( saleDTO.getClientId() );
        sale_.setId( saleDTO.getId() );
        sale_.setDate( saleDTO.getDate() );

        return sale_;
    }

    private String saleClientName(Sale sale) {

        if ( sale == null ) {
            return null;
        }
        Client client = sale.getClient();
        if ( client == null ) {
            return null;
        }
        String name = client.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long saleClientId(Sale sale) {

        if ( sale == null ) {
            return null;
        }
        Client client = sale.getClient();
        if ( client == null ) {
            return null;
        }
        Long id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}

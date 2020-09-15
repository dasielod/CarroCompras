package com.carrocompra.demo.dto;

import lombok.*;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SaleDto {
    private Long id;
    private ZonedDateTime date;
    private Long clientId;
    private String clientName;

}

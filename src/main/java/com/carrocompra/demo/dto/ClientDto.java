package com.carrocompra.demo.dto;

import lombok.*;

@Builder(builderMethodName = "newInstance")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto {
    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private String phone;
    private String email;
}

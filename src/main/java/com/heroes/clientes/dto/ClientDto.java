package com.heroes.clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long id;
    private Integer rutNumber;
    private String rutDv;
    private String name;
    private String lastName;
    private String email;
}

package com.heroes.clientes.business;

import com.heroes.clientes.dto.ClientDto;

import java.util.List;

public interface ClientBusiness {
    List<ClientDto> get();
    ClientDto get(Long id);
    ClientDto save(ClientDto clientDto);
    ClientDto put(ClientDto clientDto);
    void delete(Long id);
}

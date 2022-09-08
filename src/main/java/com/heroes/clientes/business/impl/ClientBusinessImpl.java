package com.heroes.clientes.business.impl;

import com.heroes.clientes.business.ClientBusiness;
import com.heroes.clientes.dto.ClientDto;
import com.heroes.clientes.entity.ClientEntity;
import com.heroes.clientes.config.mapping.ClientMapper;
import com.heroes.clientes.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class ClientBusinessImpl implements ClientBusiness {
    private static final String NOT_FOUND = "Client with id %s does not exist";
    private ClientService service;
    private ClientMapper mapper;

    @Autowired
    public ClientBusinessImpl(ClientService service, ClientMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<ClientDto> get() {
        return mapper.toDtoList(service.get());
    }

    @Override
    public ClientDto get(Long id) {
        return mapper.toDto(clientIdValidation(id));
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        ClientEntity entity = service.save(mapper.toEntity(clientDto));
        return mapper.toDto(entity);
    }

    @Override
    public ClientDto put(ClientDto clientDto) {
        ClientDto auxDto = mapper.toDto(clientIdValidation(clientDto.getId()));
        clientDto.setId(auxDto.getId());
        return this.save(clientDto);
    }

    @Override
    public void delete(Long id) {
        ClientDto clientDto = mapper.toDto(clientIdValidation(id));;
        service.delete(clientDto.getId());
    }

    private ClientEntity clientIdValidation(Long id){
        ClientEntity entity = service.get(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(NOT_FOUND, id))
        );
        return entity;
    }



}

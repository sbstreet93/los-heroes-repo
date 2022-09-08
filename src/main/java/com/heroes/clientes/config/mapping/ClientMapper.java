package com.heroes.clientes.config.mapping;

import com.heroes.clientes.dto.ClientDto;
import com.heroes.clientes.entity.ClientEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientEntity toEntity(ClientDto clientDto);
    ClientDto toDto(ClientEntity clientEntity);
    List<ClientDto> toDtoList(List<ClientEntity> clientEntities);
}

package com.heroes.clientes.service;

import com.heroes.clientes.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientEntity> get();
    Optional<ClientEntity> get(Long id);
    ClientEntity save(ClientEntity clientEntity);
    void delete(Long id);
    Optional<ClientEntity> findByRutNumberAndEmail(Integer rutNumber, String email);
}

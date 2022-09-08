package com.heroes.clientes.service.impl;

import com.heroes.clientes.entity.ClientEntity;
import com.heroes.clientes.repository.ClientRepository;
import com.heroes.clientes.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository repository;

    @Override
    public List<ClientEntity> get(){
        return repository.findAll();
    }
    @Override
    public Optional<ClientEntity> get(Long id){
        return repository.findById(id);
    }
    @Override
    public ClientEntity save(ClientEntity clientEntity){
        return repository.save(clientEntity);
    }
    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }
    @Override
    public Optional<ClientEntity> findByRutNumberAndEmail(Integer rutNumber, String email){
        return repository.findByRutNumberAndEmail(rutNumber, email);
    }
}

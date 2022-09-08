package com.heroes.clientes.repository;

import com.heroes.clientes.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByRutNumberAndEmail(Integer rutNumber, String email);
}

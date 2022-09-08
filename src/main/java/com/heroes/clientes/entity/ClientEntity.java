package com.heroes.clientes.entity;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rut_number")
    private Integer rutNumber;
    @Column(name = "rut_dv")
    private String rutDv;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;

    public ClientEntity() {
    }

    public ClientEntity(Long id, Integer rutNumber, String rutDv, String name, String lastName, String email) {
        this.id = id;
        this.rutNumber = rutNumber;
        this.rutDv = rutDv;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRutNumber() {
        return rutNumber;
    }

    public void setRutNumber(Integer rutNumber) {
        this.rutNumber = rutNumber;
    }

    public String getRutDv() {
        return rutDv;
    }

    public void setRutDv(String rutDv) {
        this.rutDv = rutDv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

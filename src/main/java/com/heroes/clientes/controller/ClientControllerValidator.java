package com.heroes.clientes.controller;

import com.heroes.clientes.dto.ClientDto;
import com.heroes.clientes.entity.ClientEntity;
import com.heroes.clientes.service.ClientService;
import com.heroes.clientes.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientControllerValidator implements Validator {
    private static final String ERROR_CODE = String.valueOf(HttpStatus.BAD_REQUEST.value());
    private static final String ERROR_CONFLICT = String.valueOf(HttpStatus.CONFLICT.value());
    private static final String MESSAGE_NULL_OR_EMPTY = "%s should not be null or empty";
    private static final String MESSAGE_INVALID_RUT = "Rut number is not valid";
    private static final String MESSAGE_RUT_CONFLICT = "Rut already exist";
    private static final String MESSAGE_EMAIL_CONFLICT = "Email already exist";

    @Autowired
    ClientService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClientDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClientDto clientDto = (ClientDto) target;
        validateParamNullOrEmpty(errors, clientDto.getName(), "Name");
        validateParamNullOrEmpty(errors, clientDto.getLastName(), "Last name");
        validateParamNullOrEmpty(errors, clientDto.getEmail(), "Email");
        validateParamNullOrEmpty(errors, clientDto.getRutDv(), "DV");
        validateParamNumber(errors, clientDto.getRutNumber(), "Rut number");
        validateRutAndDV(errors, clientDto.getRutNumber(), clientDto.getRutDv());
        if(clientDto.getId() == null) {
            validateEmailAndRutPost(errors, clientDto.getRutNumber(), clientDto.getEmail());
        }else{
            validateEmailAndRutPut(errors, clientDto.getRutNumber(), clientDto.getEmail(), clientDto.getId());
        }
    }

    private void validateParamNullOrEmpty(Errors errors, String param, String paramName){
        if(Utils.isNullOrEmpty(param))
            errors.reject(ERROR_CODE,String.format(MESSAGE_NULL_OR_EMPTY, paramName));
    }
    private void validateParamNumber(Errors errors, Integer param, String paramName){
        if(param == 0 || param < 0)
            errors.reject(ERROR_CODE,String.format(MESSAGE_NULL_OR_EMPTY, paramName));
    }
    private void validateRutAndDV(Errors errors, Integer rutNumber, String dv){
        if(!Utils.dvValidation(rutNumber, dv))
            errors.reject(ERROR_CODE, MESSAGE_INVALID_RUT);
    }

    private void validateEmailAndRutPost(Errors errors, Integer rutNumber, String email){
        List<ClientEntity> clientEntities = service.get();
        List<Integer> ruts = clientEntities.stream()
                .filter(clientEntity -> clientEntity.getRutNumber().intValue() == rutNumber)
                .map(ClientEntity::getRutNumber)
                .collect(Collectors.toList());
        List<String> emails = clientEntities.stream()
                .filter(clientEntity -> clientEntity.getEmail().equalsIgnoreCase(email))
                .map(ClientEntity::getEmail)
                .collect(Collectors.toList());
        if(!ruts.isEmpty())
            errors.reject(ERROR_CONFLICT, MESSAGE_RUT_CONFLICT);
        if(!emails.isEmpty())
            errors.reject(ERROR_CONFLICT, MESSAGE_EMAIL_CONFLICT);
    }

    private void validateEmailAndRutPut(Errors errors, Integer rutNumber, String email, Long id){
        List<ClientEntity> clientEntities = service.get().stream().filter(clientEntity -> clientEntity.getId() != id).collect(Collectors.toList());
        List<Integer> ruts = clientEntities.stream()
                .filter(clientEntity -> clientEntity.getRutNumber().intValue() == rutNumber)
                .map(ClientEntity::getRutNumber)
                .collect(Collectors.toList());
        List<String> emails = clientEntities.stream()
                .filter(clientEntity -> clientEntity.getEmail().equalsIgnoreCase(email))
                .map(ClientEntity::getEmail)
                .collect(Collectors.toList());
        if(!ruts.isEmpty())
            errors.reject(ERROR_CONFLICT, MESSAGE_RUT_CONFLICT);
        if(!emails.isEmpty())
            errors.reject(ERROR_CONFLICT, MESSAGE_EMAIL_CONFLICT);
    }
}

package com.heroes.clientes.controller;

import com.heroes.clientes.business.ClientBusiness;
import com.heroes.clientes.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {

    private ClientBusiness business;
    private ClientControllerValidator validator;

    @Autowired
    public ClientController(ClientBusiness business, ClientControllerValidator validator) {
        this.business = business;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity<Object> get(){
        return new ResponseEntity<>(business.get(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id){
        return new ResponseEntity<>(business.get(id), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Object> put(@RequestBody @Validated ClientDto clientDto){
        return new ResponseEntity<>(business.put(clientDto), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Object> post(@RequestBody @Validated ClientDto clientDto){
        return new ResponseEntity<>(business.save(clientDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        business.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @InitBinder("clientDto")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}

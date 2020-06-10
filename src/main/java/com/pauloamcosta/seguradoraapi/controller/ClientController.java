package com.pauloamcosta.seguradoraapi.controller;

import com.pauloamcosta.seguradoraapi.dto.ClientDto;
import com.pauloamcosta.seguradoraapi.exceptions.InvalidCpfException;
import com.pauloamcosta.seguradoraapi.model.Client;
import com.pauloamcosta.seguradoraapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Client>> findAllClients() {
        List<Client> clientList = clienteService.getAllClients();
        return ResponseEntity.ok().body(clientList);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable String id) {
        Client client = clienteService.findById(id);
        return ResponseEntity.ok().body(new ClientDto(client));
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@Valid @RequestBody ClientDto clientDto) {
        Client client = clienteService.fromDto(clientDto);
        try {
            client = clienteService.createClient(client);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (InvalidCpfException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> updateClient(@RequestBody ClientDto policyDto, @PathVariable String id) {
        Client client = clienteService.fromDto(policyDto);
        clienteService.updateClient(client);
        return ResponseEntity.noContent().build();
    }

}

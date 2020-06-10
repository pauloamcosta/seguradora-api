package com.pauloamcosta.seguradoraapi.service;

import com.pauloamcosta.seguradoraapi.dto.ClientDto;
import com.pauloamcosta.seguradoraapi.exceptions.InvalidCpfException;
import com.pauloamcosta.seguradoraapi.model.Client;

import java.util.List;

public interface ClienteService {

    List<Client> getAllClients();

    Client createClient(Client client) throws InvalidCpfException;

    Client fromDto(ClientDto clientDto);

    Client findById(String id);

    void delete(String id);

    Client updateClient(Client client);

}

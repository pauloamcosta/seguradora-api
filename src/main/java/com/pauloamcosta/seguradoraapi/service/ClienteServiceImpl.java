package com.pauloamcosta.seguradoraapi.service;

import com.pauloamcosta.seguradoraapi.dto.ClientDto;
import com.pauloamcosta.seguradoraapi.exceptions.InvalidCpfException;
import com.pauloamcosta.seguradoraapi.model.Client;
import com.pauloamcosta.seguradoraapi.repository.ClientRepository;
import com.pauloamcosta.seguradoraapi.utils.CPFValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    ClientRepository clienteRepository;

    @Override
    public List<Client> getAllClients() {
        return clienteRepository.findAll();
    }

    @Override
    public Client createClient(Client client) throws InvalidCpfException {
        if (!CPFValidator.isCPF(client.getCpf())) {
            log.error("Cpf {} is invalid", client.getCpf());
            throw new InvalidCpfException(client.getCpf());
        }
        return clienteRepository.save(client);
    }

    @Override
    public Client fromDto(ClientDto clientDto) {
        return new Client(clientDto.getId(), clientDto.getName(), clientDto.getCpf(), clientDto.getCity(), clientDto.getState());
    }

    @Override
    public Client findById(String id) {

        Optional<Client> clientOptional = clienteRepository.findById(id);

        if (!clientOptional.isPresent()) {
            log.error("Client {} doesn't exists", id);
        }
        log.info("Client {} found", id);
        return clientOptional.get();
    }

    @Override
    public void delete(String id) {
        clienteRepository.delete(findById(id));
    }

    @Override
    public Client updateClient(Client client) {
        Client newClient = findById(client.getId());
        updateData(newClient, client);
        return clienteRepository.save(newClient);
    }

    private void updateData(Client newClient, Client client) {
        newClient.setName(client.getName());
        newClient.setCpf(client.getCpf());
        newClient.setCity(client.getCity());
        newClient.setState(client.getState());
    }
}

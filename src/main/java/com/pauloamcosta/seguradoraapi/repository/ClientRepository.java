package com.pauloamcosta.seguradoraapi.repository;

import com.pauloamcosta.seguradoraapi.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
}

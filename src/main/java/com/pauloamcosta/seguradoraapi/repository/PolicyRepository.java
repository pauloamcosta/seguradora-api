package com.pauloamcosta.seguradoraapi.repository;

import com.pauloamcosta.seguradoraapi.model.Policy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends MongoRepository<Policy, String> {
    Policy findByPolicyNumber(Integer number);

}

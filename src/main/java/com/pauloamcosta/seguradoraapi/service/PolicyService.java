package com.pauloamcosta.seguradoraapi.service;

import com.pauloamcosta.seguradoraapi.dto.PolicyDto;
import com.pauloamcosta.seguradoraapi.model.Policy;

import java.util.List;

public interface PolicyService {
    List<Policy> getAllPolicies();

    Policy createPolicy(Policy policy);

    Policy findPolicyById(String id);

    void deletePolicy(String id);

    Policy findPolicyByNumber(Integer number);

    Policy fromDto(PolicyDto policyDto);

    Policy updatePolicy(Policy policy);
}

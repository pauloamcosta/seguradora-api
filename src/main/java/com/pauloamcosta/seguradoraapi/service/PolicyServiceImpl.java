package com.pauloamcosta.seguradoraapi.service;

import com.pauloamcosta.seguradoraapi.dto.PolicyDto;
import com.pauloamcosta.seguradoraapi.model.Policy;
import com.pauloamcosta.seguradoraapi.repository.PolicyRepository;
import com.pauloamcosta.seguradoraapi.utils.PolicySequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyServiceImpl implements PolicyService {

    private static final Logger log = LoggerFactory.getLogger(PolicyServiceImpl.class);

    @Autowired
    PolicyRepository policyRepository;

    @Autowired
    PolicySequenceService policySequenceService;

    @Override
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    @Override
    public Policy createPolicy(Policy policy) {
        policy.setPolicyNumber(policySequenceService.getNextSequence("policySequences"));
        return policyRepository.save(policy);
    }

    @Override
    public Policy fromDto(PolicyDto policyDto) {
        return new Policy(policyDto.getId(), policyDto.getPolicyNumber(), policyDto.getStartTime(), policyDto.getEndTime(), policyDto.getVehiclePlate(), policyDto.getValue());
    }

    @Override
    public Policy findPolicyById(String id) {

        Optional<Policy> policyOptional = policyRepository.findById(id);

        if (!policyOptional.isPresent()) {
            log.error("Policy {} doesn't exists", id);
        }
        log.info("Policy {} found", id);
        return policyOptional.get();
    }

    @Override
    public void deletePolicy(String id) {
        policyRepository.delete(findPolicyById(id));
    }

    @Override
    public Policy findPolicyByNumber(Integer number) {
        return policyRepository.findByPolicyNumber(number);
    }

    @Override
    public Policy updatePolicy(Policy policy) {
        Policy newPolicy = findPolicyById(policy.getId());
        updateData(newPolicy, policy);
        return policyRepository.save(newPolicy);
    }

    private void updateData(Policy newPolicy, Policy policy) {
        newPolicy.setStartTime(policy.getStartTime());
        newPolicy.setEndTime(policy.getEndTime());
        newPolicy.setVehiclePlate(policy.getVehiclePlate());
        newPolicy.setValue(policy.getValue());
    }
}

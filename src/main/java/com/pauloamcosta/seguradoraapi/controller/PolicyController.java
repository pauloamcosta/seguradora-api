package com.pauloamcosta.seguradoraapi.controller;

import com.pauloamcosta.seguradoraapi.dto.PolicyDto;
import com.pauloamcosta.seguradoraapi.model.Policy;
import com.pauloamcosta.seguradoraapi.service.PolicyServiceImpl;
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
@RequestMapping(value = "/policy")
public class PolicyController {

    @Autowired
    PolicyServiceImpl policyService;

    @GetMapping
    public ResponseEntity<List<Policy>> findAllPolicies() {
        List<Policy> policiesList = policyService.getAllPolicies();
        return ResponseEntity.ok().body(policiesList);
    }

    @GetMapping(value="/{number}")
    public ResponseEntity<PolicyDto> findPolicyByNumber(@PathVariable Integer number) {
        try {
            Policy policy = policyService.findPolicyByNumber(number);
            return ResponseEntity.ok().body(new PolicyDto(policy));
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createPolicy(@Valid @RequestBody PolicyDto policyDto) {
        Policy policy = policyService.fromDto(policyDto);
        policy = policyService.createPolicy(policy);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(policy.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable String id) {
        policyService.deletePolicy(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> updatePolicy(@RequestBody PolicyDto policyDto, @PathVariable String id) {
        Policy policy = policyService.fromDto(policyDto);
        policyService.updatePolicy(policy);
        return ResponseEntity.noContent().build();
    }
}

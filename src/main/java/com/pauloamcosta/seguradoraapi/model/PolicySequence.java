package com.pauloamcosta.seguradoraapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "policySequences")
public class PolicySequence {

    @Id
    private String id;
    private int seq;

}

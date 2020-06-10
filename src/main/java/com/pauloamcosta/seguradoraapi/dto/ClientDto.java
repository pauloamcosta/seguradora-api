package com.pauloamcosta.seguradoraapi.dto;

import com.pauloamcosta.seguradoraapi.model.Client;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ClientDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @NotBlank(message="Field name must be filled")
    private String name;

    @NotBlank(message="Field cpf must be filled")
    private String cpf;

    @NotBlank(message="Field city must be filled")
    private String city;

    @NotBlank(message="Field state must be filled")
    private String state;

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.city = client.getCity();
        this.state = client.getState();
    }

    public ClientDto() {
    }
}

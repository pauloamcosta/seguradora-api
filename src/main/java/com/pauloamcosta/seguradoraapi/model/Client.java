package com.pauloamcosta.seguradoraapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Document(collection = "client")
@Getter
@Setter
@EqualsAndHashCode
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String cpf;

    private String city;

    private String state;

    public Client(String id, String name, String cpf, String city, String state) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.city = city;
        this.state = state;
    }

    public Client() {

    }
}

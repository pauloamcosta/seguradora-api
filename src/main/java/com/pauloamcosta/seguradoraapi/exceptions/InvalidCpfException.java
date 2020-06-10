package com.pauloamcosta.seguradoraapi.exceptions;

public class InvalidCpfException extends Exception {
    public InvalidCpfException(String cpf) {
        super("The Cpf " + cpf + " is invalid");
    }
}


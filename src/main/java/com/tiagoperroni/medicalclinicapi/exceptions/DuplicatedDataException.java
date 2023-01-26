package com.tiagoperroni.medicalclinicapi.exceptions;

public class DuplicatedDataException extends RuntimeException {

    public DuplicatedDataException(String msg) {
        super(msg);
    }
}

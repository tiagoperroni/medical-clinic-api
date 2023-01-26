package com.tiagoperroni.medicalclinicapi.exceptions;

public class EntityMissDataException extends RuntimeException {
    public EntityMissDataException(String msg) {
        super(msg);
    }
}

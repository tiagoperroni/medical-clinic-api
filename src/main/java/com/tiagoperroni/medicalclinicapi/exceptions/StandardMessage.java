package com.tiagoperroni.medicalclinicapi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class StandardMessage {

    private Integer statusCode;

    private List<String> message;

    @JsonFormat(pattern = "dd//MM/yyyy HH:mm")
    private LocalDateTime timestamp;

    public StandardMessage() {
    }

    public StandardMessage(Integer statusCode, List<String> message, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

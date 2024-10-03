package com.dbp.backendtourplus.exceptions;

import lombok.Data;

@Data
public class ErrorMessage {

    private String message;
    private String status;

    ErrorMessage(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
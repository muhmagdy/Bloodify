package com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class TransactionException extends RuntimeException {
    public TransactionException(String msg) {
        super(msg);
    }
}

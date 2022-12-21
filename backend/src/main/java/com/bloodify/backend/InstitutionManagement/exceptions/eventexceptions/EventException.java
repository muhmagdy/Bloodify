package com.bloodify.backend.InstitutionManagement.exceptions.eventexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class EventException extends RuntimeException {
    public EventException(String msg) {
        super(msg);
    }
}

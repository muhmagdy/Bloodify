package com.bloodify.backend.InstitutionManagement.exceptions.eventexceptions;

public class InvalidDescription extends EventException {
    public InvalidDescription() {
        super("Description maximum size is 200 characters!");
    }
}

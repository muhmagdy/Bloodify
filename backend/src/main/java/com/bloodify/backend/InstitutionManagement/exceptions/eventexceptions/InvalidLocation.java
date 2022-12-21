package com.bloodify.backend.InstitutionManagement.exceptions.eventexceptions;

public class InvalidLocation extends EventException {
    public InvalidLocation() {
        super("Location length must be between 5 and 200 characters!");
    }
}

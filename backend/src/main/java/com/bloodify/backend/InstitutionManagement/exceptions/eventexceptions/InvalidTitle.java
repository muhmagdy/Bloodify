package com.bloodify.backend.InstitutionManagement.exceptions.eventexceptions;

public class InvalidTitle extends EventException {
    public InvalidTitle() {
        super("Title length must be between 5 and 30 characters!");
    }
}

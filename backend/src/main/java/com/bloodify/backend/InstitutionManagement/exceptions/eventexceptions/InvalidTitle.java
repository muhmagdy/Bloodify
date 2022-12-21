package com.bloodify.backend.InstitutionManagement.exceptions.eventexceptions;

public class InvalidTitle extends EventException {
    public InvalidTitle() {
        super("Title length must be between 2 and 100 characters!");
    }
}

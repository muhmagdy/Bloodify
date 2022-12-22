package com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions;

public class InvalidUser extends TransactionException {
    public InvalidUser() {
        super("Invalid User!");
    }
}

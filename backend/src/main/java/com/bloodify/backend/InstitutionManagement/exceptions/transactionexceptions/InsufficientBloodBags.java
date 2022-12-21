package com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions;

public class InsufficientBloodBags extends TransactionException {
    public InsufficientBloodBags() {
        super("Insufficient Blood Bags!");
    }
}

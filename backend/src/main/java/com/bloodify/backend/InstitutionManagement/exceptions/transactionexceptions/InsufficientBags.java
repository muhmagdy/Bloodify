package com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions;

public class InsufficientBags extends TransactionException {
    public InsufficientBags() {
        super("Insufficient Bags!");
    }
}

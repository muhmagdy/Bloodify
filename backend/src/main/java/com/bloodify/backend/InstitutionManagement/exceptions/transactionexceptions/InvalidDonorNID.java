package com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions;

public class InvalidDonorNID  extends TransactionException {
    public InvalidDonorNID() {
        super("Invalid Donor National ID!");
    }
}

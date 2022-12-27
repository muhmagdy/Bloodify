package com.bloodify.backend.Statistics.Service.Interfaces;

import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;

import java.time.LocalDate;

public interface UserToInstitution {
    public BloodBagsCountWrapper[] bagsGotFromTransaction(LocalDate start, LocalDate end, String instEmail);
}

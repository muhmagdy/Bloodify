package com.bloodify.backend.Statistics.Service.Interfaces;

import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;

import java.time.LocalDate;

public interface AllThreeTypes {
    public BloodBagsCountWrapper[] getAllTransactionsBagsCount (LocalDate start, LocalDate end, String instEmail);
}

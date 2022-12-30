package com.bloodify.backend.Statistics.Service.Interfaces;

import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;

public interface BloodBagsCount {
    public BloodBagsCountWrapper[] getAllCounts (String email);
}

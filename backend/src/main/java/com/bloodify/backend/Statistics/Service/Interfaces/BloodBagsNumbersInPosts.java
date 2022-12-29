package com.bloodify.backend.Statistics.Service.Interfaces;

import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;

public interface BloodBagsNumbersInPosts {
    public BloodBagsCountWrapper[] postsNumber(String instEmail);
}

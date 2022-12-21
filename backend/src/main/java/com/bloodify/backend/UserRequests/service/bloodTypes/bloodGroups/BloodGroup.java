package com.bloodify.backend.UserRequests.service.bloodTypes.bloodGroups;

import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;

import java.util.List;

public abstract class BloodGroup implements BloodType {
    @Override
    abstract public List<BloodType> getCompatibleTypes();
}

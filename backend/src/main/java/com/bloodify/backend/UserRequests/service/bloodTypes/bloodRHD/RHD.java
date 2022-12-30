package com.bloodify.backend.UserRequests.service.bloodTypes.bloodRHD;

import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;
import com.bloodify.backend.UserRequests.service.bloodTypes.bloodGroups.BloodGroup;

import java.util.List;

public class RHD implements BloodType {
    private BloodGroup group;

    public RHD(BloodGroup group) {
        this.group = group;
    }

    @Override
    public List<BloodType> getCompatibleTypesPost(){
        return group.getCompatibleTypesPost();
    }

    @Override
    public List<BloodType> getCompatibleTypesUser() {
        return group.getCompatibleTypesUser();
    }

    @Override
    public String toString() {
        return group.toString();
    }
}

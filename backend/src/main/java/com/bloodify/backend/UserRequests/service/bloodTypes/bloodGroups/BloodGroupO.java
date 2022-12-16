package com.bloodify.backend.UserRequests.service.bloodTypes.bloodGroups;

import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;

import java.util.ArrayList;
import java.util.List;

public class BloodGroupO extends BloodGroup{
    private static BloodGroupO groupO;
    private BloodGroupO(){}

    public static BloodGroupO getGroupO() {
        if(groupO == null) groupO = new BloodGroupO();
        return groupO;
    }

    @Override
    public List<BloodType> getCompatibleTypes() {
        List<BloodType> compatibleGroups = new ArrayList<>();
        compatibleGroups.add(this);
        return compatibleGroups;
    }

    @Override
    public String toString() {
        return "O";
    }
}

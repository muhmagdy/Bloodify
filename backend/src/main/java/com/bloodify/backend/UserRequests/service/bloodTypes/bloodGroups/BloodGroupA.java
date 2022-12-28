package com.bloodify.backend.UserRequests.service.bloodTypes.bloodGroups;

import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;

import java.util.ArrayList;
import java.util.List;

public class BloodGroupA extends BloodGroup {
    private static BloodGroupA groupA;

    private BloodGroupA() {
    }

    public static BloodGroupA getGroupA() {
        if (groupA == null)
            groupA = new BloodGroupA();
        return groupA;
    }

    @Override
    public List<BloodType> getCompatibleTypesPost() {
        List<BloodType> compatibleGroups = new ArrayList<>();
        compatibleGroups.add(this);
        compatibleGroups.add(BloodGroupO.getGroupO());
        return compatibleGroups;
    }

    @Override
    public String toString() {
        return "A";
    }
}

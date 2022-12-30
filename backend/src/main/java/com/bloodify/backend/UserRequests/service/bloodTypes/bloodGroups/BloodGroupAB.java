package com.bloodify.backend.UserRequests.service.bloodTypes.bloodGroups;

import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;

import java.util.ArrayList;
import java.util.List;

public class BloodGroupAB extends BloodGroup {
    private static BloodGroupAB groupAB;

    private BloodGroupAB() {
    }

    public static BloodGroupAB getGroupAB() {
        if (groupAB == null)
            groupAB = new BloodGroupAB();
        return groupAB;
    }

    @Override
    public List<BloodType> getCompatibleTypesPost() {
        List<BloodType> compatibleGroups = new ArrayList<>();
        compatibleGroups.add(BloodGroupA.getGroupA());
        compatibleGroups.add(BloodGroupB.getGroupB());
        compatibleGroups.add(this);
        compatibleGroups.add(BloodGroupO.getGroupO());
        return compatibleGroups;
    }

    @Override
    public List<BloodType> getCompatibleTypesUser() {
        List<BloodType> compatibleGroups = new ArrayList<>();
        compatibleGroups.add(this);
        return compatibleGroups;
    }

    @Override
    public String toString() {
        return "AB";
    }
}

package com.bloodify.backend.UserRequests.service.bloodTypes.bloodGroups;

import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;

import java.util.ArrayList;
import java.util.List;

public class BloodGroupB extends BloodGroup{
    private static BloodGroupB groupB;
    private BloodGroupB(){}

    public static BloodGroupB getGroupB() {
        if (groupB == null) groupB = new BloodGroupB();
        return groupB;
    }

    @Override
    public List<BloodType> getCompatibleTypes() {
        List<BloodType> compatibleGroups = new ArrayList<>();
        compatibleGroups.add(this);
        compatibleGroups.add(BloodGroupO.getGroupO());
        return compatibleGroups;
    }

    @Override
    public String toString() {
        return "B";
    }
}

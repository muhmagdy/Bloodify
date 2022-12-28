package com.bloodify.backend.UserRequests.service.bloodTypes.bloodGroups;

import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;

import java.util.ArrayList;
import java.util.List;

public class BloodGroupO extends BloodGroup {
    private static BloodGroupO groupO;

    private BloodGroupO() {
    }

    public static BloodGroupO getGroupO() {
        if (groupO == null)
            groupO = new BloodGroupO();
        return groupO;
    }

    @Override
    public List<BloodType> getCompatibleTypesPost() {
        List<BloodType> compatibleGroups = new ArrayList<>();
        compatibleGroups.add(this);
        return compatibleGroups;
    }

    @Override
    public List<BloodType> getCompatibleTypesUser() {
        List<BloodType> compatibleGroups = new ArrayList<>();
        compatibleGroups.add(this);
        compatibleGroups.add(BloodGroupAB.getGroupAB());
        compatibleGroups.add(BloodGroupA.getGroupA());
        compatibleGroups.add(BloodGroupB.getGroupB());
        return compatibleGroups;
    }


    @Override
    public String toString() {
        return "O";
    }
}

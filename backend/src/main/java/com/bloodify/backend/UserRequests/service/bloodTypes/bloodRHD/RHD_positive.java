package com.bloodify.backend.UserRequests.service.bloodTypes.bloodRHD;

import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodTypeFactory;
import com.bloodify.backend.UserRequests.service.bloodTypes.bloodGroups.BloodGroup;

import java.util.ArrayList;
import java.util.List;

public class RHD_positive extends RHD{
    BloodTypeFactory bloodTypeFactory;

    public RHD_positive(BloodGroup group) {
        super(group);
        bloodTypeFactory = BloodTypeFactory.getFactory();
    }

    @Override
    public List<BloodType> getCompatibleTypes() {
        List<BloodType> compatibleGroups = super.getCompatibleTypes();
        List<BloodType> compatibleTypes = new ArrayList<>();
        for (BloodType type: compatibleGroups) {
            compatibleTypes.add(bloodTypeFactory.generatePositive((BloodGroup) type));
            compatibleTypes.add(bloodTypeFactory.generateNegative((BloodGroup) type));
        }
        return compatibleTypes;
    }

    @Override
    public String toString() {
        return super.toString() + "+";
    }
}

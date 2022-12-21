package com.bloodify.backend.UserRequests.service.bloodTypes;


import com.bloodify.backend.UserRequests.service.bloodTypes.bloodGroups.*;
import com.bloodify.backend.UserRequests.service.bloodTypes.bloodRHD.RHD_negative;
import com.bloodify.backend.UserRequests.service.bloodTypes.bloodRHD.RHD_positive;

import java.util.HashMap;
import java.util.Map;

public class BloodTypeFactory {
    private Map<String, BloodGroup> bloodGroupMap;
    private static BloodTypeFactory factory;
    private BloodTypeFactory(){
        bloodGroupMap = new HashMap<>();
        bloodGroupMap.put("A", BloodGroupA.getGroupA());
        bloodGroupMap.put("B", BloodGroupB.getGroupB());
        bloodGroupMap.put("AB", BloodGroupAB.getGroupAB());
        bloodGroupMap.put("O", BloodGroupO.getGroupO());
    }

    public static BloodTypeFactory getFactory() {
        if (factory == null) factory = new BloodTypeFactory();
        return factory;
    }

    public BloodType generatePositive(BloodGroup group){
        return new RHD_positive(group);
    }

    public BloodType generateNegative(BloodGroup group){
        return new RHD_negative(group);
    }

    public BloodType generateFromString(String type){
        BloodGroup group = this.bloodGroupMap.get(type.substring(0, type.length() - 1));
        return (type.charAt(type.length() - 1) == '+')? new RHD_positive(group) : new RHD_negative(group);
    }
}

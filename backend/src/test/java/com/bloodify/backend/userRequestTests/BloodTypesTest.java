package com.bloodify.backend.userRequestTests;

import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodTypeFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BloodTypesTest {
    BloodTypeFactory factory = BloodTypeFactory.getFactory();

    @Test
    void getCompatibleForAp(){
        List<BloodType> compatibleTypes = factory.generateFromString("A+").getCompatibleTypes();
        List<String> compatibleStrings = new ArrayList<>();
        for (BloodType type: compatibleTypes) compatibleStrings.add(type.toString());
        assertEquals(compatibleStrings.size() ,4);
        assertTrue(compatibleStrings.contains("A+") && compatibleStrings.contains("A-")
                && compatibleStrings.contains("O+") && compatibleStrings.contains("O-"));
    }

    @Test
    void getCompatibleForAm(){
        List<BloodType> compatibleTypes = factory.generateFromString("A-").getCompatibleTypes();
        List<String> compatibleStrings = new ArrayList<>();
        for (BloodType type: compatibleTypes) compatibleStrings.add(type.toString());
        assertEquals(compatibleStrings.size() ,2);
        assertTrue(compatibleStrings.contains("A-") && compatibleStrings.contains("O-"));
    }
    @Test
    void getCompatibleForBp(){
        List<BloodType> compatibleTypes = factory.generateFromString("B+").getCompatibleTypes();
        List<String> compatibleStrings = new ArrayList<>();
        for (BloodType type: compatibleTypes) compatibleStrings.add(type.toString());
        assertEquals(compatibleStrings.size() ,4);
        assertTrue(compatibleStrings.contains("B+") && compatibleStrings.contains("B-")
                && compatibleStrings.contains("O+") && compatibleStrings.contains("O-"));
    }

    @Test
    void getCompatibleForBm(){
        List<BloodType> compatibleTypes = factory.generateFromString("B-").getCompatibleTypes();
        List<String> compatibleStrings = new ArrayList<>();
        for (BloodType type: compatibleTypes) compatibleStrings.add(type.toString());
        assertEquals(compatibleStrings.size() ,2);
        assertTrue(compatibleStrings.contains("B-") && compatibleStrings.contains("O-"));
    }
    @Test
    void getCompatibleForABp(){
        List<BloodType> compatibleTypes = factory.generateFromString("AB+").getCompatibleTypes();
        List<String> compatibleStrings = new ArrayList<>();
        for (BloodType type: compatibleTypes) compatibleStrings.add(type.toString());
        assertEquals(compatibleStrings.size() ,8);
        assertTrue(compatibleStrings.contains("B+") && compatibleStrings.contains("B-")
                && compatibleStrings.contains("O+") && compatibleStrings.contains("O-")
                && compatibleStrings.contains("A+") && compatibleStrings.contains("A-")
                && compatibleStrings.contains("AB+") && compatibleStrings.contains("AB-"));
    }

    @Test
    void getCompatibleForABm(){
        List<BloodType> compatibleTypes = factory.generateFromString("AB-").getCompatibleTypes();
        List<String> compatibleStrings = new ArrayList<>();
        for (BloodType type: compatibleTypes) compatibleStrings.add(type.toString());
        assertEquals(compatibleStrings.size() ,4);
        assertTrue(compatibleStrings.contains("B-") && compatibleStrings.contains("O-")
                && compatibleStrings.contains("A-") && compatibleStrings.contains("AB-"));
    }

    @Test
    void getCompatibleForOp(){
        List<BloodType> compatibleTypes = factory.generateFromString("O+").getCompatibleTypes();
        List<String> compatibleStrings = new ArrayList<>();
        for (BloodType type: compatibleTypes) compatibleStrings.add(type.toString());
        assertEquals(compatibleStrings.size() ,2);
        assertTrue(compatibleStrings.contains("O+") && compatibleStrings.contains("O-"));
    }

    @Test
    void getCompatibleForOm(){
        List<BloodType> compatibleTypes = factory.generateFromString("O-").getCompatibleTypes();
        List<String> compatibleStrings = new ArrayList<>();
        for (BloodType type: compatibleTypes) compatibleStrings.add(type.toString());
        assertEquals(compatibleStrings.size() ,1);
        assertTrue(compatibleStrings.contains("O-"));
    }
}

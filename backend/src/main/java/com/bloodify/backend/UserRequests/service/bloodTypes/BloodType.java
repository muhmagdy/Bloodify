package com.bloodify.backend.UserRequests.service.bloodTypes;

import java.util.List;

public interface BloodType {
    String toString();
    List<BloodType> getCompatibleTypes();
}

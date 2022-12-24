package com.bloodify.backend.UserRequests.dto.entities;

import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Slf4j
public class SearchDto {
    BloodType bloodType;
    int bagNum;
}

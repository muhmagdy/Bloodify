package com.bloodify.backend.UserRequests.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Data
@AllArgsConstructor
@Slf4j
public class SearchResult {
    int institutionId;
    String institutionName;
    String institutionLocation;

    double longitude;

    double latitude;
    Map<String, Integer> types_bags;

    int working_hours;
}

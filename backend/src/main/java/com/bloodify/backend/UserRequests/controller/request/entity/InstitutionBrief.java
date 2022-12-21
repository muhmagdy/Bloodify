package com.bloodify.backend.UserRequests.controller.request.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class InstitutionBrief {
    int id;
    String name;
    String location;
}

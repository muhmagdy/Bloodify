package com.bloodify.backend.InstitutionManagement.controller.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostBrief {
        int id;
        String nationalID;
        String name;
        LocalDateTime dateTime;
        int count;
        String bloodType;
}

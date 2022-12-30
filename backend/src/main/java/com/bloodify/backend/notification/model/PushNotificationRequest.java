package com.bloodify.backend.notification.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PushNotificationRequest {
    private String message;
    private double longtitude;
    private double latitude;
    private String instituteName;
    private boolean isReaded;
}

package com.bloodify.backend.notification.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotificationResponseBody {
    private boolean status;
    private List<NotificationResponse> notificationResponses;
}

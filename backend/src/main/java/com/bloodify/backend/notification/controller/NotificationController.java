package com.bloodify.backend.notification.controller;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.notification.dao.Interfaces.NotificationHistoryDAO;
import com.bloodify.backend.notification.model.NotificationResponse;
import com.bloodify.backend.notification.model.NotificationResponseBody;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/notification")
public class NotificationController {
    @Autowired

    NotificationHistoryDAO notificationHistoryDAO;
    @Autowired
    UserDAO userDAO;

    @GetMapping(value = "/getNotifications")
    public ResponseEntity<NotificationResponseBody> getMethodName(Authentication auth) {
        User u = userDAO.findUserByEmail(auth.getName());
        System.out.println(u.getUserID());
        List<NotificationResponse> notificationResponses = notificationHistoryDAO
                .findPostsResponsebyUser(u.getUserID());
        boolean status = notificationResponses.size() != 0;
        if (status)
            return ResponseEntity.status(HttpStatus.OK).body(new NotificationResponseBody(true, notificationResponses));
        return ResponseEntity.status(422)
                .body(new NotificationResponseBody(false, null));
    }
}

package com.bloodify.backend.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloodify.backend.notification.model.PushNotificationRequest;
import com.bloodify.backend.notification.service.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessagingException;

import ch.qos.logback.core.subst.Token;

@RestController
@RequestMapping("/try")
public class controllerTry {
    @Autowired
    FirebaseMessagingService firebaseMessagingService;
    @GetMapping("/{token}")
    void searchInInstitution(@PathVariable("token") String token) throws FirebaseMessagingException{
       System.out.println("GGGGGGGGGGG");
        PushNotificationRequest ps = new PushNotificationRequest("HI" ,29.9292617,31.1924,"hos",false);
       firebaseMessagingService.sendNotification(ps, token);
    }
}

package com.bloodify.backend.notification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserRepository;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import com.bloodify.backend.notification.dao.Interfaces.NotificationHistoryRepository;
import com.bloodify.backend.notification.model.NotificationHistory;
import com.bloodify.backend.notification.model.NotificationHistoryKey;
// import com.bloodify.backend.notification.Interfaces.NotificationHistoryRepository;
import com.bloodify.backend.notification.model.PushNotificationRequest;
import com.bloodify.backend.notification.service.FirebaseMessagingService;
import com.google.firebase.messaging.FirebaseMessagingException;

import ch.qos.logback.core.subst.Token;

@RestController
@RequestMapping("/try")
public class controllerFireBAse {
    @Autowired
    FirebaseMessagingService firebaseMessagingService;
    @GetMapping("/message/{token}")
    void searchInInstitution(@PathVariable("token") String token) throws FirebaseMessagingException{
       System.out.println("GGGGGGGGGGG");
        PushNotificationRequest ps = new PushNotificationRequest("HI" ,29.9292617,31.1924,"hos",false);
       firebaseMessagingService.sendNotification(ps, token);
    }
    @Autowired
    NotificationHistoryRepository notificationHistoryRepository;
    @Autowired
    UserRepository ur;
    @Autowired 
    PostRepository po;
    @GetMapping("/user/{userid}")
    List<Post> getNotificationsToPost(@PathVariable("userid") int userid){
        NotificationHistoryKey notificationHistoryKey = new NotificationHistoryKey(1,1);
        Post p = po.findByPostID(1);
        System.out.println(p.toString());
        User u= ur.findByEmail("john@legend.me").get(0);
        System.out.println(u.toString());
        NotificationHistory notificationHistory = new NotificationHistory(notificationHistoryKey, u, p);
        
        notificationHistoryRepository.save(notificationHistory);
        return notificationHistoryRepository.findPostsbyUser(userid);
        
    }
}

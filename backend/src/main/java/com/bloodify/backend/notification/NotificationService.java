package com.bloodify.backend.notification;

import com.bloodify.backend.AccountManagement.dao.interfaces.LoginSessionDAO;
import com.bloodify.backend.notification.model.PushNotificationRequest;
import com.bloodify.backend.notification.service.FirebaseMessagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class NotificationService {
    final LoginSessionDAO login;

    final FirebaseMessagingService firebaseMessagingService;
    public boolean sendNotification(String toEmail,String title,String data){
        String toToken;
        try{
            toToken = login.getToken(toEmail);
            firebaseMessagingService.chatNotification(toToken,title ,data);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean sendNotification(String toEmail, PushNotificationRequest note){
        String toToken;
        try{
            toToken = login.getToken(toEmail);
            firebaseMessagingService.sendNotification(note, toToken);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

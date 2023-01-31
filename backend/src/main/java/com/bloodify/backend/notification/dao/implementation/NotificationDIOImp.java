package com.bloodify.backend.notification.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.notification.dao.Interfaces.NotificationHistoryDAO;
import com.bloodify.backend.notification.dao.Interfaces.NotificationHistoryRepository;
import com.bloodify.backend.notification.model.NotificationHistory;
import com.bloodify.backend.notification.model.NotificationResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationDIOImp implements NotificationHistoryDAO {
    @Autowired
    NotificationHistoryRepository notificationHistoryRepository;

    @Override
    public List<NotificationHistory> getNotifications() {
        try {
            List<NotificationHistory> listNotification = notificationHistoryRepository.findAll();
            return listNotification;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteNotificationByPost(int postID) {
        try {
            notificationHistoryRepository.deletePostNotification(postID);
            return true;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return false;
        }

    }

    @Override
    public List<Post> findPostsbyUser(int userID) {
        try {
            List<Post> listOfPost = notificationHistoryRepository.findPostsbyUser(userID);
            return listOfPost;

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    @Override
    public List<User> findUsersbyPosts(int postID) {
        try {
            List<User> users = notificationHistoryRepository.findUsersbyPosts(postID);
            return users;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }

    }

    @Override
    public void Save(NotificationHistory notificationHistory) throws Exception {
        try {
            notificationHistoryRepository.save(notificationHistory);

        } catch (Exception e) {
            throw new Exception("unable to save");
        }
    }

    @Override
    public List<NotificationResponse> findPostsResponsebyUser(int userID) {

        List<Post> posts = findPostsbyUser(userID);

        List<NotificationResponse> notificationResponses = new ArrayList<>(posts.size());
        posts.stream().forEach((Post p) -> notificationResponses.add(new NotificationResponse(p)));
        return notificationResponses;
    }

}

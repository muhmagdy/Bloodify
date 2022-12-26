package com.bloodify.backend.notification.dao.Interfaces;

import java.util.List;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.notification.model.NotificationHistory;


public interface NotificationHistoryDAO {
    public List<NotificationHistory> getNotifications();
    public boolean deleteNotificationByPost(int postID);
    public List<Post> findPostsbyUser(int userID);
    public List<User> findUsersbyPosts(int postID);
}

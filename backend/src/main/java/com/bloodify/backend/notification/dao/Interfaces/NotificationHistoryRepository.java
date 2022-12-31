package com.bloodify.backend.notification.dao.Interfaces;

import java.util.List;

import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.notification.model.NotificationHistory;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long> {
    
    @Query(value =
    "select p from User as u,Post as p,NotificationHistory as n"
    +" Where n.id.postID=p.postID and n.id.userID=u.userID and u.userID = :param",nativeQuery = false)
    // @Query(value = "SELECT p FROM post as p p " 
    // + " JOIN FETCH table1.table2 table2 "
    // + " WHERE table1.id = :table1Id" 
    // + "  AND table2.id = "
    // + "  ( SELECT table2.id FROM table2 " 
    // + "     WHERE table2.table1.id = :table1Id "
    // + "      AND table2.effectiveDateTime = "
    // + "      ( SELECT MAX(effectiveDateTime) FROM table2"
    // + "             WHERE table2.table1.id = :table1Id "
    // + "              AND table2.effectiveDateTime <= :currentTimestamp "
    // + "      ) "
    // + "  ) ")
 
    public List<Post> findPostsbyUser(@Param("param") int userid);
    @Query(value =
    "select p from User as u,Post as p,NotificationHistory as n"
    +" Where n.id.postID=p.postID and n.id.userID=u.userID and p.postID = :param",nativeQuery = false)
    public List<User> findUsersbyPosts(@Param("param") int postid);
    @Query(value = "delete from notification_history n where n.postid=:postid" , nativeQuery = true)
    public List<Post> deletePostNotification(@Param("postid") int postid);
}

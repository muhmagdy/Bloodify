//package com.bloodify.backend.UserRequests.repository.interfaces;
//
//import com.bloodify.backend.AccountManagement.model.entities.User;
//import com.bloodify.backend.UserRequests.model.entities.Accept;
//import com.bloodify.backend.UserRequests.model.entities.Post;
//import com.bloodify.backend.UserRequests.model.entities.RequestAccept;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface RequestAcceptRepository extends JpaRepository<Accept, Integer> {
//
//
//    List<RequestAccept> findRequestAcceptsByRequester(User requester);
//
//    List<RequestAccept> findRequestAcceptsByAcceptor(User acceptor);
//
//    List<RequestAccept> findRequestAcceptsByPost(Post post);
//
//
//}

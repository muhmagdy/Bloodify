//package com.bloodify.backend.UserRequests.model.entities;
//
//import com.bloodify.backend.AccountManagement.model.entities.User;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//
//@Data
//@Entity
//@Table
//@NoArgsConstructor
//@AllArgsConstructor
//public class RequestAccept {
//    @Id
//    @GeneratedValue
//    Integer postID;
//
//    @NonNull
//    @ManyToOne(fetch = FetchType.LAZY)
//    User requester;
//
//    @NonNull
//    @ManyToOne(fetch = FetchType.LAZY)
//    User acceptor;
//
//    @NonNull
//    @ManyToOne(fetch = FetchType.LAZY)
//    Post post;
//}

package com.bloodify.backend.userRequestTests;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {
    private Random random;
    private RandomUserGenerations randomUserGenerations;

    public Randomizer() {
        this.random = new Random();
        this.randomUserGenerations = new RandomUserGenerations();
    }

    public List<Post> generateValidPost(){
        User user1 = randomUserGenerations.generateRandomUser();
        User user2 = randomUserGenerations.generateRandomUser();

        Institution institution1 = generateRandomInstitution();
        Institution institution2 = generateRandomInstitution();

        Post post1 = new Post(user1, institution1, 2, LocalDateTime.now(), LocalDateTime.now().plusDays(2), "A+");
        Post post2 = new Post(user2, institution2, 4, LocalDateTime.now(), LocalDateTime.now().plusDays(4), "AB-");
        List<Post> savedPosts = new ArrayList<>();
        savedPosts.add(post1); savedPosts.add(post2);

        User user3 = random.nextInt() % 2 == 0? user1 : randomUserGenerations.generateRandomUser();
        Institution institution3 = random.nextInt() % 2 == 0? institution1 : generateRandomInstitution();
        String bloodType = this.randomUserGenerations.generateBloodType();

        while (bloodType.equals(post1.getBloodType())) bloodType = this.randomUserGenerations.generateBloodType();
        Post post3 = new Post(user3, institution3, 3, LocalDateTime.now(), bloodType);
        savedPosts.add(post3);
        return savedPosts;
    }

    public List<Post> generateInValidPost(){
        User user1 = randomUserGenerations.generateRandomUser();
        User user2 = randomUserGenerations.generateRandomUser();

        Institution institution1 = generateRandomInstitution();
        Institution institution2 = generateRandomInstitution();

        Post post1 = new Post(user1, institution1, 2, LocalDateTime.now(), LocalDateTime.now().plusDays(2), "A+");
        Post post2 = new Post(user2, institution2, 4, LocalDateTime.now(), LocalDateTime.now().plusDays(5), "AB-");
        List<Post> savedPosts = new ArrayList<>();
        savedPosts.add(post1); savedPosts.add(post2);
        savedPosts.add(post2);
        return savedPosts;
    }

    public List<Post> generateSomeUserPosts(){
        User user1 = randomUserGenerations.generateRandomUser();
        Institution institution1 = generateRandomInstitution();
        Institution institution2 = generateRandomInstitution();
        Post post1 = new Post(user1, institution1, 2, LocalDateTime.now(), "A+");
        Post post2 = new Post(user1, institution2, 2, LocalDateTime.now(), "A+");
        Post post3 = new Post(user1, institution1, 2, LocalDateTime.now(), "AB+");
        List<Post> posts = new ArrayList<>();
        posts.add(post1); posts.add(post2); posts.add(post3);
        return posts;
    }

    public Institution generateRandomInstitution(){
        return new Institution(randomUserGenerations.generateName(10, 15), randomUserGenerations.generateEmail(5, 20),
        randomUserGenerations.generateLocations().substring(0, 10), randomUserGenerations.generateFloat(),
                randomUserGenerations.generateFloat(), randomUserGenerations.generatePassword(10));
    }
}

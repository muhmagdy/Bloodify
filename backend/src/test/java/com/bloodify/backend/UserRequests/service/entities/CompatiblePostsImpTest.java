//package com.bloodify.backend.UserRequests.service.entities;
//
//import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
//import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
//import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionRepository;
//import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
//import com.bloodify.backend.AccountManagement.dao.interfaces.UserRepository;
//import com.bloodify.backend.AccountManagement.model.entities.Institution;
//import com.bloodify.backend.AccountManagement.model.entities.User;
//import com.bloodify.backend.UserRequests.model.entities.Post;
//import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
//import com.bloodify.backend.UserRequests.service.interfaces.CompatiblePosts;
//import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
//import com.bloodify.backend.userRequestTests.Randomizer;
//import jakarta.annotation.Resource;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import org.junit.jupiter.api.*;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//
//class CompatiblePostsImpTest {
//    @Autowired
//    private CompatiblePostsImp compatible;
//
//    @Resource(name = "userDAOImp")
//    UserDAO userDAO;
//
//    @Resource(name = "institutionDAOImp")
//    InstitutionDAO institutionDAO;
//
//    @Resource(name = "postDaoImp")
//    PostDao postDao;
//
//    @Resource()
//    UserRepository userRepository;
//
//    @Resource()
//    InstitutionRepository instRepository;
//
//    @Resource()
//    PostRepository postRepository;
//
//    static Double[] userLongitudes = {29.882137, 29.885613, 29.894327, 29.933361};
//    static Double[] userLatitudes  = {31.210453, 31.213481, 31.199945, 31.217912};
//    static Double[] instLong = {29.892540, 29.925540};
//    static Double[] instLat = {31.197730, 31.207730};    // represents Andalusia Al Shalalat Hospital
//    static String[] bloodTypes = {"A-", "B-", "AB-", "B+"};
//    static User[] users = new User[4];
//    static User universalDonor = new User();
//    static Institution[] institution = new Institution[2];
//    static Post[] posts = new Post[8];
//    static User[] usersForPosts = new User[8];
////    static Post universalAcceptor = new Post();
//
//
//    @BeforeAll
//    public static void setters() {
//
//        RandomUserGenerations randomUser = new RandomUserGenerations();
//        for(int i=0; i<4; i++) {
//            users[i] = new User();
//            users[i] = randomUser.generateRandomUser();
//            users[i].setBloodType(bloodTypes[i]);
//            users[i].setLongitude(userLongitudes[i]);
//            users[i].setLatitude(userLatitudes[i]);
//        }
//
////        Inserting users for posts
//        String[] requestedBloodTypes = {"A-", "B-", "A-", "AB-", "A-", "O+", "B-", "AB-"};
//        for(int i=0; i<8; i++) {
//            usersForPosts[i] = new User();
//            usersForPosts[i] = randomUser.generateRandomUser();
//            usersForPosts[i].setBloodType(requestedBloodTypes[i]);
//        }
//
//        universalDonor = randomUser.generateRandomUser();
//        universalDonor.setBloodType("O-"); // can give blood to everybody
//        universalDonor.setLongitude(userLongitudes[1]);
//        universalDonor.setLatitude(userLongitudes[1]);
//
//
//        RandomUserGenerations randomInstitution = new RandomUserGenerations();
//        for(int i=0; i<institution.length; i++) {
//            institution[i] = new Institution();
//            institution[i] = randomInstitution.generateRandomInstitution();
//            institution[i].setLongitude(instLong[i]);
//            institution[i].setLatitude(instLat[i]);
//            int allBagsCount = 10;
//            institution[i].setNegativeA_bagsCount(allBagsCount);
//            institution[i].setPositiveA_bagsCount(allBagsCount);
//            institution[i].setPositiveB_bagsCount(allBagsCount);
//            institution[i].setNegativeB_bagsCount(allBagsCount);
//            institution[i].setPositiveAB_bagsCount(allBagsCount);
//            institution[i].setNegativeAB_bagsCount(allBagsCount);
//            institution[i].setPositiveO_bagsCount(allBagsCount);
//            institution[i].setNegativeO_bagsCount(allBagsCount);
//        }
//
//        Randomizer randomPost = new Randomizer();
//        for(int i=0; i<8; i++) {
//            List<Post> generatedPost = randomPost.generateValidPost();
//            Post chosenPost = generatedPost.get(2);
//            if(i<4)
//                chosenPost.setInstitution(institution[0]);
//            else
//                chosenPost.setInstitution(institution[1]);
//            chosenPost.setUser(usersForPosts[i]);
//            chosenPost.setBloodType(requestedBloodTypes[i]);
//            posts[i] = chosenPost;
//        }
//    }
//
//    @Test
//    @Order(3)
//    void storePosts() {
//        this.userRepository.deleteAll();
//        this.instRepository.deleteAll();
//        this.postRepository.deleteAll();
//
//        for (User user : users) {
//            userDAO.saveUser(user);
//        }
//        for (Institution value : institution) {
//            institutionDAO.saveInstitution(value);
//        }
//        for(int i=0; i<posts.length; i++) {
//            userDAO.saveUser(usersForPosts[i]);
//            postRepository.save(posts[i]);
////            postDao.addPost(posts[i]);
//        }
//    }
//
////  Testing Distance between our unique institution and each of the other 4 normal users.
//    @Test
//    void distance1() {
//        int calculatedDist = compatible.distance(userLatitudes[0], userLongitudes[0], instLat[0], instLong[0]);
//        int actualDistanceRounded = 2; // result got on the online calculator
//        assertEquals(calculatedDist, actualDistanceRounded);
//        System.out.println("calculated distance = " + calculatedDist + " km\nactual distance: " + actualDistanceRounded + " km");
//    }
//    @Test
//    void distance2() {
//        int calculatedDist = compatible.distance(userLatitudes[1], userLongitudes[1], instLat[0], instLong[0]);
//        int actualDistanceRounded = 2; // result got on the online calculator
//        assertEquals(calculatedDist, actualDistanceRounded);
//        System.out.println("calculated distance = " + calculatedDist + " km\nactual distance: " + actualDistanceRounded + " km");
//    }
//    @Test
//    void distance3() {
//        int calculatedDist = compatible.distance(userLatitudes[2], userLongitudes[2], instLat[0], instLong[0]);
//        int actualDistanceRounded = 0; // result got on the online calculator
//        assertEquals(calculatedDist, actualDistanceRounded);
//        System.out.println("calculated distance = " + calculatedDist + " km\nactual distance: " + actualDistanceRounded + " km");
//    }
//    @Test
//    void distance4() {
//        int calculatedDist = compatible.distance(userLatitudes[3], userLongitudes[3], instLat[0], instLong[0]);
//        int actualDistanceRounded = 4; // result got on the online calculator
//        assertEquals(calculatedDist, actualDistanceRounded);
//        System.out.println("calculated distance = " + calculatedDist + " km\nactual distance: " + actualDistanceRounded + " km");
//    }
//
//    @Test
//    void distance5() {
//        int calculatedDist = compatible.distance(userLatitudes[0], userLongitudes[0], instLat[1], instLong[1]);
//        int actualDistanceRounded = 4; // result got on the online calculator
//        assertEquals(calculatedDist, actualDistanceRounded);
//        System.out.println("calculated distance = " + calculatedDist + " km\nactual distance: " + actualDistanceRounded + " km");
//    }
//    @Test
//    void distance6() {
//        int calculatedDist = compatible.distance(userLatitudes[1], userLongitudes[1], instLat[1], instLong[1]);
//        int actualDistanceRounded = 4; // result got on the online calculator
//        assertEquals(calculatedDist, actualDistanceRounded);
//        System.out.println("calculated distance = " + calculatedDist + " km\nactual distance: " + actualDistanceRounded + " km");
//    }
//    @Test
//    void distance7() {
//        int calculatedDist = compatible.distance(userLatitudes[2], userLongitudes[2], instLat[1], instLong[1]);
//        int actualDistanceRounded = 3; // result got on the online calculator
//        assertEquals(calculatedDist, actualDistanceRounded);
//        System.out.println("calculated distance = " + calculatedDist + " km\nactual distance: " + actualDistanceRounded + " km");
//    }
//    @Test
//    void distance8() {
//        int calculatedDist = compatible.distance(userLatitudes[3], userLongitudes[3], instLat[1], instLong[1]);
//        int actualDistanceRounded = 1; // result got on the online calculator
//        assertEquals(calculatedDist, actualDistanceRounded);
//        System.out.println("calculated distance = " + calculatedDist + " km\nactual distance: " + actualDistanceRounded + " km");
//    }
//
//
//    @Test
//    @Order(4)
//    void getAllPostsMatchingBloodType1() {
//        List<Post> gotPosts = compatible.getAllPostsMatchingBloodType(users[0].getBloodType());
//        assertEquals(3, gotPosts.size());
//    }
//    @Test
//    @Order(4)
//    void getAllPostsMatchingBloodType2() {
//        List<Post> gotPosts = compatible.getAllPostsMatchingBloodType(users[1].getBloodType());
//        assertEquals(gotPosts.size(), 2);
//    }
//    @Test
//    @Order(4)
//    void getAllPostsMatchingBloodType3() {
//        List<Post> gotPosts = compatible.getAllPostsMatchingBloodType(users[2].getBloodType());
//        assertEquals(gotPosts.size(), 2);
//    }
//    @Test
//    @Order(4)
//    void getAllPostsMatchingBloodType4() {
//        List<Post> gotPosts = compatible.getAllPostsMatchingBloodType(users[3].getBloodType());
//        assertEquals(gotPosts.size(), 0);
//    }
//    @Test
//    @Order(4)
//    void getAllPostsMatchingBloodType5() {
//        List<Post> gotPosts = compatible.getAllPostsMatchingBloodType(universalDonor.getBloodType());
//        assertEquals(0, gotPosts.size());
//    }
//
//    @Test
//    void isPostMatchingUserDistance1() {
//        assertTrue(compatible.isPostMatchingUserDistance(users[0], posts[0], 3));
//    }
//    @Test
//    void isPostMatchingUserDistance2() {
//        assertTrue(compatible.isPostMatchingUserDistance(users[1], posts[0], 3));
//    }
//    @Test
//    void isPostMatchingUserDistance3() {
//        assertTrue(compatible.isPostMatchingUserDistance(users[2], posts[0], 3));
//    }
//    @Test
//    void isPostMatchingUserDistance4() {
////      4 not <= 3
//        assertFalse(compatible.isPostMatchingUserDistance(users[3], posts[0], 3));
//    }
//
//    @Test
//    @Order(4)
//    void allPostsMatching1() {
//        User user = userDAO.findUserByEmailJoin(users[0].getEmail());
//        int threshold = 3;
//        List<Post> allPostsMatching = compatible.allPostsMatching(user, threshold);
//        List<Post> expected = new ArrayList<>();
//        expected.add(posts[0]);     expected.add(posts[2]);
//        assertEquals(expected.size(), allPostsMatching.size());
//
//        for(int i=0; i<expected.size(); i++)
//            assertEquals(expected.get(i).getPostID(), allPostsMatching.get(i).getPostID());
//    }
//    @Test
//    @Order(4)
//    void allPostsMatching2() {
//        User user = users[1];
//        int threshold = 3;
//        List<Post> allPostsMatching = compatible.allPostsMatching(user, threshold);
//        List<Post> expected = new ArrayList<>();
//        expected.add(posts[1]);
//        assertEquals(expected.size(), allPostsMatching.size());
//
//        for(int i=0; i<expected.size(); i++)
//            assertEquals(expected.get(i).getPostID(), allPostsMatching.get(i).getPostID());
//    }
//    @Test
//    @Order(4)
//    void allPostsMatching3() {
//        User user = users[2];
//        int threshold = 3;
//        List<Post> allPostsMatching = compatible.allPostsMatching(user, threshold);
//        List<Post> expected = new ArrayList<>();
//        expected.add(posts[3]);     expected.add(posts[7]);
//        assertEquals(expected.size(), allPostsMatching.size());
//
//        for(int i=0; i<expected.size(); i++)
//            assertEquals(expected.get(i).getPostID(), allPostsMatching.get(i).getPostID());
//    }
//    @Test
//    @Order(4)
//    void allPostsMatching4() {
//        User user = users[3];
//        int threshold = 3;
//        List<Post> allPostsMatching = compatible.allPostsMatching(user, threshold);
//        List<Post> expected = new ArrayList<>();
//        assertEquals(expected, allPostsMatching);
//
//        for(int i=0; i<expected.size(); i++)
//            assertEquals(expected.get(i).getPostID(), allPostsMatching.get(i).getPostID());
//    }
//
//}
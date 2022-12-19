package com.bloodify.backend.UserRequests.service.entities;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import com.bloodify.backend.UserRequests.service.interfaces.CompatiblePosts;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import com.bloodify.backend.userRequestTests.Randomizer;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class CompatiblePostsImpTest {
    @Resource(name = "compatiblePostsImp")
    private CompatiblePostsImp compatible;
    @Mock
    PostRepository postRepository;
    @Mock
    UserDAO userDAO;
    @Mock
    InstitutionDAO institutionDAO;
//    @Mock
//    @Resource(name = "postDaoImp")
    @InjectMocks
    PostDao postDao = new PostDaoImp();

    static Double[] userLongitudes = {29.882137, 29.885613, 29.894327, 29.933361};
    static Double[] userLatitudes  = {31.210453, 31.213481, 31.199945, 31.217912};
    static Double instLong = 29.892540;    static Double instLat = 31.197730;    // represents Andalusia Al Shalalat Hospital
    static String[] bloodTypes = {"A+", "B-", "AB-", "B+"};
    static User[] users = new User[4];
    static User universalDonor = new User();
    static Institution institution;
    static Post[] posts = new Post[8];
//    static Post universalAcceptor = new Post();


    @BeforeAll
    public static void setters() {

        RandomUserGenerations randomUser = new RandomUserGenerations();
        for(int i=0; i<4; i++) {
            users[i] = new User();
            users[i] = randomUser.generateRandomUser();
            users[i].setBloodType(bloodTypes[i]);
            users[i].setLongitude(userLongitudes[i]);
            users[i].setLatitude(userLatitudes[i]);
        }
        universalDonor = randomUser.generateRandomUser();
        universalDonor.setBloodType("O-"); // can give blood to everybody
        universalDonor.setLongitude(userLongitudes[1]);
        universalDonor.setLatitude(userLongitudes[1]);


        RandomUserGenerations randomInstitution = new RandomUserGenerations();
        institution = new Institution();
        institution = randomInstitution.generateRandomInstitution();
        institution.setLongitude(instLong);
        institution.setLatitude(instLat);
        int allBagsCount = 10;
        institution.setNegativeA_bagsCount(allBagsCount);
        institution.setPositiveA_bagsCount(allBagsCount);
        institution.setPositiveB_bagsCount(allBagsCount);
        institution.setNegativeB_bagsCount(allBagsCount);
        institution.setPositiveAB_bagsCount(allBagsCount);
        institution.setNegativeAB_bagsCount(allBagsCount);
        institution.setPositiveO_bagsCount(allBagsCount);
        institution.setNegativeO_bagsCount(allBagsCount);

        Randomizer randomPost = new Randomizer();
        String[] requestedBloodTypes = {"A+", "B-", "A+", "AB-", "A+", "O+", "B-", "O-"};
        for(int i=0; i<8; i++) {
            List<Post> generatedPost = randomPost.generateValidPost();
            Post chosenPost = generatedPost.get(2);
            chosenPost.setInstitution(institution);
            chosenPost.setBloodType(requestedBloodTypes[i]);
            posts[i] = chosenPost;
        }
//        universalAcceptor = randomPost.generateValidPost().get(2);
//        universalAcceptor.setInstitution(institution);
//        universalAcceptor.setBloodType("AB+"); // can take blood from everybody

    }

    @Test
    @Order(1)
    void storePosts() {
        for(int i=0; i<8; i++)
            postDao.addPost(posts[i]);
    }

//  Testing Distance between our unique institution and each of the other 4 normal users.
    @Test
    void distance1() {
        int calculatedDist = (int)Math.round(compatible.distance(userLatitudes[0], userLongitudes[0], instLat, instLong));
        int actualDistanceRounded = 2; // result got on the online calculator
        assertEquals(calculatedDist, actualDistanceRounded);
        System.out.println("calculated distance = " + calculatedDist + " km\nactual distance: " + actualDistanceRounded + " km");
    }
    @Test
    void distance2() {
        int calculatedDist = (int)Math.round(compatible.distance(userLatitudes[1], userLongitudes[1], instLat, instLong));
        int actualDistanceRounded = 2; // result got on the online calculator
        assertEquals(calculatedDist, actualDistanceRounded);
        System.out.println("calculated distance = " + calculatedDist + " km\nactual distance: " + actualDistanceRounded + " km");
    }
    @Test
    void distance3() {
        int calculatedDist = (int)Math.round(compatible.distance(userLatitudes[2], userLongitudes[2], instLat, instLong));
        int actualDistanceRounded = 0; // result got on the online calculator
        assertEquals(calculatedDist, actualDistanceRounded);
        System.out.println("calculated distance = " + calculatedDist + " km\nactual distance: " + actualDistanceRounded + " km");
    }
    @Test
    void distance4() {
        int calculatedDist = (int)Math.round(compatible.distance(userLatitudes[3], userLongitudes[3], instLat, instLong));
        int actualDistanceRounded = 4; // result got on the online calculator
        assertEquals(calculatedDist, actualDistanceRounded);
        System.out.println("calculated distance = " + calculatedDist + " km\nactual distance: " + actualDistanceRounded + " km");
    }


//    @Test
//    @Order(2)
//    void getAllPostsMatchingBloodType1() {
//        List<Post> gotPosts = compatible.getAllPostsMatchingBloodType(users[0].getBloodType());
//        assertEquals(gotPosts.size(), 3);
//    }
//    @Test
//    @Order(2)
//    void getAllPostsMatchingBloodType2() {
//        List<Post> gotPosts = compatible.getAllPostsMatchingBloodType(users[1].getBloodType());
//        assertEquals(gotPosts.size(), 2);
//    }
//    @Test
//    @Order(2)
//    void getAllPostsMatchingBloodType3() {
//        List<Post> gotPosts = compatible.getAllPostsMatchingBloodType(users[2].getBloodType());
//        assertEquals(gotPosts.size(), 1);
//    }
//    @Test
//    @Order(2)
//    void getAllPostsMatchingBloodType4() {
//        List<Post> gotPosts = compatible.getAllPostsMatchingBloodType(users[3].getBloodType());
//        assertEquals(gotPosts.size(), 0);
//    }
//    @Test
//    @Order(2)
//    void getAllPostsMatchingBloodType5() {
//        List<Post> gotPosts = compatible.getAllPostsMatchingBloodType(universalDonor.getBloodType());
//        assertEquals(gotPosts.size(), 8);
//    }

    @Test
    void isPostMatchingUserDistance1() {
        assertTrue(compatible.isPostMatchingUserDistance(users[0], posts[0], 3));
    }
    @Test
    void isPostMatchingUserDistance2() {
        assertTrue(compatible.isPostMatchingUserDistance(users[1], posts[0], 3));
    }
    @Test
    void isPostMatchingUserDistance3() {
        assertTrue(compatible.isPostMatchingUserDistance(users[2], posts[0], 3));
    }
    @Test
    void isPostMatchingUserDistance4() {
//      4 not <= 3
        assertFalse(compatible.isPostMatchingUserDistance(users[3], posts[0], 3));
    }
}
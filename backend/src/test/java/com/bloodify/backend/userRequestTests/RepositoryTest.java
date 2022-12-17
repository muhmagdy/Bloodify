package com.bloodify.backend.userRequestTests;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Resource(name = "userDAOImp")
    @Mock
    UserDAO userDAO;

    @Resource(name = "institutionDAOImp")
    @Mock
    private InstitutionDAO instDao;

    private final Randomizer randomizer = new Randomizer();
    private final RandomUserGenerations userGenerations = new RandomUserGenerations();


    @AfterEach
    public void tear_down(){
        this.postRepository.deleteAll();
    }
    @Test
    void doesSavingWork(){
        User user1 = userGenerations.generateRandomUser();
        Institution institution1 = randomizer.generateRandomInstitution();
        Post post1 = new Post(user1, institution1, 5, LocalDateTime.now(), "A+");
        this.userDAO.saveUser(user1);
        this.instDao.saveInstitution(institution1);
        this.postRepository.save(post1);

        Post got = this.postRepository.findPostByUserAndInstitutionAndBloodType(post1.getUser(), post1.getInstitution(),
                                                                                post1.getBloodType());
        assertNotNull(got);
        assertEquals(got.getUser().getUserID(), post1.getUser().getUserID());
    }

    @Test
    void getPostByUser(){
        User user1 = userGenerations.generateRandomUser();
        Institution institution1 = randomizer.generateRandomInstitution();
        User user2 = userGenerations.generateRandomUser();
        Institution institution2 = randomizer.generateRandomInstitution();
        Post post1 = new Post(user1, institution1, 5, LocalDateTime.now(), "A+");
        Post post2 = new Post(user2, institution2, 5, LocalDateTime.now(), "A-");

        this.userDAO.saveUser(user1); this.userDAO.saveUser(user2);
        this.instDao.saveInstitution(institution1); this.instDao.saveInstitution(institution2);
        this.postRepository.save(post1); this.postRepository.save(post2);

        Post got = this.postRepository.findPostsByUser(user1).get(0);

        assertNotNull(got);
        assertEquals(got.getUser().getUserID(), post1.getUser().getUserID());
        assertNotEquals(got.getUser().getUserID(), post2.getUser().getUserID());
    }

    @Test
    void getPostByInstitution(){
        User user1 = userGenerations.generateRandomUser();
        Institution institution1 = randomizer.generateRandomInstitution();
        User user2 = userGenerations.generateRandomUser();
        Institution institution2 = randomizer.generateRandomInstitution();
        Post post1 = new Post(user1, institution1, 5, LocalDateTime.now(), "A+");
        Post post2 = new Post(user2, institution2, 5, LocalDateTime.now(), "A-");

        this.userDAO.saveUser(user1); this.userDAO.saveUser(user2);
        this.instDao.saveInstitution(institution1); this.instDao.saveInstitution(institution2);
        this.postRepository.save(post1); this.postRepository.save(post2);

        Post got = this.postRepository.findPostsByInstitution(institution1).get(0);

        assertNotNull(got);
        assertEquals(got.getInstitution().getInstitutionID(), post1.getInstitution().getInstitutionID());
        assertNotEquals(got.getInstitution().getInstitutionID(), post2.getInstitution().getInstitutionID());
    }

    @Test
    void getPostByBloodType(){
        User user1 = userGenerations.generateRandomUser();
        Institution institution1 = randomizer.generateRandomInstitution();
        User user2 = userGenerations.generateRandomUser();
        Institution institution2 = randomizer.generateRandomInstitution();
        Post post1 = new Post(user1, institution1, 5, LocalDateTime.now(), "A+");
        Post post2 = new Post(user2, institution2, 5, LocalDateTime.now(), "A-");

        this.userDAO.saveUser(user1); this.userDAO.saveUser(user2);
        this.instDao.saveInstitution(institution1); this.instDao.saveInstitution(institution2);
        this.postRepository.save(post1); this.postRepository.save(post2);

        Post got = this.postRepository.findAllByBloodType("A+").get(0);

        assertNotNull(got);
        assertEquals(got.getInstitution().getInstitutionID(), post1.getInstitution().getInstitutionID());
        assertNotEquals(got.getInstitution().getInstitutionID(), post2.getInstitution().getInstitutionID());
    }

    @Test
    void deleteRedundantPosts(){
        User user1 = userGenerations.generateRandomUser();
        Institution institution1 = randomizer.generateRandomInstitution();
        User user2 = userGenerations.generateRandomUser();
        Institution institution2 = randomizer.generateRandomInstitution();
        Post post1 = new Post(user1, institution1, 1, LocalDateTime.now().minusDays(10), "A+");
        Post post2 = new Post(user2, institution2, 2, LocalDateTime.now(), "A-");

        this.userDAO.saveUser(user1); this.userDAO.saveUser(user2);
        this.instDao.saveInstitution(institution1); this.instDao.saveInstitution(institution2);
        this.postRepository.save(post1); this.postRepository.save(post2);

        this.postRepository.deletePostsByStartTimeBeforeOrBagsNum(LocalDateTime.now().minusDays(7), 0);

        assertEquals(this.postRepository.findPostsByUser(user1).size(), 0);
        assertNotNull(this.postRepository.findPostsByUser(user2).get(0));
    }

    @Test
    void deleteSpecificPosts(){
        User user1 = userGenerations.generateRandomUser();
        Institution institution1 = randomizer.generateRandomInstitution();
        User user2 = userGenerations.generateRandomUser();
        Institution institution2 = randomizer.generateRandomInstitution();
        Post post1 = new Post(user1, institution1, 1, LocalDateTime.now().minusDays(10), "A+");
        Post post2 = new Post(user2, institution2, 2, LocalDateTime.now(), "A-");

        this.userDAO.saveUser(user1); this.userDAO.saveUser(user2);
        this.instDao.saveInstitution(institution1); this.instDao.saveInstitution(institution2);
        this.postRepository.save(post1); this.postRepository.save(post2);

        this.postRepository.deletePostByUserAndInstitutionAndBloodType(user1, institution1, "A+");

        assertEquals(this.postRepository.findPostsByUser(user1).size(), 0);
        assertNotNull(this.postRepository.findPostsByUser(user2).get(0));
    }

    @Test
    void modifySomePost(){
        User user1 = userGenerations.generateRandomUser();
        Institution institution1 = randomizer.generateRandomInstitution();
        User user2 = userGenerations.generateRandomUser();
        Institution institution2 = randomizer.generateRandomInstitution();
        Post post1 = new Post(user1, institution1, 1, LocalDateTime.now().minusDays(10), "A+");
        Post post2 = new Post(user2, institution2, 2, LocalDateTime.now(), "A-");

        this.userDAO.saveUser(user1); this.userDAO.saveUser(user2);
        this.instDao.saveInstitution(institution1); this.instDao.saveInstitution(institution2);
        this.postRepository.save(post1); this.postRepository.save(post2);
        this.postRepository.updatePostSet(institution2.getInstitutionID(), 5, "O-", post1.getPostID());
        post1 = this.postRepository.findPostByUserAndInstitutionAndBloodType(user1, institution2, "O-");
        assertNotNull(post1);
        assertEquals(post1.getBloodType(), "O-");
    }
}

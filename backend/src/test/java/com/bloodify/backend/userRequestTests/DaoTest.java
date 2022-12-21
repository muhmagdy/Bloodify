package com.bloodify.backend.userRequestTests;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.exceptions.InstitutionNotFoundException;
import com.bloodify.backend.UserRequests.exceptions.UserNotFoundException;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import com.bloodify.backend.UserRequests.service.entities.PostDaoImp;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DaoTest {
    @Mock
    PostRepository postRepository;
    @Mock
    UserDAO userDAO;
    @Mock
    InstitutionDAO institutionDAO;
    @InjectMocks
    PostDao postDaoToTest = new PostDaoImp();
    private final Randomizer randomizer = new Randomizer();
    @Test
    void addNewPost() { /// check post has no duplicate
        List<Post> savedPosts = randomizer.generateValidPost();
        Post post3 = savedPosts.get(2);
        savedPosts.remove(2);
        savedPosts = savedPosts.stream().filter(p -> p.getUser() == post3.getUser())
                        .filter(p -> p.getInstitution() == post3.getInstitution())
                        .filter(p -> p.getBloodType().equals(post3.getBloodType())).toList();
        Post duplicate = savedPosts.size() == 0? null : savedPosts.get(0);
        when(postRepository.findPostByUserAndInstitutionAndBloodType(post3.getUser(),
                post3.getInstitution(), post3.getBloodType())).thenReturn(duplicate);

        assertTrue(this.postDaoToTest.addPost(post3));
    }

    @Test
    void addDuplicatePost(){
        List<Post> savedPosts = randomizer.generateInValidPost();
        Post post3 = savedPosts.get(2);
        savedPosts.remove(2);
        savedPosts = savedPosts.stream().filter(p -> p.getUser() == post3.getUser())
                .filter(p -> p.getInstitution() == post3.getInstitution())
                .filter(p -> p.getBloodType().equals(post3.getBloodType())).toList();
        Post duplicate = savedPosts.size() == 0? null : savedPosts.get(0);
        when(postRepository.findPostByUserAndInstitutionAndBloodType(post3.getUser(),
                post3.getInstitution(), post3.getBloodType())).thenReturn(duplicate);
        assertFalse(this.postDaoToTest.addPost(post3));
    }

    @Test
    void updatePresentPost(){
        Post post = randomizer.generateValidPost().get(2);
        when(postRepository.findPostByUserAndInstitutionAndBloodType(post.getUser(), post.getInstitution(), post.getBloodType()))
                .thenReturn(post);
        post.setPostID(new Random().nextInt());
        assertTrue(this.postDaoToTest.updatePost(post));
    }

    @Test
    void getValidPostEmail(){
        Post post = randomizer.generateValidPost().get(2);
        post.setPostID(new Random().nextInt());
        when(this.postRepository.findByPostID(post.getPostID())).thenReturn(post);
        String email = this.postDaoToTest.getPostEmail(post.getPostID());
        assertEquals(email, post.getUser().getEmail());
    }

    @Test
    void getInValidPostEmail(){
        List<Post> posts = randomizer.generateValidPost();
        Post post = posts.get(2);
        post.setPostID(new Random().nextInt());
        when(this.postRepository.findByPostID(post.getPostID() + 1)).thenReturn(posts.get(1));
        String email = this.postDaoToTest.getPostEmail(post.getPostID());
        assertNotEquals(email, post.getUser().getEmail());
    }

    @Test
    void getSpecificValidPost(){
        List<Post> posts = randomizer.generateValidPost();
        Post post = posts.get(0);
        when(this.postRepository.findPostByUserAndInstitutionAndBloodType(post.getUser(), post.getInstitution(),
                post.getBloodType())).thenReturn(post);
        Post post1 = this.postDaoToTest.getSpecificUserPost(post);
        assertEquals(post.getInstitution(), post1.getInstitution());
    }

    @Test
    void getAllUserPosts(){
        List<Post> posts = randomizer.generateValidPost();
        posts.get(1).setUser(posts.get(0).getUser());
        posts.get(2).setUser(new User());
        when(this.userDAO.findUserByEmail(posts.get(0).getUser().getEmail())).thenReturn(posts.get(0).getUser());
        when(this.postRepository.findPostsByUser(posts.get(0).getUser())).thenReturn(
                posts.stream().filter(p -> p.getUser().equals(posts.get(0).getUser())).toList());
        List<Post> posts1 = this.postDaoToTest.getUserAllPosts(posts.get(0).getUser().getEmail());

        assertFalse(posts1.contains(posts.get(2)));
//        assertEquals(posts1.get(0).getUser().getUserID(), posts.get(0).getUser().getUserID());
        assertTrue(posts1.contains(posts.get(1)));
        assertTrue(posts1.contains(posts.get(0)));
    }

    @Test
    void getAllUserPostsWrong(){
        List<Post> posts = randomizer.generateValidPost();
        posts.get(1).setUser(posts.get(0).getUser());
        posts.get(2).setUser(new User());
        when(this.userDAO.findUserByEmail("")).thenReturn(null);
        when(this.postRepository.findPostsByUser(null)).thenThrow(new UserNotFoundException());
        List<Post> posts1 = this.postDaoToTest.getUserAllPosts(posts.get(0).getUser().getEmail());
        assertNull(posts1);
    }

    @Test
    void getAllInstitutionPosts(){
        List<Post> posts = randomizer.generateValidPost();
        posts.get(1).setInstitution(posts.get(0).getInstitution());
        posts.get(2).setInstitution(new Institution());
        when(this.institutionDAO.findInstitutionByID(posts.get(0).getInstitution().getInstitutionID()))
                .thenReturn(posts.get(0).getInstitution());
        when(this.postRepository.findPostsByInstitution(posts.get(0).getInstitution())).thenReturn(
                posts.stream().filter(p -> p.getInstitution().equals(posts.get(0).getInstitution())).toList());
        List<Post> posts1 = this.postDaoToTest.getInstitutionAllPosts(posts.get(0).getInstitution().getInstitutionID());
        System.out.println();
        assertFalse(posts1.contains(posts.get(2)));
        assertTrue(posts1.contains(posts.get(1)));
        assertTrue(posts1.contains(posts.get(0)));
    }

    @Test
    void getAllInstitutionPostsWrong(){
        List<Post> posts = randomizer.generateValidPost();
        posts.get(1).setInstitution(posts.get(0).getInstitution());
        posts.get(2).setInstitution(new Institution());
        when(this.institutionDAO.findInstitutionByID(posts.get(0).getInstitution().getInstitutionID()))
                .thenReturn(null);
        when(this.postRepository.findPostsByInstitution(null)).thenThrow(new InstitutionNotFoundException());
        List<Post> posts1 = this.postDaoToTest.getInstitutionAllPosts(posts.get(0).getInstitution().getInstitutionID());
        System.out.println();
        assertNull(posts1);
    }

}
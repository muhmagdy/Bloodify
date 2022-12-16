package com.bloodify.backend.userRequestTests;

import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import com.bloodify.backend.UserRequests.service.entities.PostDaoImp;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class PostDaoTest {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostDao postDaoToTest = new PostDaoImp();

    private Randomizer randomizer = new Randomizer();

//    @BeforeEach
//    void set_up(){
//        postDaoToTest = new PostDaoImp();
//    }
//
//    @AfterEach
//    void tear_down(){
//
//    }
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

}
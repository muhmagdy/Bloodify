package com.bloodify.backend.userRequestTests;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.UserRequests.dto.entities.PostDto;
import com.bloodify.backend.UserRequests.exceptions.InstitutionNotFoundException;
import com.bloodify.backend.UserRequests.exceptions.UserNotFoundException;
import com.bloodify.backend.UserRequests.model.mapper.Post_DTO_Mapper;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodTypeFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@SpringBootTest
public class PostDtoTest {
    @Mock
    UserDAO userDao;
    @Mock
    InstitutionDAO institutionDAO;
    @InjectMocks
    Post_DTO_Mapper mapperToTest;

    BloodTypeFactory factory = BloodTypeFactory.getFactory();
    RandomUserGenerations generations  = new RandomUserGenerations();
    Randomizer randomizer = new Randomizer();

    @Test
    void mapCorrectly(){
        PostDto dto = new PostDto(1, "Lamo@yaf.org", 8, 5,
                factory.generateFromString("O-"), LocalDateTime.now(), LocalDateTime.now().plusDays(3));
        when(userDao.findUserByEmail("Lamo@yaf.org")).thenReturn(generations.generateRandomUser());
        when(institutionDAO.findInstitutionByID(8)).thenReturn(randomizer.generateRandomInstitution());
        assertNotNull(mapperToTest.map_to_Post(dto));
    }

    @Test
    void mapWrong1(){
        PostDto dto = new PostDto(1, null, 8, 5,
                factory.generateFromString("O-"), LocalDateTime.now(), LocalDateTime.now().plusDays(3));
        when(userDao.findUserByEmail(null)).thenReturn(null);
        when(institutionDAO.findInstitutionByID(8)).thenReturn(randomizer.generateRandomInstitution());
        assertThrows(UserNotFoundException.class, () -> mapperToTest.map_to_Post(dto));
    }

    @Test
    void mapWrong2(){
        PostDto dto = new PostDto(1, "Lamo@yaf.org", 8, 5,
                factory.generateFromString("O-"), LocalDateTime.now(), LocalDateTime.now().plusDays(3));
        when(userDao.findUserByEmail("Lamo@yaf.org")).thenReturn(generations.generateRandomUser());
        when(institutionDAO.findInstitutionByID(8)).thenReturn(null);
        assertThrows(InstitutionNotFoundException.class, () -> mapperToTest.map_to_Post(dto));
    }
}

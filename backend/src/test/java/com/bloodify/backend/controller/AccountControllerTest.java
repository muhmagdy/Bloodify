package com.bloodify.backend.controller;

import com.bloodify.backend.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.model.entities.User;
import com.bloodify.backend.model.responses.UserSignUpResponse;
import com.bloodify.backend.services.exceptions.BothEmailAndNationalIdExists;
import com.bloodify.backend.services.exceptions.EmailExistsException;
import com.bloodify.backend.services.exceptions.NationalIdExistsException;
import com.bloodify.backend.services.interfaces.AccountManagerService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.*;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AllArgsConstructor
@Getter
@Setter
class UserWithNoEmail{
    private String firstName, lastName, nationalID, bloodType, password;
    private boolean hasDiseases;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastTimeDonated;
}
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AccountManagerService accountManagerService;

    RandomUserGenerations random = new RandomUserGenerations();

    private User generateRandomUser(){
        return new User(random.generateName(5,10), random.generateName(5,10), random.generateNationalID(),
                random.generateEmail(10, 30), "A+", random.generateDiseases(),
                random.generateDate(1980, 2022), random.generatePassword(15));
    }

    /**
     * Checks if the controller listens to sign up http request
     */
    @Test
    void whenEmptySignupResponse_thenReturns422() throws Exception{
        mockMvc.perform(post("/api/v1/test/user").
                contentType("application/json")).
                andExpect(status().isUnprocessableEntity());
    }

    /**
     * Checks input deserialization
     */
    @Test
    void whenValidSignupInput_thenReturns201() throws Exception{
        User user = generateRandomUser();
        when(accountManagerService.signUpUser(user)).thenReturn(true);
        mockMvc.perform(post("/api/v1/test/user").
                contentType("application/json").
                content(objectMapper.writeValueAsString(user))).
                andExpect(status().isCreated());
    }

    @Test
    void whenEmailIsMissingSignup() throws  Exception{
        UserWithNoEmail user = new UserWithNoEmail(random.generateName(5,10), random.generateName(5,10),
                random.generateNationalID(), "A+", random.generatePassword(15),
                random.generateDiseases(), random.generateDate(1980, 2022));
                mockMvc.perform(post("/api/v1/test/user").
                contentType("application/json").
                content(objectMapper.writeValueAsString(user))).
                andExpect(status().isUnprocessableEntity());
    }

    @Test
    void emailIsNullSignupTest() throws Exception{
        User user = new User(random.generateName(5,10), random.generateName(5,10), random.generateNationalID(),
                null, "A+", random.generateDiseases(),
                random.generateDate(1980, 2022), random.generatePassword(15));
        mockMvc.perform(post("/api/v1/test/user").
                contentType("application/json").
                content(objectMapper.writeValueAsString(user))).
                andExpect(status().isUnprocessableEntity());
    }

    @Test
    void validInputSignupTest() throws Exception{
        User user = generateRandomUser();
        mockMvc.perform(post("/api/v1/test/user").
                contentType("application/json").
                content(objectMapper.writeValueAsString(user)));
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(accountManagerService, times(1)).signUpUser(userCaptor.capture());
        assertEquals(userCaptor.getValue().getFirstName(), user.getFirstName());
        assertEquals(userCaptor.getValue().getLastName(), user.getLastName());
        assertEquals(userCaptor.getValue().getEmail(), user.getEmail());
        assertEquals(userCaptor.getValue().getNationalID(), user.getNationalID());
        assertEquals(userCaptor.getValue().getBloodType(), user.getBloodType());
        assertEquals(userCaptor.getValue().getPassword(), user.getPassword());
        assertEquals(userCaptor.getValue().isHasDiseases(), user.isHasDiseases());
        assertEquals(userCaptor.getValue().getLastTimeDonated(), user.getLastTimeDonated());
    }

    @Test
    void validInputSignupReturnsValidResponse() throws Exception{
        User user = generateRandomUser();
        when(accountManagerService.signUpUser(user)).thenReturn(true);
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/test/user").
                                contentType("application/json").
                                content(objectMapper.writeValueAsString(user))).
                                andExpect(status().isCreated()).
                                andReturn();
        String expected = objectMapper.writeValueAsString(new UserSignUpResponse(true, "Success"));
        String actual = mvcResult.getResponse().getContentAsString();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void whenEmailExistInSignup_thenEmailExIsThrown() throws Exception{
        User user = new User(random.generateName(5,10), random.generateName(5,10), random.generateNationalID(),
                "existing@email.com", "A+", random.generateDiseases(),
                random.generateDate(1980, 2022), random.generatePassword(15));
        when(accountManagerService.signUpUser(user)).thenThrow(new EmailExistsException());
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/test/user").
                contentType("application/json").
                content(objectMapper.writeValueAsString(user))).
                andExpect(status().isConflict()).
                andReturn();
        String expected = objectMapper.
                writeValueAsString(new UserSignUpResponse(false, "An account already exists with the same email."));
        String actual = mvcResult.getResponse().getContentAsString();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void whenNationalIDExistInSignup_thenNationalIDExIsThrown() throws Exception{
        User user = new User(random.generateName(5,10), random.generateName(5,10), "30000000000000",
                random.generateEmail(10,15), "A+", random.generateDiseases(),
                random.generateDate(1980, 2022), random.generatePassword(15));
        when(accountManagerService.signUpUser(user)).thenThrow(new NationalIdExistsException());
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/test/user").
                contentType("application/json").
                content(objectMapper.writeValueAsString(user))).
                andExpect(status().isConflict()).
                andReturn();
        String expected = objectMapper.
                writeValueAsString(
                        new UserSignUpResponse(false, "An account already exists with the same National ID."));
        String actual = mvcResult.getResponse().getContentAsString();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void whenEmailAndNationalIDExistInSignup_thenExIsThrown() throws Exception{
        User user = new User(random.generateName(5,10), random.generateName(5,10), "30000000000000",
                "exisiting@email.com", "A+", random.generateDiseases(),
                random.generateDate(1980, 2022), random.generatePassword(15));
        when(accountManagerService.signUpUser(user)).thenThrow(new BothEmailAndNationalIdExists());
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/test/user").
                contentType("application/json").
                content(objectMapper.writeValueAsString(user))).
                andExpect(status().isConflict()).
                andReturn();
        String expected = objectMapper.
                writeValueAsString(
                        new UserSignUpResponse(false, "An account already exists with the same email and national id."));
        String actual = mvcResult.getResponse().getContentAsString();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

}
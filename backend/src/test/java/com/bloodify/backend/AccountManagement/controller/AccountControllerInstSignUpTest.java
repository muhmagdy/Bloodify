package com.bloodify.backend.AccountManagement.controller;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.responses.SignUpResponse;
import com.bloodify.backend.AccountManagement.services.exceptions.EmailExistsException;
import com.bloodify.backend.AccountManagement.services.interfaces.AccountManagerService;
import com.bloodify.backend.UserRequests.model.request.PasswordChangeRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AllArgsConstructor
@Getter
@Setter
class InstWithNoEmail{
    private String name, locationEnglish, password;
    private float locationLatitude, locationLongitude;
}

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = AccountController.class)
class AccountControllerInstSignUpTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AccountManagerService accountManagerService;

    RandomUserGenerations random = new RandomUserGenerations();

    private Institution generateRandomInst() {
        return new Institution(random.generateName(5, 10), random.generateEmail(10, 30), random.generateName(10, 10),
                random.generateFloat(), random.generateFloat(), random.generatePassword(20));
    }

    /**
     * Checks if the controller listens to sign up http request
     */
    @Test
    void whenEmptySignupResponse_thenReturns422() throws Exception {
        mockMvc.perform(post("/api/v1/institution").
                contentType("application/json")).
                andExpect(status().isUnprocessableEntity());
    }

    /**
     * Checks input deserialization
     */
    @Test
    void whenValidSignupInput_thenReturns201() throws Exception {
        Institution Institution = generateRandomInst();
        when(accountManagerService.instSignUp(Institution)).thenReturn(true);
        mockMvc.perform(post("/api/v1/institution").
                contentType("application/json").
                content(objectMapper.writeValueAsString(Institution))).
                andExpect(status().isCreated());
    }

    @Test
    void whenEmailIsMissingSignup() throws Exception {
        InstWithNoEmail Institution = new InstWithNoEmail(random.generateName(5, 20), random.generateName(5, 20),
                random.generatePassword(20), random.generateFloat(), random.generateFloat());
        mockMvc.perform(post("/api/v1/institution").
                contentType("application/json").
                content(objectMapper.writeValueAsString(Institution))).
                andExpect(status().isUnprocessableEntity());
    }

    @Test
    void emailIsNullSignupTest() throws Exception {
        Institution Institution = new Institution(random.generateName(5, 10), null, random.generateName(10, 10),
                random.generateFloat(), random.generateFloat(), random.generatePassword(20));
        mockMvc.perform(post("/api/v1/institution").
                contentType("application/json").
                content(objectMapper.writeValueAsString(Institution))).
                andExpect(status().isUnprocessableEntity());
    }

    @Test
    void validInputSignupTest() throws Exception {
        Institution Institution = generateRandomInst();
        mockMvc.perform(post("/api/v1/institution").
                contentType("application/json").
                content(objectMapper.writeValueAsString(Institution)));
        ArgumentCaptor<Institution> InstitutionCaptor = ArgumentCaptor.forClass(Institution.class);
        verify(accountManagerService, times(1)).instSignUp(InstitutionCaptor.capture());
        assertEquals(InstitutionCaptor.getValue().getEmail(), Institution.getEmail());
//        assertEquals(InstitutionCaptor.getValue().getName(), Institution.getName());
        assertEquals(InstitutionCaptor.getValue().getPassword(), Institution.getPassword());
        assertEquals(InstitutionCaptor.getValue().getLocation(), Institution.getLocation());
        assertEquals(InstitutionCaptor.getValue().getLatitude(), Institution.getLatitude());
        assertEquals(InstitutionCaptor.getValue().getLongitude(), Institution.getLongitude());
    }

    @Test
    void validInputSignupReturnsValidResponse() throws Exception {
        Institution Institution = generateRandomInst();
        when(accountManagerService.instSignUp(Institution)).thenReturn(true);
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/institution").
                contentType("application/json").
                content(objectMapper.writeValueAsString(Institution))).
                andExpect(status().isCreated()).
                andReturn();
        String expected = objectMapper.writeValueAsString(new SignUpResponse(true, "Success"));
        String actual = mvcResult.getResponse().getContentAsString();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void whenEmailExistInSignup_thenEmailExIsThrown() throws Exception {
        Institution Institution = new Institution(random.generateName(5, 10), "existing@email.com",
                random.generateName(10, 10), random.generateFloat(), random.generateFloat(),
                random.generatePassword(20));
        when(accountManagerService.instSignUp(Institution)).thenThrow(new EmailExistsException());
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/institution").
                contentType("application/json").
                content(objectMapper.writeValueAsString(Institution))).
                andExpect(status().isConflict()).
                andReturn();
        String expected = objectMapper.
                writeValueAsString(new SignUpResponse(false, "An account already exists with the same email."));
        String actual = mvcResult.getResponse().getContentAsString();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void whenEmptyPasswordResponse_thenReturns422() throws Exception {
        mockMvc.perform(post("/api/v1/password").
                contentType("application/json")).
                andExpect(status().isUnprocessableEntity());
    }

    @Test
    void whenValidPasswordRequest_thenReturnTrue() throws Exception {
        String email = random.generateEmail(10,20);
        SignUpResponse response = new SignUpResponse(true, "Code sent successfully to " + email);
        when(accountManagerService.sendVerificationCode(email)).thenReturn(response);
        MvcResult mvcResult =
                mockMvc.perform(post("/api/v1/password")
                .contentType("application/json")
                        .content(objectMapper.writeValueAsString(email)))
                .andReturn();

        String expected =
                objectMapper.writeValueAsString(response);
        String actual = mvcResult.getResponse().getContentAsString();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void whenInvalidPasswordRequest_thenReturnFalse() throws Exception {
        String email = random.generateEmail(10,20);
        SignUpResponse response = new SignUpResponse(false, "Cannot send code. Please try again later.");
        when(accountManagerService.sendVerificationCode(email)).thenReturn(response);
        MvcResult mvcResult =
                mockMvc.perform(post("/api/v1/password")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(email)))
                        .andReturn();

        String expected =
                objectMapper.writeValueAsString(response);
        String actual = mvcResult.getResponse().getContentAsString();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void whenValidPasswordRequest_thenReturn200OK() throws Exception {
        String email = random.generateEmail(10,20);
        SignUpResponse response = new SignUpResponse(true, "Code sent successfully to " + email);
        when(accountManagerService.sendVerificationCode(email)).thenReturn(response);
        MvcResult mvcResult =
                mockMvc.perform(post("/api/v1/password")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(email)))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    @Test
    void whenEmptyPasswordResetResponse_thenReturns400() throws Exception {
        mockMvc.perform(post("/api/v1/password").
                contentType("application/json")).
                andExpect(status().isUnprocessableEntity());
    }

    @Test
    void whenPasswordResetCorrect_thenStateIsTrue() throws Exception {
        String email ="test@test.com";
        String code = "1234";
        String password = "1234567890@PASSWORD";
        PasswordChangeRequest passwordChangeRequest =
                new PasswordChangeRequest(email, code, password);
        when(accountManagerService.resetPassword(email, code, password)).thenReturn(true);
        MvcResult mvcResult = mockMvc.perform(patch("/api/v1/password").
                contentType("application/json").
                content(objectMapper.writeValueAsString(passwordChangeRequest))).
                andExpect(status().isOk()).
                andReturn();
        String expected = objectMapper.
                writeValueAsString(new SignUpResponse(true, "Password changed successfully"));
        String actual = mvcResult.getResponse().getContentAsString();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void whenPasswordResetIncorrect_thenStateIsFalse() throws Exception {
        String email ="test@test.com";
        String code = "1234";
        String password = "1234567890@PASSWORD";
        PasswordChangeRequest passwordChangeRequest =
                new PasswordChangeRequest(email, code, password);
        when(accountManagerService.resetPassword(email, code, password)).thenReturn(false);
        MvcResult mvcResult = mockMvc.perform(patch("/api/v1/password").
                contentType("application/json").
                content(objectMapper.writeValueAsString(passwordChangeRequest))).
                andExpect(status().isOk()).
                andReturn();
        String expected = objectMapper.
                writeValueAsString(new SignUpResponse(false, "Incorrect code, please try again."));
        String actual = mvcResult.getResponse().getContentAsString();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }
}
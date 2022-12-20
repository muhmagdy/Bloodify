package com.bloodify.backend.InstitutionManagement.controller.api;

import com.bloodify.backend.InstitutionManagement.service.interfaces.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = TransactionController.class)
class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    /**
     * Checks if the controller listens to instToUser donation http request
     * returns Bad Request -> 400 (it must not return not found 404)
     */
    @Test
    void instToUserDonEndpointExist() throws Exception {
        mockMvc.perform(post("/api/v1/institution/transaction/instToUser").
                        contentType("application/json")).
                andExpect(status().isBadRequest());
    }

    /**
     * Checks if the controller listens to userToUser donation http request
     * returns Bad Request -> 400 (it must not return not found 404)
     */
    @Test
    void userToUserDonEndpointExist() throws Exception {
        mockMvc.perform(post("/api/v1/institution/transaction/userToUser").
                        contentType("application/json")).
                andExpect(status().isBadRequest());
    }

    /**
     * Checks if the controller listens to userToInst donation http request
     * returns Bad Request -> 400 (it must not return not found 404)
     */
    @Test
    void userToInstDonEndpointExist() throws Exception {
        mockMvc.perform(post("/api/v1/institution/transaction/userToInst").
                        contentType("application/json")).
                andExpect(status().isBadRequest());
    }

}

package com.bloodify.backend.InstitutionManagement.controller.api;

import com.bloodify.backend.InstitutionManagement.service.interfaces.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = EventController.class)
class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    /**
     * Checks if the controller listens to create event http request
     * returns Bad Request -> 400 (it must not return not found 404)
     */
    @Test
    void createEventEndPointExist() throws Exception {
        mockMvc.perform(post("/api/v1/institution/event/createEvent").
                        contentType("application/json")).
                andExpect(status().isBadRequest());
    }

}
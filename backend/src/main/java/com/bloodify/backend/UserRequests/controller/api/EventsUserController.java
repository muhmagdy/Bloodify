package com.bloodify.backend.UserRequests.controller.api;

import com.bloodify.backend.InstitutionManagement.model.Event;
import com.bloodify.backend.InstitutionManagement.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin()
@RequestMapping("/api/v1/user")
@Controller
public class EventsUserController {
    @Autowired
    EventService eventService;


    @GetMapping("/events")
    List<Event> getEvents(Authentication auth){
        return eventService.getAllEvents();
    }

}

package com.bloodify.backend.InstitutionManagement.controller.api;

import com.bloodify.backend.InstitutionManagement.controller.request.EventRequest;
import com.bloodify.backend.InstitutionManagement.controller.response.EventResponse;
import com.bloodify.backend.InstitutionManagement.dto.mapper.EventDTOMapper;
import com.bloodify.backend.InstitutionManagement.exceptions.eventexceptions.EventException;
import com.bloodify.backend.InstitutionManagement.model.Event;
import com.bloodify.backend.InstitutionManagement.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1/institution/event")
public class EventController {
    @Autowired
    EventService eventService;

    @PostMapping("/createEvent")
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest eventRequest, Authentication auth) {
        int eventId = eventService.createEvent(new EventDTOMapper().mapToDTO(eventRequest,
                auth.getName()));

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new EventResponse(true, "Event Created Successfuly!", eventId));
    }

    @GetMapping("/getEvents")
    public ResponseEntity<List<Event>> getEvents(Authentication auth) {
        List<Event> events = eventService.getAllInstEvents(auth.getName());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(events);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(EventException.class)
    public EventResponse handleTransactionException(EventException exception) {
        return new EventResponse(false, exception.getMessage(), null);
    }
}

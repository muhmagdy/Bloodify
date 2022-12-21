package com.bloodify.backend.InstitutionManagement.service.interfaces;

import com.bloodify.backend.InstitutionManagement.dto.EventDTO;
import com.bloodify.backend.InstitutionManagement.model.Event;

import java.util.List;

public interface EventService {
    int createEvent(EventDTO eventDTO);

    List<Event> getAllInstEvents(String email);

    void delete();
}

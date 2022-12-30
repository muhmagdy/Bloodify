package com.bloodify.backend.InstitutionManagement.repository.interfaces;

import com.bloodify.backend.InstitutionManagement.model.Event;

import java.util.List;

public interface EventDAO {
    // saves the event
    int save(Event event);

    // find all the currently active events of a specified institution (by email)
    List<Event> findAllInstEvents(String email);

    List<Event> findAllEvents();

    // deletes all the expired events
    void deleteEvent();
}

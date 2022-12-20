package com.bloodify.backend.InstitutionManagement.repository.interfaces;

import com.bloodify.backend.InstitutionManagement.model.Event;

import java.util.List;

public interface EventDAO {
    boolean save(Event event);

    List<Event> findAllInstEvents(String email);
}

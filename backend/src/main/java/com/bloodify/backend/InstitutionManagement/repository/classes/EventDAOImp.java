package com.bloodify.backend.InstitutionManagement.repository.classes;

import com.bloodify.backend.InstitutionManagement.model.Event;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.EventDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventDAOImp implements EventDAO {
    @Autowired
    EventRepository eventRepository;

    @Override
    public boolean save(Event event) {
        try {
            eventRepository.save(event);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Event> findAllInstEvents(String email) {
        return eventRepository.findByInstitution_Email(email);
    }

}

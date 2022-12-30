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
    public int save(Event event) {
        return eventRepository.save(event).getEventID();
    }

    @Override
    public List<Event> findAllInstEvents(String email) {
        return eventRepository.findByInstitution_Email(email);
    }

    @Override
    public List<Event> findAllEvents(){
        return eventRepository.findByOrderByEndDateAsc();
    }

    @Override
    public void deleteEvent() {
        eventRepository.abortEvent();
    }

}

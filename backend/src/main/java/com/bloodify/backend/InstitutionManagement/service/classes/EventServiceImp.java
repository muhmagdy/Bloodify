package com.bloodify.backend.InstitutionManagement.service.classes;

import com.bloodify.backend.InstitutionManagement.dto.EventDTO;
import com.bloodify.backend.InstitutionManagement.model.Event;
import com.bloodify.backend.InstitutionManagement.model.mapper.EventModelMapper;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.EventDAO;
import com.bloodify.backend.InstitutionManagement.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImp implements EventService {
    @Autowired
    EventDAO eventDAO;

    @Autowired
    EventModelMapper eventModelMapper;

    @Override
    public int createEvent(EventDTO eventDTO) {
        return eventDAO.save(eventModelMapper.mapToModel(eventDTO));
    }

    @Override
    public List<Event> getAllInstEvents(String email) {
        return eventDAO.findAllInstEvents(email);
    }

    // this function is scheduled to be called every 120,000 ms -> 2 minutes
    // and deletes all the expired event
    // TODO Events might need to stay in the db so a flag can be added to each record
    @Override
    @Scheduled(fixedRate = 120000)
    public void delete() {
        eventDAO.deleteEvent();
    }

}

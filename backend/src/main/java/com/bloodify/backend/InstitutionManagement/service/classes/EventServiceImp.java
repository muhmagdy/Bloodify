package com.bloodify.backend.InstitutionManagement.service.classes;

import com.bloodify.backend.InstitutionManagement.dto.EventDTO;
import com.bloodify.backend.InstitutionManagement.dto.mapper.EventDTOMapper;
import com.bloodify.backend.InstitutionManagement.model.Event;
import com.bloodify.backend.InstitutionManagement.model.mapper.EventModelMapper;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.EventDAO;
import com.bloodify.backend.InstitutionManagement.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImp implements EventService {
    @Autowired
    EventDAO eventDAO;

    @Autowired
    EventModelMapper eventModelMapper;

    @Override
    public boolean createEvent(EventDTO eventDTO) {
        return eventDAO.save(eventModelMapper.mapToModel(eventDTO));
    }

    @Override
    public List<Event> getAllInstEvents(String email) {
        return eventDAO.findAllInstEvents(email);
    }

}

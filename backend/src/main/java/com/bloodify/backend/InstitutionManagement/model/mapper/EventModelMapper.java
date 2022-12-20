package com.bloodify.backend.InstitutionManagement.model.mapper;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.InstitutionManagement.dto.EventDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.eventexceptions.*;
import com.bloodify.backend.InstitutionManagement.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventModelMapper {
    @Autowired
    InstitutionDAO institutionDAO;

    public Event mapToModel(EventDTO eventDTO) {
        Event event = new Event();

        event.setInstitution(
                institutionDAO.findInstitutionByEmail(eventDTO.getInstitutionEmail())
        );

        if (event.getInstitution() == null)
            throw new InvalidInstitution();

        event.setTitle(eventDTO.getTitle());

        if (event.getTitle() == null
                || event.getTitle().length() < 5
                || event.getTitle().length() > 30)
            throw new InvalidTitle();

        event.setStartDate(eventDTO.getStartDate());

        if(event.getStartDate() == null)
            throw new InvalidDate();

        event.setEndDate(eventDTO.getEndDate());

        if(event.getEndDate() == null)
            throw new InvalidDate();

        event.setStartWorkingHour(eventDTO.getStartWorkingHour());

        if(event.getStartWorkingHour() == null)
            throw new InvalidWorkingHour();

        event.setEndWorkingHour(eventDTO.getEndWorkingHour());

        if(event.getEndWorkingHour() == null)
            throw new InvalidWorkingHour();

        event.setLocation(eventDTO.getLocation());

        if(event.getLocation() == null
                || event.getLocation().length() < 5
                || event.getLocation().length() > 30)
            throw new InvalidLocation();

        event.setLongitude(eventDTO.getLongitude());

        if(event.getLongitude() == null)
            throw new InvalidLongitude();

        event.setLatitude(eventDTO.getLatitude());

        if(event.getLatitude() == null)
            throw new InvalidLatitude();

        event.setDescription(eventDTO.getDescription());

        if(event.getDescription() != null
                && event.getDescription().length() > 200)
            throw new InvalidDescription();

        return event;
    }

}

package com.bloodify.backend.InstitutionManagement.dto.mapper;

import com.bloodify.backend.InstitutionManagement.controller.request.EventRequest;
import com.bloodify.backend.InstitutionManagement.dto.EventDTO;

public class EventDTOMapper {
    public EventDTO mapToDTO(EventRequest eventRequest, String email) {
        EventDTO eventDTO = new EventDTO();

        eventDTO.setTitle(eventRequest.getTitle());

        eventDTO.setInstitutionEmail(email);

        eventDTO.setStartDate(eventRequest.getStartDate());

        eventDTO.setEndDate(eventRequest.getEndDate());

        eventDTO.setStartWorkingHour(eventRequest.getStartWorkingHour());

        eventDTO.setEndWorkingHour(eventRequest.getEndWorkingHour());

        eventDTO.setLocation(eventRequest.getLocation());

        eventDTO.setLongitude(eventRequest.getLongitude());

        eventDTO.setLatitude(eventRequest.getLatitude());

        return eventDTO;
    }
}

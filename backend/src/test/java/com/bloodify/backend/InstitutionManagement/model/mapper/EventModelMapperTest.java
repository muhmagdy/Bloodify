package com.bloodify.backend.InstitutionManagement.model.mapper;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.InstitutionManagement.dto.EventDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.eventexceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventModelMapperTest {
    @Mock
    InstitutionDAO institutionDAO;

    @Mock
    EventDTO dto;

    @InjectMocks
    EventModelMapper mapper;

    private final RandomUserGenerations randomizer = new RandomUserGenerations();

    // invalid institution
    @Test
    public void invalidInst() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(null);

        Assertions.assertThrows(InvalidInstitution.class,
                () -> mapper.mapToModel(dto));
    }


    // valid institution
    // short title
    @Test
    public void shortTitle() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        String shortString = randomizer.generateName(0, 1);

        when(dto.getTitle())
                .thenReturn(shortString);

        Assertions.assertThrows(InvalidTitle.class,
                () -> mapper.mapToModel(dto));
    }

    // valid institution
    // long title
    @Test
    public void longTitle() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        String longString = randomizer.generateName(101, 120);

        when(dto.getTitle())
                .thenReturn(longString);

        Assertions.assertThrows(InvalidTitle.class,
                () -> mapper.mapToModel(dto));
    }

    // valid institution
    // valid title
    // invalid start date
    @Test
    public void invalidStartDate() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        String correctTitle = randomizer.generateName(2, 100);

        when(dto.getTitle())
                .thenReturn(correctTitle);

        when(dto.getStartDate())
                .thenReturn(null);

        Assertions.assertThrows(InvalidDate.class,
                () -> mapper.mapToModel(dto));

    }

    // valid institution
    // valid title
    // valid start date
    // invalid end date
    @Test
    public void invalidEndDate() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        String correctTitle = randomizer.generateName(2, 100);

        when(dto.getTitle())
                .thenReturn(correctTitle);

        when(dto.getStartDate())
                .thenReturn(LocalDate.now());

        when(dto.getEndDate())
                .thenReturn(null);

        Assertions.assertThrows(InvalidDate.class,
                () -> mapper.mapToModel(dto));

    }

    // valid institution
    // valid title
    // valid start date
    // valid end date
    // invalid start working hour
    @Test
    public void invalidStartWorkingHour() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        String correctTitle = randomizer.generateName(2, 100);

        when(dto.getTitle())
                .thenReturn(correctTitle);

        when(dto.getStartDate())
                .thenReturn(LocalDate.now());

        when(dto.getEndDate())
                .thenReturn(LocalDate.now());

        when(dto.getStartWorkingHour())
                .thenReturn(null);

        Assertions.assertThrows(InvalidWorkingHour.class,
                () -> mapper.mapToModel(dto));
    }

    // valid institution
    // valid title
    // valid start date
    // valid end date
    // valid start working hour
    // invalid end working hour
    @Test
    public void invalidEndWorkingHour() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        String correctTitle = randomizer.generateName(2, 100);

        when(dto.getTitle())
                .thenReturn(correctTitle);

        when(dto.getStartDate())
                .thenReturn(LocalDate.now());

        when(dto.getEndDate())
                .thenReturn(LocalDate.now());

        when(dto.getStartWorkingHour())
                .thenReturn(LocalTime.now());

        when(dto.getEndWorkingHour())
                .thenReturn(null);

        Assertions.assertThrows(InvalidWorkingHour.class,
                () -> mapper.mapToModel(dto));
    }

    // valid institution
    // valid title
    // valid start date
    // valid end date
    // valid start working hour
    // valid end working hour
    // short location
    @Test
    public void shortLocation() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        String correctTitle = randomizer.generateName(2, 100);

        when(dto.getTitle())
                .thenReturn(correctTitle);

        when(dto.getStartDate())
                .thenReturn(LocalDate.now());

        when(dto.getEndDate())
                .thenReturn(LocalDate.now());

        when(dto.getStartWorkingHour())
                .thenReturn(LocalTime.now());

        when(dto.getEndWorkingHour())
                .thenReturn(LocalTime.now());

        String shortLocation = randomizer.generateName(0, 4);

        when(dto.getLocation())
                .thenReturn(shortLocation);


        Assertions.assertThrows(InvalidLocation.class,
                () -> mapper.mapToModel(dto));
    }

    // valid institution
    // valid title
    // valid start date
    // valid end date
    // valid start working hour
    // valid end working hour
    // long location
    @Test
    public void longLocation() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        String correctTitle = randomizer.generateName(2, 100);

        when(dto.getTitle())
                .thenReturn(correctTitle);

        when(dto.getStartDate())
                .thenReturn(LocalDate.now());

        when(dto.getEndDate())
                .thenReturn(LocalDate.now());

        when(dto.getStartWorkingHour())
                .thenReturn(LocalTime.now());

        when(dto.getEndWorkingHour())
                .thenReturn(LocalTime.now());

        String longLocation = randomizer.generateName(201, 220);

        when(dto.getLocation())
                .thenReturn(longLocation);

        Assertions.assertThrows(InvalidLocation.class,
                () -> mapper.mapToModel(dto));
    }

    // valid institution
    // valid title
    // valid start date
    // valid end date
    // valid start working hour
    // valid end working hour
    // invalid longitude
    @Test
    public void invalidLongitude() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        String correctTitle = randomizer.generateName(2, 100);

        when(dto.getTitle())
                .thenReturn(correctTitle);

        when(dto.getStartDate())
                .thenReturn(LocalDate.now());

        when(dto.getEndDate())
                .thenReturn(LocalDate.now());

        when(dto.getStartWorkingHour())
                .thenReturn(LocalTime.now());

        when(dto.getEndWorkingHour())
                .thenReturn(LocalTime.now());

        String correctLocation = randomizer.generateName(5, 200);

        when(dto.getLocation())
                .thenReturn(correctLocation);

        when(dto.getLongitude())
                .thenReturn(null);

        Assertions.assertThrows(InvalidLongitude.class,
                () -> mapper.mapToModel(dto));
    }

    // valid institution
    // valid title
    // valid start date
    // valid end date
    // valid start working hour
    // valid end working hour
    // valid longitude
    // invalid latitude
    @Test
    public void invalidLatitude() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        String correctTitle = randomizer.generateName(2, 100);

        when(dto.getTitle())
                .thenReturn(correctTitle);

        when(dto.getStartDate())
                .thenReturn(LocalDate.now());

        when(dto.getEndDate())
                .thenReturn(LocalDate.now());

        when(dto.getStartWorkingHour())
                .thenReturn(LocalTime.now());

        when(dto.getEndWorkingHour())
                .thenReturn(LocalTime.now());

        String correctLocation = randomizer.generateName(5, 200);

        when(dto.getLocation())
                .thenReturn(correctLocation);

        when(dto.getLongitude())
                .thenReturn(new BigDecimal("0.00"));

        when(dto.getLatitude())
                .thenReturn(null);

        Assertions.assertThrows(InvalidLatitude.class,
                () -> mapper.mapToModel(dto));
    }


    // valid everything
    @Test
    public void validEverything() {
        when(institutionDAO.findInstitutionByEmail(dto.getInstitutionEmail()))
                .thenReturn(new Institution());

        String correctTitle = randomizer.generateName(2, 100);

        when(dto.getTitle())
                .thenReturn(correctTitle);

        when(dto.getStartDate())
                .thenReturn(LocalDate.now());

        when(dto.getEndDate())
                .thenReturn(LocalDate.now());

        when(dto.getStartWorkingHour())
                .thenReturn(LocalTime.now());

        when(dto.getEndWorkingHour())
                .thenReturn(LocalTime.now());

        String correctLocation = randomizer.generateName(5, 200);

        when(dto.getLocation())
                .thenReturn(correctLocation);

        when(dto.getLongitude())
                .thenReturn(new BigDecimal("0.00"));

        when(dto.getLatitude())
                .thenReturn(new BigDecimal("0.00"));


        Assertions.assertDoesNotThrow(() -> mapper.mapToModel(dto));
    }

}
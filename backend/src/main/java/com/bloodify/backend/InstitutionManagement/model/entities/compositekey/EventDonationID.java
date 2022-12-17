package com.bloodify.backend.InstitutionManagement.model.entities.compositekey;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.InstitutionManagement.model.entities.Event;


import java.io.Serializable;


public class EventDonationID implements Serializable {
    private User donor;

    private Event event;
}
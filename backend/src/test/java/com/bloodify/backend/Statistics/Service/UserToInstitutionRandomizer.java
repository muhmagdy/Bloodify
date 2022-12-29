package com.bloodify.backend.Statistics.Service;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.InstitutionManagement.model.InstToUserDonation;
import com.bloodify.backend.InstitutionManagement.model.UserToInstDonation;
import com.bloodify.backend.InstitutionManagement.model.UserToUserDonation;
import com.bloodify.backend.userRequestTests.Randomizer;

import java.time.LocalDate;

public class UserToInstitutionRandomizer {
    RandomUserGenerations userGenerations = new RandomUserGenerations();
    Randomizer instAndPostGenerations = new Randomizer();

    public UserToInstDonation UserToInstitutionRandomizer() {
        return new UserToInstDonation(instAndPostGenerations.generateRandomInstitution(),
                userGenerations.generateNationalID(), userGenerations.generateBloodType(), LocalDate.now());
    }

    public InstToUserDonation InstitutionToUserRandomizer() {
        return new InstToUserDonation(instAndPostGenerations.generateRandomInstitution(),
                userGenerations.generateNationalID(), userGenerations.generateBloodType(), 1, LocalDate.now());
    }

    public UserToUserDonation UserToUserRandomizer() {
        return new UserToUserDonation(instAndPostGenerations.generateRandomInstitution(),
                userGenerations.generateRandomUser(), userGenerations.generateNationalID(), LocalDate.now());
    }

}

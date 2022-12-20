package com.bloodify.backend.InstitutionManagement.service.interfaces;

import com.bloodify.backend.InstitutionManagement.dto.InstToUserDonDTO;
import com.bloodify.backend.InstitutionManagement.dto.UserToUserDonDTO;

public interface TransactionService {

    void applyUserDonation(UserToUserDonDTO userToUserDonDTO);

    void applyInstitutionDonation(InstToUserDonDTO instToUserDonDTO);

}

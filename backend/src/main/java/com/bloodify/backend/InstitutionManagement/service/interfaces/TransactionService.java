package com.bloodify.backend.InstitutionManagement.service.interfaces;

import com.bloodify.backend.InstitutionManagement.dto.InstToUserDonDTO;
import com.bloodify.backend.InstitutionManagement.dto.UserToInstDonDTO;
import com.bloodify.backend.InstitutionManagement.dto.UserToUserDonDTO;

public interface TransactionService {

    void applyUserToUserDonation(UserToUserDonDTO userToUserDonDTO);

    void applyInstToUserDonation(InstToUserDonDTO instToUserDonDTO);

    void applyUserToInstDonation(UserToInstDonDTO userToInsDonDTO);

}

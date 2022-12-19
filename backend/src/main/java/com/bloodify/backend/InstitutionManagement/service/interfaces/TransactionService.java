package com.bloodify.backend.InstitutionManagement.service.interfaces;

import com.bloodify.backend.InstitutionManagement.dto.InstitutionDonationDTO;
import com.bloodify.backend.InstitutionManagement.dto.UserDonationDTO;

public interface TransactionService {

    boolean applyUserDonation(UserDonationDTO userDonationDTO);

    void applyInstitutionDonation(InstitutionDonationDTO institutionDonationDTO);

}

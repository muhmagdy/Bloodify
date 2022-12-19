package com.bloodify.backend.InstitutionManagement.service.classes;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.InstitutionManagement.dto.InstitutionDonationDTO;
import com.bloodify.backend.InstitutionManagement.dto.UserDonationDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InsufficientBloodBags;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.TransactionException;
import com.bloodify.backend.InstitutionManagement.model.InstitutionDonation;
import com.bloodify.backend.InstitutionManagement.model.UserDonation;
import com.bloodify.backend.InstitutionManagement.model.mapper.InstitutionDonationModelMapper;
import com.bloodify.backend.InstitutionManagement.model.mapper.UserDonationModelMapper;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.InstitutionDonationDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserDonationDAO;
import com.bloodify.backend.InstitutionManagement.service.interfaces.TransactionService;
import com.bloodify.backend.UserRequests.service.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class TransactionServiceImp implements TransactionService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    InstitutionDAO institutionDAO;

    @Autowired
    InstitutionDonationDAO institutionDonationDAO;

    @Autowired
    UserDonationDAO userDonationDAO;

    @Autowired
    PostService postService;

    @Autowired
    InstitutionDonationModelMapper institutionDonationModelMapper;

    @Autowired
    UserDonationModelMapper userDonationModelMapper;

    @Transactional(rollbackFor = TransactionException.class)
    public boolean applyUserDonation(UserDonationDTO userDonationDTO) {

        UserDonation model = userDonationModelMapper.mapToModel(userDonationDTO);

        // update only happens if the user is already registered otherwise no changes happen
        int rowsEdited = userDAO.updateLastTimeDonatedByNationalID(LocalDate.now(),
                userDonationDTO.getDonorNationalID());

        boolean isSaved = userDonationDAO.save(model);

        boolean isDecremented = postService.decrementBags(userDonationDTO.getPostID());

        if (!(isSaved && isDecremented))
            throw new TransactionException("Invalid Transaction!");

        return rowsEdited != 0;
    }

    @Transactional(rollbackFor = TransactionException.class)
    public void applyInstitutionDonation(InstitutionDonationDTO institutionDonationDTO) {
        int currentBags;

        InstitutionDonation model = institutionDonationModelMapper.mapToModel(institutionDonationDTO);

        currentBags = institutionDAO.getBagsCount(
                institutionDonationDTO.getInstitutionEmail(),
                institutionDonationDTO.getBloodType()
        );

        Integer requiredBags = institutionDonationDTO.getBagsCount();

        if (currentBags < requiredBags)
            throw new InsufficientBloodBags();

        institutionDAO.updateBagsCount(
                institutionDonationDTO.getInstitutionEmail(),
                institutionDonationDTO.getBloodType(),
                currentBags - requiredBags
        );

        if(!institutionDonationDAO.save(model))
            throw new TransactionException("Invalid Transaction!");
    }

}

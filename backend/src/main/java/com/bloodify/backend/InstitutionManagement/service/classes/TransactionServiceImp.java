package com.bloodify.backend.InstitutionManagement.service.classes;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.InstitutionManagement.dto.InstToUserDonDTO;
import com.bloodify.backend.InstitutionManagement.dto.UserToInstDonDTO;
import com.bloodify.backend.InstitutionManagement.dto.UserToUserDonDTO;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.InsufficientBloodBags;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.TransactionException;
import com.bloodify.backend.InstitutionManagement.model.InstToUserDonation;
import com.bloodify.backend.InstitutionManagement.model.UserToInstDonation;
import com.bloodify.backend.InstitutionManagement.model.UserToUserDonation;
import com.bloodify.backend.InstitutionManagement.model.mapper.InstToUserDonModelMapper;
import com.bloodify.backend.InstitutionManagement.model.mapper.UserToInstDonModelMapper;
import com.bloodify.backend.InstitutionManagement.model.mapper.UserToUserDonModelMapper;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.InstToUserDonDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToInstDonDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToUserDonDAO;
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
    InstToUserDonDAO instToUserDonDAO;

    @Autowired
    UserToUserDonDAO userToUserDonDAO;

    @Autowired
    UserToInstDonDAO userToInstDonDAO;

    @Autowired
    PostService postService;

    @Autowired
    InstToUserDonModelMapper instToUserDonModelMapper;

    @Autowired
    UserToUserDonModelMapper userToUserDonModelMapper;

    @Autowired
    UserToInstDonModelMapper userToInstDonModelMapper;

    @Override
    @Transactional(rollbackFor = TransactionException.class)
    public void applyUserToUserDonation(UserToUserDonDTO userToUserDonDTO) {

        UserToUserDonation model = userToUserDonModelMapper.mapToModel(userToUserDonDTO);

        // update only happens if the user is already registered otherwise no changes happen
        userDAO.updateLastTimeDonatedByNationalID(LocalDate.now(),
                userToUserDonDTO.getDonorNationalID());

        boolean isSaved = userToUserDonDAO.save(model);

        boolean isDecremented = postService.decrementBags(userToUserDonDTO.getPostID());

        if (!(isSaved && isDecremented))
            throw new TransactionException("Invalid Transaction!");

    }

    @Override
    @Transactional(rollbackFor = TransactionException.class)
    public void applyInstToUserDonation(InstToUserDonDTO instToUserDonDTO) {
        InstToUserDonation model = instToUserDonModelMapper.mapToModel(instToUserDonDTO);

        Integer currentBags = institutionDAO.getBagsCount(
                instToUserDonDTO.getInstitutionEmail(),
                instToUserDonDTO.getBloodType()
        );

        Integer requiredBags = instToUserDonDTO.getBagsCount();

        if (currentBags < requiredBags)
            throw new InsufficientBloodBags();

        institutionDAO.updateBagsCount(
                instToUserDonDTO.getInstitutionEmail(),
                instToUserDonDTO.getBloodType(),
                currentBags - requiredBags
        );

        if(!instToUserDonDAO.save(model))
            throw new TransactionException("Invalid Transaction!");
    }

    @Override
    @Transactional(rollbackFor = TransactionException.class)
    public void applyUserToInstDonation(UserToInstDonDTO userToInsDonDTO) {
        UserToInstDonation model = userToInstDonModelMapper.mapToModel(userToInsDonDTO);

        // update only happens if the user is already registered otherwise no changes happen
        userDAO.updateLastTimeDonatedAndBloodTypeByNationalID(
                LocalDate.now(),
                userToInsDonDTO.getBloodType(),
                userToInsDonDTO.getDonorNationalID()
        );

        if(!userToInstDonDAO.save(model))
            throw new TransactionException("Invalid Transaction!");

    }

}

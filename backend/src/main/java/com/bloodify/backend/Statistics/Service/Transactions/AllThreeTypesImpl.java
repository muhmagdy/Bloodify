package com.bloodify.backend.Statistics.Service.Transactions;

import com.bloodify.backend.InstitutionManagement.repository.interfaces.InstToUserDonDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToInstDonDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToUserDonDAO;
import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;
import com.bloodify.backend.Statistics.Service.Interfaces.AllThreeTypes;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AllThreeTypesImpl implements AllThreeTypes {
    @Resource
    InstToUserDonDAO instToUserDonDAO;

    @Resource
    UserToUserDonDAO userToUserDonDAO;

    @Resource
    UserToInstDonDAO userToInstDonDAO;

    @Override
    public BloodBagsCountWrapper[] getAllTransactionsBagsCount (LocalDate start, LocalDate end, String instEmail) {
        BloodBagsCountWrapper[] wrapAnswer = new BloodBagsCountWrapper[8];
        String[] bloodTypesNames = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
        int[] bagsCounts = new int[8];
        for(int i=0; i<8; i++) {
            bagsCounts[i] += instToUserDonDAO.requestedBloodBagsByTypeAndDate(bloodTypesNames[i], start, end, instEmail);
            bagsCounts[i] += userToUserDonDAO.requestedBloodBagsByTypeAndDate(bloodTypesNames[i], start, end, instEmail);
            bagsCounts[i] += userToInstDonDAO.requestedBloodBagsByTypeAndDate(bloodTypesNames[i], start, end, instEmail);
            wrapAnswer[i] = new BloodBagsCountWrapper(bloodTypesNames[i], bagsCounts[i]);
        }

        return wrapAnswer;
    }

}

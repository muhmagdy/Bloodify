package com.bloodify.backend.Statistics.Service.Institution;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionRepository;
import com.bloodify.backend.Statistics.Service.Common.BagsNumbersAndPercentsCalculation;
import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;
import com.bloodify.backend.Statistics.Service.Common.Wrapper;
import com.bloodify.backend.Statistics.Service.Interfaces.BloodBagsCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BloodBagsCountImpl implements BloodBagsCount {
    @Autowired
    @Qualifier("InstitutionRepository")
    InstitutionRepository instRepo;
    Wrapper wrap = new Wrapper();

    public BloodBagsCountWrapper[] getAllCounts (String email) {
        String[] bloodTypesNames = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
//      ordering here: a+, a-, b+, b-, o+, o-, ab+, ab-
        int[] bloodTypes = new int[8];
        calculateBagsNum(email, bloodTypes);

        BloodBagsCountWrapper[] result = new BloodBagsCountWrapper[8];
        wrap.wrapAnswer(result, bloodTypesNames, bloodTypes);

        return result;
    }


    private void calculateBagsNum(String email, int[] bloodTypes) {
        bloodTypes[0] = instRepo.findAPByEmailLike(email);
        bloodTypes[1] = instRepo.findANByEmailLike(email);
        bloodTypes[2] = instRepo.findBPByEmailLike(email);
        bloodTypes[3] = instRepo.findBNByEmailLike(email);
        bloodTypes[4] = instRepo.findOPByEmailLike(email);
        bloodTypes[5] = instRepo.findONByEmailLike(email);
        bloodTypes[6] = instRepo.findABPByEmailLike(email);
        bloodTypes[7] = instRepo.findABNByEmailLike(email);
    }

}

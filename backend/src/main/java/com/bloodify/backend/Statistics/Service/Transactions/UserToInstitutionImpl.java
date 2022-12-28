package com.bloodify.backend.Statistics.Service.Transactions;

import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToInstDonRepository;
import com.bloodify.backend.Statistics.Service.Common.BagsNumbersAndPercentsCalculation;
import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;
import com.bloodify.backend.Statistics.Service.Common.Wrapper;
import com.bloodify.backend.Statistics.Service.Interfaces.UserToInstitution;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserToInstitutionImpl implements UserToInstitution {
    @Resource
    UserToInstDonRepository repository;

//    BagsNumbersAndPercentsCalculation calculate = new BagsNumbersAndPercentsCalculation();
    Wrapper wrap = new Wrapper();

    public BloodBagsCountWrapper[] bagsGotFromTransaction(LocalDate start, LocalDate end, String institutionEmail) {
        BloodBagsCountWrapper[] result = new BloodBagsCountWrapper[8];
//      Order: a+, a-, b+, b-, o+, o-, ab+, ab-
        String[] bloodTypesNames = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

        int[] bagsCounts = new int[8];
        getCounts(start, end, bloodTypesNames, bagsCounts, institutionEmail);

//        int totalBags = calculate.countTotalBags(bagsCounts);

//        double[] percentages = new double[8];
//        calculate.calculateBagsPercents(bagsCounts, totalBags, percentages);

        wrap.wrapAnswer(result, bloodTypesNames, bagsCounts);

        return result;
    }

    private void getCounts(LocalDate start, LocalDate end, String[] bloodTypes, int[] bagsCounts, String institutionEmail) {
        for(int i = 0; i< bagsCounts.length; i++) {
            bagsCounts[i] = repository.countByBloodTypeLikeAndDonationDateBetween(bloodTypes[i], start, end, institutionEmail);
        }
    }

}

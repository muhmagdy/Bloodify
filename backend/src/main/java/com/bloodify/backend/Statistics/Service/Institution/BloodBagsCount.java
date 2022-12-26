package com.bloodify.backend.Statistics.Service.Institution;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionRepository;
import com.bloodify.backend.Statistics.Service.Common.BagsNumbersAndPercentsCalculation;
import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;
import org.springframework.beans.factory.annotation.Qualifier;

public class BloodBagsCount {
    @Qualifier("InstitutionRepository")
    InstitutionRepository instRepo;

    BagsNumbersAndPercentsCalculation calculate = new BagsNumbersAndPercentsCalculation();

    public BloodBagsCountWrapper[] getAllCounts (String email) {
        String[] bloodTypesNames = {"Ap, An, Bp, Bn, Op, On, ABp, ABn"};
//      ordering here: a+, a-, b+, b-, o+, o-, ab+, ab-
        int[] bloodTypes = new int[8];
        calculateBagsNum(email, bloodTypes);
        int totalBags = calculate.countTotalBags(bloodTypes);

        double[] bloodTypePercents = new double[8];
        calculate.calculateBagsPercents(bloodTypes, totalBags, bloodTypePercents);

        BloodBagsCountWrapper[] result = new BloodBagsCountWrapper[8];
        for(int i=0; i<8; i++) {
            result[i] = new BloodBagsCountWrapper(bloodTypesNames[i], bloodTypes[i], bloodTypePercents[i]);
        }

        return result;
    }


    private void calculateBagsNum(String email, int[] bloodTypes) {
        for(int i=0; i<bloodTypes.length; i++)
            bloodTypes[i] = instRepo.findBNEmailLike(email);
    }

}

package com.bloodify.backend.Statistics.Service.Institution;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionRepository;
import org.springframework.beans.factory.annotation.Qualifier;

public class BloodBagsCount {
    @Qualifier("InstitutionRepository")
    InstitutionRepository instRepo;

    public BloodBagsCountWrapper[] getAllCounts (String email) {
        String[] bloodTypesNames = {"Ap, An, Bp, Bn, Op, On, ABp, ABn"};
//      ordering here: a+, a-, b+, b-, o+, o-, ab+, ab-
        int[] bloodTypes = new int[8];
        calculateBagsNum(email, bloodTypes);
        int totalBags = countTotalBags(bloodTypes);

        double[] bloodTypePercents = new double[8];
        calculateBagsPercents(bloodTypes, totalBags, bloodTypePercents);

        BloodBagsCountWrapper[] result = new BloodBagsCountWrapper[8];
        for(int i=0; i<8; i++) {
            result[i] = new BloodBagsCountWrapper(bloodTypesNames[i], bloodTypes[i], bloodTypePercents[i]);
        }

        return result;
    }

    private void calculateBagsPercents(int[] bloodTypes, int totalBags, double[] bloodTypePercents) {
        for(int i = 0; i< bloodTypes.length; i++) {
            bloodTypePercents[i] = calcPercent(bloodTypes[i], totalBags);
        }
    }

    private void calculateBagsNum(String email, int[] bloodTypes) {
        bloodTypes[0] = instRepo.findAPByEmailLike(email);
        bloodTypes[1] = instRepo.findANByEmailLike(email);
        bloodTypes[2] = instRepo.findBPByEmailLike(email);
        bloodTypes[3] = instRepo.findBNEmailLike(email);
        bloodTypes[4] = instRepo.findOPByEmailLike(email);
        bloodTypes[5] = instRepo.findONEmailLike(email);
        bloodTypes[6] = instRepo.findABPByEmailLike(email);
        bloodTypes[7] = instRepo.findABNByEmailLike(email);
    }

    private int countTotalBags(int[] bloodTypes) {
        int total = 0;
        for(int i: bloodTypes)
            total += i;
        return total;
    }

    private double calcPercent(double part, double total) {
        return (part/total) * 100;
    }

}

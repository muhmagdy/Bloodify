package com.bloodify.backend.Statistics.Service.Transaction;

import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToInstDonRepository;
import com.bloodify.backend.Statistics.Service.Common.BagsNumbersAndPercentsCalculation;
import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;
import com.bloodify.backend.Statistics.Service.Common.Wrapper;
import jakarta.annotation.Resource;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class UserToInstitution {
    @Resource
    UserToInstDonRepository repository;

    BagsNumbersAndPercentsCalculation calculate = new BagsNumbersAndPercentsCalculation();
    Wrapper wrap = new Wrapper();

    public BloodBagsCountWrapper[] bagsGotFromTransaction(LocalDate start, LocalDate end) {
        BloodBagsCountWrapper[] result = new BloodBagsCountWrapper[8];
//      Order: a+, a-, b+, b-, o+, o-, ab+, ab-
        String[] bloodTypesNames = {"Ap, An, Bp, Bn, Op, On, ABp, ABn"};

        int[] bagsCounts = new int[8];
        getCounts(start, end, bloodTypesNames, bagsCounts);

        int totalBags = calculate.countTotalBags(bagsCounts);

        double[] percentages = new double[8];
        calculate.calculateBagsPercents(bagsCounts, totalBags, percentages);

        wrap.wrapAnswer(result, bloodTypesNames, bagsCounts, percentages);

        return result;
    }

    private void getCounts(LocalDate start, LocalDate end, String[] bloodTypes, int[] bagsCounts) {
        for(int i = 0; i< bagsCounts.length; i++) {
            bagsCounts[i] = repository.countByTypeAndDonationDateBetween(bloodTypes[i], start, end);
        }
    }

}

package com.bloodify.backend.Statistics.Service.Common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BagsNumbersAndPercentsCalculationTest {
    BagsNumbersAndPercentsCalculation calc = new BagsNumbersAndPercentsCalculation();
//    @Test
//    void calculateBagsPercents() {
//        int[] bags = {5, 3, 7, 21, 9, 4, 15, 11};
//        int total = calc.countTotalBags(bags);
//        double[] expectedPercents = new double[8];
//        for(int i=0; i<8; i++)
//            expectedPercents[i] = bags[i] / (double)total * 100;
//        double[] actualPercents = new double[8];
//        calc.calculateBagsPercents(bags, total, actualPercents);
//        for(int i=0; i<actualPercents.length; i++) {
//            assertEquals(expectedPercents[i], actualPercents[i]);
//        }
//    }

    @Test
    void countTotalBags() {
        int[] bags = {5, 3, 7, 21, 9, 4, 15, 11};
        int total = 5+3+7+21+9+4+15+11;
        assertEquals(total, calc.countTotalBags(bags));
    }
}
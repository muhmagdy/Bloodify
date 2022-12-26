package com.bloodify.backend.Statistics.Service.Common;

public class BagsNumbersAndPercentsCalculation {

    public void calculateBagsPercents(int[] bloodTypes, int totalBags, double[] bloodTypePercents) {
        for(int i = 0; i< bloodTypes.length; i++) {
            bloodTypePercents[i] = calcPercent(bloodTypes[i], totalBags);
        }
    }

    public int countTotalBags(int[] bloodTypes) {
        int total = 0;
        for(int i: bloodTypes)
            total += i;
        return total;
    }

    private double calcPercent(double part, double total) {
        return (part/total) * 100;
    }


}

package com.bloodify.backend.Statistics.Service.Common;

public class Wrapper {
//    public void wrapAnswer(BloodBagsCountWrapper[] result, String[] bloodTypesNames, int[] bagsCounts, double[] percentages) {
//        for(int i=0; i<8; i++) {
//            result[i] = new BloodBagsCountWrapper(bloodTypesNames[i], bagsCounts[i], percentages[i]);
//        }
//    }
    public void wrapAnswer(BloodBagsCountWrapper[] result, String[] bloodTypesNames, int[] bagsCounts) {
        for(int i=0; i<8; i++) {
            result[i] = new BloodBagsCountWrapper(bloodTypesNames[i], bagsCounts[i]);
        }
    }

}

package com.bloodify.backend.Statistics.Service.Post;

import com.bloodify.backend.Statistics.Service.Common.BagsNumbersAndPercentsCalculation;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import jakarta.annotation.Resource;

public class PostsAndBTNumbers {
    @Resource
    PostRepository repository;

    BagsNumbersAndPercentsCalculation calculate = new BagsNumbersAndPercentsCalculation();

    public PostsStatisticsWrapper[] postsNumber(String bloodType) {
        String[] bloodTypesNames = {"Ap, An, Bp, Bn, Op, On, ABp, ABn"};
        int[] postsNumbers = new int[8];
        double[] postPercents = new double[8];
        int[] bagsNumbers = new int[8];
        double[] bagsPercents = new double[8];
        int totalPosts, totalBags;

        totalPosts = getPostsNumbers(bloodTypesNames, postsNumbers);
        totalBags = getBagsNumbers(bloodTypesNames, bagsNumbers);

        calculate.calculateBagsPercents(postsNumbers, totalPosts, postPercents);
        calculate.calculateBagsPercents(bagsNumbers, totalBags, bagsPercents);

        PostsStatisticsWrapper[] result = new PostsStatisticsWrapper[8];
        for(int i=0; i<8; i++) {
            result[i] = new PostsStatisticsWrapper(bloodTypesNames[i],
                    postsNumbers[i], postPercents[i],
                    bagsNumbers[i], bagsPercents[i]);
        }
        return result;
    }

    private int getBagsNumbers(String[] bloodTypesNames, int[] bagsNumbers) {
        int totalBags = 0;
        for(int i = 0; i< bagsNumbers.length; i++) {
            int sum = repository.sumBagsByBloodType(bloodTypesNames[i]);
            bagsNumbers[i] = sum;
            totalBags+=sum;
        }
        return totalBags;
    }

    private int getPostsNumbers(String[] bloodTypesNames, int[] postsNumbers) {
        int totalPosts = 0;
        for(int i = 0; i< postsNumbers.length; i++) {
            int number = repository.countByBloodType(bloodTypesNames[i]);
            postsNumbers[i] = number;
            totalPosts += number;
        }
        return totalPosts;
    }


}

package com.bloodify.backend.Statistics.Service.Post;

import com.bloodify.backend.Statistics.Service.Common.BagsNumbersAndPercentsCalculation;
import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;
import com.bloodify.backend.Statistics.Service.Interfaces.PostsAndBTNumbers;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PostsAndBTNumbersImpl implements PostsAndBTNumbers {
    @Resource
    PostRepository repository;

    BagsNumbersAndPercentsCalculation calculate = new BagsNumbersAndPercentsCalculation();

    public BloodBagsCountWrapper[] postsNumber() {
        String[] bloodTypesNames = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
        int[] postsNumbers = new int[8];
//        double[] postsPercents = new double[8];
        int[] bagsNumbers = new int[8];
//        double[] bagsPercents = new double[8];

//        getPostsNumbers(bloodTypesNames, postsNumbers);
        getBagsNumbers(bloodTypesNames, bagsNumbers);

//        calculate.calculateBagsPercents(postsNumbers, totalPosts, postsPercents);
//        calculate.calculateBagsPercents(bagsNumbers, totalBags, bagsPercents);

        BloodBagsCountWrapper[] result = new BloodBagsCountWrapper[8];
//        for(int i=0; i<8; i++) {
//            result[i] = new PostsStatisticsWrapper(bloodTypesNames[i],
//                    postsNumbers[i], postsPercents[i],
//                    bagsNumbers[i], bagsPercents[i]);
//        }
        for(int i=0; i<8; i++) {
            result[i] = new BloodBagsCountWrapper(bloodTypesNames[i], bagsNumbers[i]);
        }
        return result;
    }


    private void getBagsNumbers(String[] bloodTypesNames, int[] bagsNumbers) {
        int totalBags = 0;
        for(int i = 0; i< bagsNumbers.length; i++) {
            int sum = repository.sumBagsByBloodType(bloodTypesNames[i]);
            bagsNumbers[i] = sum;
            totalBags+=sum;
        }
    }

//    private void getPostsNumbers(String[] bloodTypesNames, int[] postsNumbers) {
//        int totalPosts = 0;
//        for(int i = 0; i< postsNumbers.length; i++) {
//            int number = repository.countByBloodType(bloodTypesNames[i]);
//            postsNumbers[i] = number;
//            totalPosts += number;
//        }
//    }


}

package com.bloodify.backend.Statistics.Service.Post;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;
import com.bloodify.backend.Statistics.Service.Common.Wrapper;
import com.bloodify.backend.Statistics.Service.Interfaces.BloodBagsNumbersInPosts;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BloodBagsNumbersInPostsImpl implements BloodBagsNumbersInPosts {
    @Resource
    PostRepository repository;
    Wrapper wrap = new Wrapper();


    public BloodBagsCountWrapper[] postsNumber(String instEmail) {
        String[] bloodTypesNames = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
        int[] bagsNumbers = new int[8];
        getBagsNumbers(bloodTypesNames, bagsNumbers, instEmail);
        BloodBagsCountWrapper[] result = new BloodBagsCountWrapper[8];
        wrap.wrapAnswer(result, bloodTypesNames, bagsNumbers);
        return result;
    }


    private void getBagsNumbers(String[] bloodTypesNames, int[] bagsNumbers, String instEmail) {
//        int totalBags = 0;
        for(int i = 0; i< bagsNumbers.length; i++) {
            Integer sum = repository.sumBagsByBloodType(bloodTypesNames[i], instEmail);
            bagsNumbers[i] = sum == null? 0: sum;
//            totalBags+=sum;
        }
    }

}

package com.bloodify.backend.Statistics.Controller;

import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;
import com.bloodify.backend.Statistics.Service.Interfaces.AllThreeTypes;
import com.bloodify.backend.Statistics.Service.Interfaces.BloodBagsCount;
import com.bloodify.backend.Statistics.Service.Interfaces.BloodBagsNumbersInPosts;
import com.bloodify.backend.Statistics.Service.Interfaces.UserToInstitution;
import com.bloodify.backend.UserRequests.controller.request.entity.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1/institution/displayStatistics")

public class API_StatisticsController {
    @Autowired
    AllThreeTypes allTransactions;
    @Autowired
    UserToInstitution userToInstitution;
    @Autowired
    BloodBagsCount instBloodBagsCount;
    @Autowired
    BloodBagsNumbersInPosts bloodBagsInPosts;

    @GetMapping("/institutionBags")
    public ResponseEntity<PostResponse<BloodBagsCountWrapper[]>> getInstitutionBags(Authentication auth) {
        BloodBagsCountWrapper[] response = instBloodBagsCount.getAllCounts(auth.getName());
        boolean status = response.length != 0;
        if(status)
            return ResponseEntity.status(200).body(new PostResponse<>(true, response));
        return ResponseEntity.status(422).body(new PostResponse<>(false, null));
    }

    @GetMapping("/postBags")
    public ResponseEntity<PostResponse<BloodBagsCountWrapper[]>> getPostsBags(Authentication auth) {
        BloodBagsCountWrapper[] response = bloodBagsInPosts.postsNumber(auth.getName());
        boolean status = response.length != 0;
        if(status)
            return ResponseEntity.status(200).body(new PostResponse<>(true, response));
        return ResponseEntity.status(422).body(new PostResponse<>(false, null));
    }

    @GetMapping("/collectedBagsFromUser")
    public ResponseEntity<PostResponse<BloodBagsCountWrapper[]>> getDonationBags(Authentication auth) {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusMonths(1);
        BloodBagsCountWrapper[] response = userToInstitution.bagsGotFromTransaction(start, end, auth.getName());
        boolean status = response.length != 0;
        if(status)
            return ResponseEntity.status(200).body(new PostResponse<>(true, response));
        return ResponseEntity.status(422).body(new PostResponse<>(false, null));
    }

    @GetMapping("/collectedBagsFromAllTransactions")
    public ResponseEntity<PostResponse<BloodBagsCountWrapper[]>> getAllTransactionsBags(Authentication auth) {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusMonths(1);
        BloodBagsCountWrapper[] response = allTransactions.getAllTransactionsBagsCount(start, end, auth.getName());
        boolean status = response.length != 0;
        if(status)
            return ResponseEntity.status(200).body(new PostResponse<>(true, response));
        return ResponseEntity.status(422).body(new PostResponse<>(false, null));
    }

}

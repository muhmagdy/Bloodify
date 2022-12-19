package com.bloodify.backend.InstitutionManagement.controller.api;

import com.bloodify.backend.InstitutionManagement.controller.reponse.InstToUserDonResponse;
import com.bloodify.backend.InstitutionManagement.controller.reponse.UserToUserDonResponse;
import com.bloodify.backend.InstitutionManagement.controller.request.InstitutionDonationRequest;
import com.bloodify.backend.InstitutionManagement.controller.request.UserDonationRequest;
import com.bloodify.backend.InstitutionManagement.dto.mapper.InstitutionDonationDTOMapper;
import com.bloodify.backend.InstitutionManagement.dto.mapper.UserDonationDTOMapper;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.TransactionException;
import com.bloodify.backend.InstitutionManagement.service.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/institution/transaction/instToUser")
    public ResponseEntity<InstToUserDonResponse> instDonation(@RequestBody InstitutionDonationRequest donationRequest,
                                                              Authentication auth) {

        transactionService.applyInstitutionDonation(
                new InstitutionDonationDTOMapper().mapToDTO(donationRequest, auth.getName())
        );

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new InstToUserDonResponse(true, "Transaction Successful!"));

    }

    @PostMapping("/institution/transaction/userToUser")
    public ResponseEntity<UserToUserDonResponse> userDonation(@RequestBody UserDonationRequest donationRequest,
                                                              Authentication auth) {

        boolean updatedLastTimeDon = transactionService.applyUserDonation(
                new UserDonationDTOMapper().mapToDTO(donationRequest, auth.getName())
        );

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new UserToUserDonResponse(true, "Transaction Successful!", updatedLastTimeDon));

    }


    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(TransactionException.class)
    public InstToUserDonResponse handleTransactionException(TransactionException exception) {
        return new InstToUserDonResponse(false, exception.getMessage());
    }

}

package com.bloodify.backend.InstitutionManagement.controller.api;

import com.bloodify.backend.InstitutionManagement.controller.response.TransactionResponse;
import com.bloodify.backend.InstitutionManagement.controller.request.InstToUserDonRequest;
import com.bloodify.backend.InstitutionManagement.controller.request.UserToInstDonRequest;
import com.bloodify.backend.InstitutionManagement.controller.request.UserToUserDonRequest;
import com.bloodify.backend.InstitutionManagement.dto.mapper.InstToUserDonDTOMapper;
import com.bloodify.backend.InstitutionManagement.dto.mapper.UserToInstDonDTOMapper;
import com.bloodify.backend.InstitutionManagement.dto.mapper.UserToUserDonDTOMapper;
import com.bloodify.backend.InstitutionManagement.exceptions.transactionexceptions.TransactionException;
import com.bloodify.backend.InstitutionManagement.service.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1/institution/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/inst-to-user")
    public ResponseEntity<TransactionResponse> createInstToUserDon(@RequestBody InstToUserDonRequest donationRequest,
                                                                   Authentication auth) {

        transactionService.applyInstToUserDonation(
                new InstToUserDonDTOMapper().mapToDTO(donationRequest, auth.getName())
        );

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new TransactionResponse(true, "Transaction Successful!"));

    }

    @PostMapping("/user-to-user")
    public ResponseEntity<TransactionResponse> createUserToUserDon(@RequestBody UserToUserDonRequest donationRequest,
                                                                   Authentication auth) {

        transactionService.applyUserToUserDonation(
                new UserToUserDonDTOMapper().mapToDTO(donationRequest, auth.getName())
        );

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new TransactionResponse(true, "Transaction Successful!"));

    }

    @PostMapping("/user-to-inst")
    public ResponseEntity<TransactionResponse> createUserToInstDon(@RequestBody UserToInstDonRequest donationRequest,
                                                                   Authentication auth) {
        transactionService.applyUserToInstDonation(
                new UserToInstDonDTOMapper().mapToDTO(donationRequest, auth.getName())
        );

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new TransactionResponse(true, "Transaction Successful!"));

    }


    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(TransactionException.class)
    public TransactionResponse handleTransactionException(TransactionException exception) {
        return new TransactionResponse(false, exception.getMessage());
    }

}

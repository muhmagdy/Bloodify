package com.bloodify.backend.UserRequests.controller.api;

import com.bloodify.backend.UserRequests.controller.request.entity.InstitutionBrief;
import com.bloodify.backend.UserRequests.controller.request.entity.PostRequest;
import com.bloodify.backend.UserRequests.controller.request.entity.PostResponse;
import com.bloodify.backend.UserRequests.dto.entities.SearchResult;
import com.bloodify.backend.UserRequests.service.entities.SearchServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/api/v1/user/blood")
public class API_Search_Controller {
    @Autowired
    private SearchServiceImp searchService;

    @GetMapping("/search/{bloodType}")
    ResponseEntity<List<SearchResult>> searchInInstitution(@PathVariable("bloodType") String type, Authentication auth){
        List<SearchResult> results = searchService.SearchInInstitutions(type);
        if (results == null) return ResponseEntity.status(422).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(searchService.SearchInInstitutions(type));
    }

    @GetMapping("/allInstitutions")
    public ResponseEntity<List<InstitutionBrief>> getAllInstitutions(Authentication auth){
        return ResponseEntity.status(HttpStatus.OK).body(searchService.getALlInstitutions());
    }
}

package com.bloodify.backend.UserRequests.controller.api;

import com.bloodify.backend.UserRequests.dto.entities.SearchResult;
import com.bloodify.backend.UserRequests.service.entities.SearchServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    List<SearchResult> searchInInstitution(@PathVariable("bloodType") String type, Authentication auth){
        return searchService.SearchInInstitutions(type);
    }
}

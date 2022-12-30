package com.bloodify.backend.InstitutionManagement.controller.api;

import com.bloodify.backend.InstitutionManagement.controller.reponse.PostBrief;
import com.bloodify.backend.InstitutionManagement.service.classes.InstHomepageServiceImp;
import com.bloodify.backend.InstitutionManagement.service.interfaces.InstHomepageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1/institution")
public class HomepageController {
    @Autowired
    InstHomepageService homepageService;

    @GetMapping("/posts")
    List<PostBrief> getInstitutionRelatedPosts(Authentication auth){
        return homepageService.getPostBriefs(auth.getName());
    }
}

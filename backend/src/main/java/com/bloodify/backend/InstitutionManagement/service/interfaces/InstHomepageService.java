package com.bloodify.backend.InstitutionManagement.service.interfaces;

import com.bloodify.backend.InstitutionManagement.controller.reponse.PostBrief;

import java.util.List;

public interface InstHomepageService {
    List<PostBrief> getPostBriefs(String institutionEmail);
}

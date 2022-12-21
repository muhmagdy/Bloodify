package com.bloodify.backend.UserRequests.service.interfaces;

import com.bloodify.backend.UserRequests.controller.request.entity.InstitutionBrief;
import com.bloodify.backend.UserRequests.dto.entities.SearchDto;
import com.bloodify.backend.UserRequests.dto.entities.SearchResult;

import java.util.List;

public interface SearchService {
    List<SearchResult> SearchInInstitutions(String bloodType);
    List<InstitutionBrief> getALlInstitutions();
}

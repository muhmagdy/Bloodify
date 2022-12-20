package com.bloodify.backend.UserRequests.service.entities;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionRepository;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.UserRequests.controller.request.entity.InstitutionBrief;
import com.bloodify.backend.UserRequests.dto.entities.SearchResult;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodType;
import com.bloodify.backend.UserRequests.service.bloodTypes.BloodTypeFactory;
import com.bloodify.backend.UserRequests.service.interfaces.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class SearchServiceImp implements SearchService {
    private final Map<String, String> type_fieldMap;

    @Autowired
    private InstitutionRepository institutionRepository;

    public SearchServiceImp() {
        this.type_fieldMap = new HashMap<>();
        this.type_fieldMap.put("A+", "positiveA_bagsCount");
        this.type_fieldMap.put("B+", "positiveB_bagsCount");
        this.type_fieldMap.put("AB+", "positiveAB_bagsCount");
        this.type_fieldMap.put("O+", "positiveO_bagsCount");
        this.type_fieldMap.put("A-", "negativeA_bagsCount");
        this.type_fieldMap.put("B-", "negativeB_bagsCount");
        this.type_fieldMap.put("AB-", "negativeAB_bagsCount");
        this.type_fieldMap.put("O-", "negativeO_bagsCount");
    }

    @Override
    public List<SearchResult> SearchInInstitutions(String bloodType) {
        bloodType = bloodType.replace('p', '+').replace('n', '-');
        List<String> compatibleTypes;
        try {
            compatibleTypes = getCompatibleTypes(BloodTypeFactory.getFactory().generateFromString(bloodType));
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        List<String> compatibleTypesForInstit = new ArrayList<>();
        for (String type: compatibleTypes) compatibleTypesForInstit.add(type_fieldMap.get(type));

        List<Institution> found = searchForTypes(compatibleTypesForInstit);
        List<SearchResult> results = new ArrayList<>();

        for (Institution i : found){
            Map<String, Integer> types_bags = new HashMap<>();
            for (String type: compatibleTypes) types_bags.put(type, getCount(type, i));
            results.add(new SearchResult(i.getInstitutionID(), i.getName(), i.getLocation(), i.getLongitude(),
                    i.getLatitude(), types_bags, i.getWorkingHours()));
        }
        return results;
    }

    @Override
    public List<InstitutionBrief> getALlInstitutions() {
        return this.institutionRepository.findAll().stream()
                .map(institution -> new InstitutionBrief(institution.getInstitutionID(), institution.getName(), institution.getLocation()))
                .toList();
    }
    private List<String> getCompatibleTypes(BloodType bloodType){
        List<BloodType> compatibleTypes = bloodType.getCompatibleTypes();
        List<String> compatibleTypesStr = new ArrayList<>();
        for (BloodType type : compatibleTypes) compatibleTypesStr.add(type.toString());
        return compatibleTypesStr;
    }

    private List<Institution> searchForTypes(List<String> compatibleTypes){
        SpecificationBuilder builder = new SpecificationBuilder(compatibleTypes);
        Specification<Institution> spec = builder.build();
        return institutionRepository.findAll(spec);
    }

    private int getCount(String type, Institution i){
        return switch (type) {
            case "A+" -> i.getPositiveA_bagsCount();
            case "B+" -> i.getPositiveB_bagsCount();
            case "O+" -> i.getPositiveO_bagsCount();
            case "AB+" -> i.getPositiveAB_bagsCount();
            case "A-" -> i.getNegativeA_bagsCount();
            case "B-" -> i.getNegativeB_bagsCount();
            case "O-" -> i.getNegativeO_bagsCount();
            case "AB-" -> i.getNegativeAB_bagsCount();
            default -> 0;
        };
    }

}

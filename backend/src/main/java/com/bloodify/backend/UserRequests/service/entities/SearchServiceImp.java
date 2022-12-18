package com.bloodify.backend.UserRequests.service.entities;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionRepository;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
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
        System.out.println(bloodType + "------------------------------");
        List<String> compatibleTypes = getCompatibleTypes(BloodTypeFactory.getFactory().generateFromString(bloodType));
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

    private List<String> getCompatibleTypes(BloodType bloodType){
        List<BloodType> compatibleTypes = bloodType.getCompatibleTypes();
        List<String> compatibleTypesStr = new ArrayList<>();
        System.out.println("-----------------");
        for (BloodType type : compatibleTypes) {
            System.out.println(type.toString() + "00000000000");
            compatibleTypesStr.add(type.toString());
        }
        System.out.println("-----------------");
        return compatibleTypesStr;
    }

    private List<Institution> searchForTypes(List<String> compatibleTypes){
        SpecificationBuilder builder = new SpecificationBuilder(compatibleTypes);
        Specification<Institution> spec = builder.build();
        return institutionRepository.findAll(spec);
    }

    private int getCount(String type, Institution i){
        switch (type){
            case "A+":
                return i.getPositiveA_bagsCount();
            case "B+":
                return i.getPositiveB_bagsCount();
            case "O+":
                return i.getPositiveO_bagsCount();
            case "AB+":
                return i.getPositiveAB_bagsCount();
            case "A-":
                return i.getNegativeA_bagsCount();
            case "B-":
                return i.getNegativeB_bagsCount();
            case "O-":
                return i.getNegativeO_bagsCount();
            case "AB-":
                return i.getNegativeAB_bagsCount();
        }
        return 0;
    }

}

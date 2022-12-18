package com.bloodify.backend.UserRequests.service.entities;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SpecificationBuilder {
    private final List<String> compatibleTypes;

    public SpecificationBuilder(List<String> compatibleTypes) {
        this.compatibleTypes = compatibleTypes;
    }

    public Specification<Institution> build(){
        if (compatibleTypes.size() == 0) return null;
        Specification<Institution> specification = new SearchPredicate(compatibleTypes.get(0));
        for (int i = 1; i < compatibleTypes.size(); i++) {
            String anotherType = compatibleTypes.get(i);
            specification = Specification.where(specification).and(new SearchPredicate(anotherType));
        }
        return specification;
    }
}

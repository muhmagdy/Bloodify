package com.bloodify.backend.UserRequests.service.entities;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class SearchPredicate implements Specification<Institution> {
    private final String bloodType;

    public SearchPredicate(String bloodType) {
        super();
        this.bloodType = bloodType;
        System.out.println(bloodType);
    }

    @Override
    public Predicate toPredicate(Root<Institution> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.greaterThan(root.get(bloodType), 0 );
    }
}

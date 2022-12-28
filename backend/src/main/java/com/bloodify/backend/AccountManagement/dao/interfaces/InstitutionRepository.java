package com.bloodify.backend.AccountManagement.dao.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("InstitutionRepository")
public interface InstitutionRepository extends JpaRepository<Institution, Integer>,
        JpaSpecificationExecutor<Institution> {
    List<Institution> findByEmail(String email);


    List<Institution> findAll();

    List<Institution> findAll(Specification<Institution> spec);

    List<Institution> findByInstitutionID(int institutionID);

    List<Institution> haveBloodPacketsPositiveA(int quantity);

    List<Institution> haveBloodPacketsPositiveB(int quantity);

    List<Institution> haveBloodPacketsPositiveAB(int quantity);

    List<Institution> haveBloodPacketsPositiveO(int quantity);

    List<Institution> haveBloodPacketsNegativeA(int quantity);

    List<Institution> haveBloodPacketsNegativeB(int quantity);

    List<Institution> haveBloodPacketsNegativeAB(int quantity);

    List<Institution> haveBloodPacketsNegativeO(int quantity);

    boolean existsByEmail(String email);

    @Transactional
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("update Institution u set u.password = ?1 where u.email = ?2")
    boolean updatePasswordByEmail(@NonNull String newPassword,
                                  @NonNull String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.positiveA_bagsCount = ?1 where i.email = ?2")
    int updatePositiveA_bagsCountByEmail(@NonNull Integer positiveA_bagsCount, String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.positiveB_bagsCount = ?1 where i.email = ?2")
    int updatePositiveB_bagsCountByEmail(@NonNull Integer positiveB_bagsCount, String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.positiveAB_bagsCount = ?1 where i.email = ?2")
    int updatePositiveAB_bagsCountByEmail(@NonNull Integer positiveAB_bagsCount, String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.positiveO_bagsCount = ?1 where i.email = ?2")
    int updatePositiveO_bagsCountByEmail(@NonNull Integer positiveO_bagsCount, String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.negativeA_bagsCount = ?1 where i.email = ?2")
    int updateNegativeA_bagsCountByEmail(@NonNull Integer negativeA_bagsCount, String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.negativeB_bagsCount = ?1 where i.email = ?2")
    int updateNegativeB_bagsCountByEmail(@NonNull Integer negativeB_bagsCount, String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.negativeAB_bagsCount = ?1 where i.email = ?2")
    int updateNegativeAB_bagsCountByEmail(@NonNull Integer negativeAB_bagsCount, String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.negativeO_bagsCount = ?1 where i.email = ?2")
    int updateNegativeO_bagsCountByEmail(@NonNull Integer negativeO_bagsCount, String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.positiveA_bagsCount = i.positiveA_bagsCount + ?1 where i.email = ?2")
    int incrementAPosBagsCountBy(Integer positiveA_bagsCount, @NonNull String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.positiveB_bagsCount = i.positiveB_bagsCount + ?1 where i.email = ?2")
    int incrementBPosBagsCountBy(@NonNull Integer positiveB_bagsCount, @NonNull String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.positiveAB_bagsCount = i.positiveAB_bagsCount + ?1 where i.email = ?2")
    int incrementABPosBagsCountBy(@NonNull Integer positiveAB_bagsCount, String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.positiveO_bagsCount = i.positiveO_bagsCount + ?1 where i.email = ?2")
    int incrementOPosBagsCountBy(@NonNull Integer positiveO_bagsCount, @NonNull String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.negativeA_bagsCount = i.negativeA_bagsCount + ?1 where i.email = ?2")
    int incrementANegBagsCountBy(@NonNull Integer negativeA_bagsCount, @NonNull String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.negativeB_bagsCount = i.negativeB_bagsCount + ?1 where i.email = ?2")
    int incrementBNegBagsCountBy(@NonNull Integer negativeB_bagsCount, @NonNull String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.negativeAB_bagsCount = i.negativeAB_bagsCount + ?1 where i.email = ?2")
    int incrementABNegBagsCountBy(@NonNull Integer negativeAB_bagsCount, @NonNull String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Institution i set i.negativeO_bagsCount = i.negativeO_bagsCount + ?1 where i.email = ?2")
    int incrementONegBagsCountBy(@NonNull Integer negativeO_bagsCount, @NonNull String email);


}

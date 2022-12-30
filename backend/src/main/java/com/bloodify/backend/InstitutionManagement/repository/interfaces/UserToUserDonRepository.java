package com.bloodify.backend.InstitutionManagement.repository.interfaces;

import com.bloodify.backend.InstitutionManagement.model.UserToUserDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserToUserDonRepository extends JpaRepository<UserToUserDonation, Integer> {
    List<UserToUserDonation> findByInstitution_Email(@NonNull String email);

    List<UserToUserDonation> findByInstitution_EmailAndDonationDate(@NonNull String email,
                                                                    @NonNull LocalDate donationDate);

    List<UserToUserDonation> findByDonorNationalID(@NonNull String donorNationalID);

    List<UserToUserDonation> findByAcceptor_NationalID(@NonNull String nationalID);

    @Query("""
            select count(u) from UserToUserDonation u
            where u.acceptor.bloodType = ?1 and u.donationDate between ?2 and ?3 and u.institution.email like ?4""")
    int countByAcceptor_BloodTypeAndDonationDateBetween(String bloodType, LocalDate donationDateStart,
                                                        LocalDate donationDateEnd, String instEmail);


}
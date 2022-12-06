package com.bloodify.backend.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Data
@NoArgsConstructor

@NamedNativeQuery(
        name = "Institution.findByEmail",
        query = "SELECT * FROM institution WHERE email = ?",
        resultClass = Institution.class
)
@NamedNativeQuery(
        name = "Institution.haveBloodPacketsPositiveA",
        query = "SELECT * FROM institution WHERE count_Ap >= ?",
        resultClass = Institution.class
)
@NamedNativeQuery(
        name = "Institution.haveBloodPacketsPositiveB",
        query = "SELECT * FROM institution WHERE count_Bp >= ?",
        resultClass = Institution.class
)
@NamedNativeQuery(
        name = "Institution.haveBloodPacketsPositiveAB",
        query = "SELECT * FROM institution WHERE count_ABp >= ?",
        resultClass = Institution.class
)
@NamedNativeQuery(
        name = "Institution.haveBloodPacketsPositiveO",
        query = "SELECT * FROM institution WHERE count_Op >= ?",
        resultClass = Institution.class
)
@NamedNativeQuery(
        name = "Institution.haveBloodPacketsNegativeA",
        query = "SELECT * FROM institution WHERE count_An >= ?",
        resultClass = Institution.class
)
@NamedNativeQuery(
        name = "Institution.haveBloodPacketsNegativeB",
        query = "SELECT * FROM institution WHERE count_Bn >= ?",
        resultClass = Institution.class
)
@NamedNativeQuery(
        name = "Institution.haveBloodPacketsNegativeAB",
        query = "SELECT * FROM institution WHERE count_ABn >= ?",
        resultClass = Institution.class
)
@NamedNativeQuery(
        name = "Institution.haveBloodPacketsNegativeO",
        query = "SELECT * FROM institution WHERE count_On >= ?",
        resultClass = Institution.class
)
//@NamedNativeQuery(
//        name = "Institution.setPacketChangesPositiveA",
//        query = "UPDATE institution SET count_Ap = ? WHERE email = ?",
//        resultClass = Institution.class
//)
//@NamedNativeQuery(
//        name = "Institution.setPacketChangesNegativeA",
//        query = "UPDATE institution SET count_An = ? WHERE email = ?",
//        resultClass = Institution.class
//)
//@NamedNativeQuery(
//        name = "Institution.setPacketChangesPositiveB",
//        query = "UPDATE institution SET count_Bp = ? WHERE email = ?",
//        resultClass = Institution.class
//)
//@NamedNativeQuery(
//        name = "Institution.setPacketChangesNegativeB",
//        query = "UPDATE institution SET count_Bn = ? WHERE email = ?",
//        resultClass = Institution.class
//)
//@NamedNativeQuery(
//        name = "Institution.setPacketChangesPositiveAB",
//        query = "UPDATE institution SET count_ABp = ? WHERE email = ?",
//        resultClass = Institution.class
//)
//@NamedNativeQuery(
//        name = "Institution.setPacketChangesNegativeAB",
//        query = "UPDATE institution SET count_ABn = ? WHERE email = ?",
//        resultClass = Institution.class
//)
//@NamedNativeQuery(
//        name = "Institution.setPacketChangesPositiveO",
//        query = "UPDATE institution SET count_Op = ? WHERE email = ?",
//        resultClass = Institution.class
//)
//@NamedNativeQuery(
//        name = "Institution.setPacketChangesNegativeO",
//        query = "UPDATE institution SET count_On = ? WHERE email = ?",
//        resultClass = Institution.class
//)
public class Institution {
    @Id
    @GeneratedValue
    int institutionID;
    @Column(unique = true, nullable = false, length = 40)
    String email;

    @Column(nullable = false, length = 40)
    String name;   

    @Column(length = 50)
    String location;
    @Column()
    Double latitude;
    @Column()
    Double longitude;
    @Column(name = "working_hours")
    Integer workingHours;
    @Column(nullable = false, length = 60)
    String password;

    @Column(name = "count_Ap")
    Integer positiveA_bagsCount;
    @Column(name = "count_Bp")
    Integer positiveB_bagsCount;
    @Column(name = "count_ABp")
    Integer positiveAB_bagsCount;
    @Column(name = "count_Op")
    Integer positiveO_bagsCount;
    @Column(name = "count_An")
    Integer negativeA_bagsCount;
    @Column(name = "count_Bn")
    Integer negativeB_bagsCount;
    @Column(name = "count_ABn")
    Integer negativeAB_bagsCount;
    @Column(name = "count_On")
    Integer negativeO_bagsCount;

    public Institution (String email, String name, String password, String location, int workingHours) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.location = location;
        this.workingHours = workingHours;
        this.negativeA_bagsCount = 0;
        this.negativeB_bagsCount = 0;
        this.negativeAB_bagsCount = 0;
        this.negativeO_bagsCount = 0;
        this.positiveA_bagsCount = 0;
        this.positiveB_bagsCount = 0;
        this.positiveAB_bagsCount = 0;
        this.positiveO_bagsCount = 0;
    }

    public Institution(String email, String password, String location, int workingHours) {
        this.email = email;
        this.password = password;
        this.location = location;
        this.workingHours = workingHours;
        this.negativeA_bagsCount = 0;
        this.negativeB_bagsCount = 0;
        this.negativeAB_bagsCount = 0;
        this.negativeO_bagsCount = 0;
        this.positiveA_bagsCount = 0;
        this.positiveB_bagsCount = 0;
        this.positiveAB_bagsCount = 0;
        this.positiveO_bagsCount = 0;
    }

    public Institution (String name, String email, String locationEnglish, float locationLatitude, float locationLongitude, String password)
    {
        this.name = name;
        this.email = email;
        this.location = locationEnglish;
        this.password = password;
    }

}
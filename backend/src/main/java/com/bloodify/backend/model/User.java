package com.bloodify.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor

@NamedNativeQuery(
        name = "User.findByEmail",
        query = "SELECT * FROM user WHERE email = ?",
        resultClass = User.class
)
@NamedNativeQuery(
        name = "User.findByNationalID",
        query = "SELECT * FROM user WHERE national_id = ?",
        resultClass = User.class
)

@NamedNativeQuery(
        name = "User.findByBloodType",
        query = "SELECT * FROM user WHERE blood_type = ? AND blood_type_sign = ?",
        resultClass = User.class
)

public class User {
    @Id
    @GeneratedValue
    private int userID;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "national_id", unique = true)
    private String nationalID;
    @Column(unique = true)
    private String email;
    @Column(name = "blood_type")
    private String bloodType;
    @Column(name = "blood_type_sign")
    private char bloodTypeSign;
    @Column(name = "has_diseases")
    private boolean hasDiseases;
    @Column(name = "last_time_donated")
    private LocalDate lastTimeDonated;
    private String password;

    public User(String firstName, String lastName, String nationalID, String email, String bloodType,
                char bloodTypeSign, boolean hasDiseases, LocalDate lastTimeDonated, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalID = nationalID;
        this.email = email;
        this.bloodType = bloodType;
        this.bloodTypeSign = bloodTypeSign;
        this.hasDiseases = hasDiseases;
        this.lastTimeDonated = lastTimeDonated;
        this.password = password;
    }


}

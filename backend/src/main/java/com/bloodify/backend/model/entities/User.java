package com.bloodify.backend.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import lombok.ToString;

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
        query = "SELECT * FROM user WHERE blood_type = ?",
        resultClass = User.class
)

@ToString
public class User {
    @Id
    @GeneratedValue
    private int userID;

    @NonNull
    @Size(max = 20, message = "First name is too long")
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @NonNull
    @Size(max = 20, message = "Last name is too long")
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @NonNull
    @Size(min= 14, max = 14, message = "National ID is not 14 characters long")
    @Column(name = "national_id", unique = true, nullable = false, length = 14)
    private String nationalID;

    @NonNull
    @Size(max = 40, message = "Email is too long")
    @Column(unique = true, nullable = false, length = 40)
    private String email;

    @NonNull
    @Size(max = 3, message = "Blood type is too long")
    @Column(name = "blood_type", nullable = false, length = 3)
    private String bloodType;

    @Column(name = "has_diseases")
    private boolean hasDiseases;

    @Column(name = "last_time_donated")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastTimeDonated;

    @NonNull
    @Size(max = 30, message = "Password is too long")
    @Size(min = 8, message = "Password is too short")
    @Column(nullable = false, length = 30)
    private String password;

    public User(String firstName, String lastName, String nationalID, String email, String bloodType,
                boolean hasDiseases, LocalDate lastTimeDonated, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalID = nationalID;
        this.email = email;
        this.bloodType = bloodType;
        this.hasDiseases = hasDiseases;
        this.lastTimeDonated = lastTimeDonated;
        this.password = password;
    }
}

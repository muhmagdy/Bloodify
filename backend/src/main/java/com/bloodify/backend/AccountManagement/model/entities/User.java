package com.bloodify.backend.AccountManagement.model.entities;

import com.bloodify.backend.UserRequests.model.entities.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Size(max = 60, message = "Password is too long")
    @Size(min = 8, message = "Password is too short")
    @Column(nullable = false, length = 100)
    private String password = "";

    @Column(name= "status")
    private Integer status;

    @Column()
    Double latitude;

    @Column()
    Double longitude;


//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    @ToString.Exclude
//    private List<Post> posts;

//    @ManyToOne(fetch = FetchType.EAGER)
//
//    private Post acceptedPost;


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
//        this.posts = new ArrayList<>();
    }

}

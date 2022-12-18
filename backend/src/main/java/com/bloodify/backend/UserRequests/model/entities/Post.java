package com.bloodify.backend.UserRequests.model.entities;


import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    Integer postID;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    Institution institution;
    @NonNull
    @Column(name = "req_bags_number", nullable = false)
    Integer bagsNum;

    @NonNull
    @JsonFormat(timezone = "HH:mm dd-MM-yyyy")
    @Column(name = "created_at")
    LocalDateTime startTime;

    @NonNull
    @Size(max = 3, message = "blood type is too long")
    @Column(name = "blood_type", nullable = false, length = 3)
    String bloodType;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Accept> accepts;


    public Post(@NonNull User user, @NonNull Institution institution,
                @NonNull int bagsNum, @NonNull LocalDateTime startTime, @NonNull String bloodType) {
        this.user = user;
        this.institution = institution;
        this.bagsNum = bagsNum;
        this.startTime = startTime;
        this.bloodType = bloodType;
        this.accepts = new ArrayList<>();
    }

    public Post(int postID, @NonNull User user, @NonNull Institution institution, @NonNull int bagsNum,
                @NonNull LocalDateTime startTime, @NonNull String bloodType) {
        this.postID = postID;
        this.user = user;
        this.institution = institution;
        this.bagsNum = bagsNum;
        this.startTime = startTime;
        this.bloodType = bloodType;
        this.accepts = new ArrayList<>();
    }

    public Post(@NonNull User user, @NonNull Institution institution, @NonNull String bloodType) {
        this.user = user;
        this.institution = institution;
        this.bloodType = bloodType;
        this.accepts = new ArrayList<>();
    }
}

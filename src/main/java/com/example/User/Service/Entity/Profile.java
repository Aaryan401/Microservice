package com.example.User.Service.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="profile")
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false,unique = true)
    private String mobile;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String pinCode;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate(){
        this.createdDate=LocalDateTime.now();
        this.updatedDate=LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedDate=LocalDateTime.now();
    }

    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="user_id",unique = true)
    private User user;
}

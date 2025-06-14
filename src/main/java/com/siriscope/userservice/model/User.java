package com.siriscope.userservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "\"user\"")
public class User {

    @Column(unique = true)
    private String email;

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private Long mobileNumber;
}

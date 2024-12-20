package com.baukur.api.user.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

    public User(UserDetailsImpl user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Column(name = "iconid")
    private Long iconId;

}

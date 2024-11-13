package com.baukur.api.user.domain;

import lombok.Data;

@Data
public class LoggedInUser {

    private Long id;
    private String email;

    public LoggedInUser(UserDetailsImpl user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }
}

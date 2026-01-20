package com.example.user_service.dto;

import com.example.user_service.entity.AppUser;

public class AuthResponse {

    private String id;
    private String name;
    private String email;
    private String role;
    private String token;

    public AuthResponse(AppUser user, String token) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }
}

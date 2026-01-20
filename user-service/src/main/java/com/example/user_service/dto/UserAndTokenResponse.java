package com.example.user_service.dto;

import com.example.user_service.entity.AppUser;

public class UserAndTokenResponse {
    private UserResponse user;
    private String token;

    public UserAndTokenResponse(AppUser user, String token) {
        this.user = new UserResponse(user.getId(), user.getName(), user.getEmail());
        this.token = token;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
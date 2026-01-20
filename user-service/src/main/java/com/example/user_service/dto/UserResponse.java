package com.example.user_service.dto;

public class UserResponse {
    private String id;
    private String name;
    private String email;

    public UserResponse(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /************* ✨ Windsurf Command ⭐ *************/
    /**
     * Gets the user's email address.
     * 
     * @return the user's email address
     */
    /******* bcb09ea6-4812-410b-b9da-c68f39d9574c *******/
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
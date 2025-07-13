package com.appfilm.appfilm.dto;

public class AuthResponseDto {

    private Long id;
    private String username;

    public AuthResponseDto() {}

    public AuthResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}


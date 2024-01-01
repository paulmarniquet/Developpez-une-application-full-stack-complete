package com.openclassrooms.mddapi.dto;

import lombok.Getter;

@Getter
public class JwtTokenDto {
    private String token;

    public JwtTokenDto(String token) {
        this.token = token;
    }

    public JwtTokenDto() {}
}
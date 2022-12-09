package com.davidminkovski.certifications.dto;

import lombok.Data;

@Data
public class JWTAuthResponse {

    private String accessToken;
    private final String tokenType = "Bearer";

    public JWTAuthResponse(String accessToken){
        this.accessToken = accessToken;
    }
}

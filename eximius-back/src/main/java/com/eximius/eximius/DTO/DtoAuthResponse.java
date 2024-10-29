package com.eximius.eximius.DTO;

import lombok.Data;

import java.util.List;

//DTO encargado de devolver la informacion con el token y e tipo que tenga este
@Data
public class DtoAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer ";
    private List<String> roles;



    public DtoAuthResponse(String accessToken, List<String> roles) {
        this.accessToken = accessToken;
        this.roles = roles;
    }
}

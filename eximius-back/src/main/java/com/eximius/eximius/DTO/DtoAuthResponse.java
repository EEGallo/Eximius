package com.eximius.eximius.DTO;

import lombok.Data;

//DTO encargado de devolver la informacion con el token y e tipo que tenga este
@Data
public class DtoAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer ";


    public DtoAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}

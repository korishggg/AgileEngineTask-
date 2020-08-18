package com.example.demo.api.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgileEngineAuthorizationResponse {
    private String auth;
    private String token;
}

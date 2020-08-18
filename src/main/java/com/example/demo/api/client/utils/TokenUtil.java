package com.example.demo.api.client.utils;

import com.example.demo.api.client.request.AgileEngineAuthorizationRequest;
import com.example.demo.api.client.response.AgileEngineAuthorizationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TokenUtil {
    @Value("${agileengine.api.key}")
    private String apiKey;

    private RestTemplate restTemplate;

    public TokenUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AgileEngineAuthorizationResponse retrieveToken() {
        AgileEngineAuthorizationRequest request = new AgileEngineAuthorizationRequest(apiKey);

        ResponseEntity<AgileEngineAuthorizationResponse> responseEntity = restTemplate
                .postForEntity(
                        "http://interview.agileengine.com/auth",
                        request,
                        AgileEngineAuthorizationResponse.class
                );
        return responseEntity.getBody();
    }
}

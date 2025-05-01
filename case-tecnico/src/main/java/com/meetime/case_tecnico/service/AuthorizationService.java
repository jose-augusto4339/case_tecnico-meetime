package com.meetime.case_tecnico.service;

import com.meetime.case_tecnico.config.HubspotProperties;
import com.meetime.case_tecnico.dto.TokenResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthorizationService {

    private final HubspotProperties hubProps;

    private final RestTemplate restTemplate = new RestTemplate();

    public AuthorizationService(HubspotProperties hubProps){
        this.hubProps = hubProps;
    }

    public String generateAuthorizationUrl() {
        return UriComponentsBuilder.fromUriString("https://app.hubspot.com/oauth/authorize")
                .queryParam("client_id", hubProps.getClientId())
                .queryParam("redirect_uri", hubProps.getRedirectUri())
                .queryParam("optional_scope", hubProps.getOptionalScope())
                .queryParam("state", hubProps.getState())
                .build()
                .toUriString();
    }

    public String exchangeCodeForAccessToken(String code) {
        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "authorization_code");
        body.put("client_id", hubProps.getClientId());
        body.put("client_secret", hubProps.getClientSecret());
        body.put("redirect_uri", hubProps.getRedirectUri());
        body.put("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<TokenResponseDTO> response = restTemplate.postForEntity("https://api.hubapi.com/oauth/v1/token", request, TokenResponseDTO.class);
        return response.getBody() != null ? response.getBody().getAccessToken() : null;
    }
}


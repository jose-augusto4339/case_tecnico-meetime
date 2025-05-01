package com.meetime.case_tecnico.service;

import com.meetime.case_tecnico.config.HubspotProperties;
import com.meetime.case_tecnico.dto.TokenResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
                .queryParam("scope", hubProps.getScope())
                .queryParam("state", UUID.randomUUID().toString())
                .build()
                .toUriString();
    }

    public String exchangeCodeForAccessToken(String code) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", hubProps.getClientId());
        body.add("client_secret", hubProps.getClientSecret());
        body.add("redirect_uri", hubProps.getRedirectUri());
        body.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<TokenResponseDTO> response = restTemplate.postForEntity("https://api.hubapi.com/oauth/v1/token", request, TokenResponseDTO.class);
        return response.getBody() != null ? response.getBody().getAccessToken() : null;
    }
}


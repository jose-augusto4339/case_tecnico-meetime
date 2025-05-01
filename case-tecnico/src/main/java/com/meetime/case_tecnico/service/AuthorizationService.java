package com.meetime.case_tecnico.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.meetime.case_tecnico.config.HubspotProperties;
import com.meetime.case_tecnico.dto.TokenResponseDTO;
import jakarta.servlet.http.HttpSession;
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
import java.util.concurrent.TimeUnit;

@Service
public class AuthorizationService {

    private final HubspotProperties hubProps;

    private final RestTemplate restTemplate = new RestTemplate();

    private final TokenService tokenService;

    public AuthorizationService(HubspotProperties hubProps, TokenService tokenService){
        this.hubProps = hubProps;
        this.tokenService = tokenService;
    }

    private Cache<String, String> stateCache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES) // Expira em 10 minutos
            .build();

    public String generateAuthorizationUrl() {
        String state = UUID.randomUUID().toString();

        stateCache.put(state, "user-state");

        return UriComponentsBuilder.fromUriString(hubProps.getAuthorizeUri())
                .queryParam("client_id", hubProps.getClientId())
                .queryParam("redirect_uri", hubProps.getRedirectUri())
                .queryParam("scope", hubProps.getScope())
                .queryParam("state",  state)
                .build()
                .toUriString();
    }

    public String exchangeCodeForAccessToken(String code, String state) {
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

        if (response.getBody() != null) {
            String token = response.getBody().getAccessToken();
            tokenService.storeToken(state, token);
            return token;
        }
        return null;
    }

    public String getStateData(String state) {
        return stateCache.getIfPresent(state);
    }
}


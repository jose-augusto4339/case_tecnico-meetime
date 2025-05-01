package com.meetime.case_tecnico.service;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final Cache<String, String> tokenCache;

    public TokenService(Cache<String, String> tokenCache) {
        this.tokenCache = tokenCache;
    }

    public void storeToken(String state, String accessToken) {
        tokenCache.put(state, accessToken);
    }

    public String getToken(String state) {
        return tokenCache.getIfPresent(state);
    }

    public void removeToken(String state) {
        tokenCache.invalidate(state);
    }
}

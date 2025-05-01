package com.meetime.case_tecnico.controller;

import com.meetime.case_tecnico.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authService;

    @GetMapping("/authorization-url")
    public ResponseEntity<String> generateAuthorization(){
        String authorizationUrl = authService.generateAuthorizationUrl();
        return ResponseEntity.ok(authorizationUrl);
    }

    @GetMapping("/oauth-callback")
    public ResponseEntity<String> handleOAuthCallback(@RequestParam("code") String code) {
        String accessToken = authService.exchangeCodeForAccessToken(code);
        return ResponseEntity.ok("Access token: " + accessToken);
    }
}

package com.meetime.case_tecnico.controller;

import com.meetime.case_tecnico.dto.WebhookEventDTO;
import com.meetime.case_tecnico.exception.InvalidSubscriptionTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/webhook")
public class WebhookControler {

    @PostMapping("/events")
    public ResponseEntity<Void> handleWebhook(@RequestBody WebhookEventDTO event) {
        if (!"contact.creation".equals(event.getSubscriptionType())) {
            throw new InvalidSubscriptionTypeException("Invalid subscription type!");
        }
        return ResponseEntity.ok().build();
    }

}

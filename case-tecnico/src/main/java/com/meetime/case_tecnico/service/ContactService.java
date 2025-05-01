package com.meetime.case_tecnico.service;

import com.meetime.case_tecnico.config.HubspotProperties;
import com.meetime.case_tecnico.dto.ContactDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ContactService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final HubspotProperties hubProps;

    public ContactService(HubspotProperties hubProps){
        this.hubProps = hubProps;
    }

    public String createContact(ContactDTO contactRequest) {
        String url = hubProps.getApiURL() + "/contacts";

        Map<String, Object> properties = new HashMap<>();
        properties.put("email", contactRequest.getEmail());
        properties.put("firstname", contactRequest.getFirstName());
        properties.put("lastname", contactRequest.getLastName());

        Map<String, Object> body = new HashMap<>();
        body.put("properties", properties);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(hubProps.getAccessToken());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            return response.getBody();
        } catch (HttpClientErrorException e) {
            return "Erro ao criar contato: " + e.getResponseBodyAsString();
        }
    }
}

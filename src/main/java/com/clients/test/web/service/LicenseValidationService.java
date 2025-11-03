package com.clients.test.web.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class LicenseValidationService {

    private static final String LICENSE_API_URL = "http://localhost:8081/licenses";

    public boolean isLicenseValid(String licenseNumber) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(LICENSE_API_URL + licenseNumber, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Object valid = response.getBody().get("valide");
                return valid != null && Boolean.parseBoolean(valid.toString());
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification du permis : " + e.getMessage());
        }
        return false;
    }
}

package com.alikmndlu.authservice.service.impl;

import com.alikmndlu.authservice.dto.UserCredentialsDto;
import com.alikmndlu.authservice.service.AuthService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;

    @Override
    public boolean isUserExistsWithCredentials(UserCredentialsDto userCredentialsDto) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(
                new ObjectMapper().writeValueAsString(userCredentialsDto), headers
        );

        ResponseEntity<?> loginResponse = restTemplate.postForEntity(
                "http://localhost:9001/users/check-user-exists",
                entity,
                String.class
        );
        return loginResponse.getBody().equals("YES");
    }

    @Override
    public String createJwtForAuthenticatedUser(UserCredentialsDto userCredentialsDto) {
        return JWT.create()
                .withIssuer("auth0")
                .withSubject(userCredentialsDto.getEmailAddress())
                .sign(Algorithm.HMAC256("secret-key"));
    }
}
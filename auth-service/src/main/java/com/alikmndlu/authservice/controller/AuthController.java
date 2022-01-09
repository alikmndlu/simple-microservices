package com.alikmndlu.authservice.controller;

import com.alikmndlu.authservice.dto.UserCredentialsDto;
import com.alikmndlu.authservice.service.AuthService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> loginHandler(@RequestBody UserCredentialsDto userCredentialsDto) throws Exception {

        boolean userExists = authService.isUserExistsWithCredentials(userCredentialsDto);
        return userExists ?
                ResponseEntity.ok().body(Map.of("token", authService.createJwtForAuthenticatedUser(userCredentialsDto))) :
                ResponseEntity.badRequest().build();
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(@RequestHeader(name = "Authorization", required = false) String token){
        if (token == null)
            return ResponseEntity.badRequest().build();
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret-key")).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            log.info("Valid Token Sent To The Server, {}", jwt.getSubject());
            return ResponseEntity.ok().body(jwt.getSubject());
        } catch (JWTVerificationException exception){
            log.info("Invalid Token Sent To The Server");
            return ResponseEntity.badRequest().build();
        }
    }
}

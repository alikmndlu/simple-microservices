package com.alikmndlu.authservice.controller;

import com.alikmndlu.authservice.dto.UserCredentialsDto;
import com.alikmndlu.authservice.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
                ResponseEntity.ok().build() :
                ResponseEntity.badRequest().build();
    }
}

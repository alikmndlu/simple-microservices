package com.alikmndlu.user.controller;

import com.alikmndlu.user.dto.UserAddressesListDto;
import com.alikmndlu.user.dto.UserCredentialsDto;
import com.alikmndlu.user.model.User;
import com.alikmndlu.user.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        log.info("Inside saveUser method of UserController");
        user = userService.saveUser(user);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }

    // Get User Without Addresses
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserWithoutAddresses(@PathVariable Long id) {
        log.info("Inside getUserWithoutAddresses method of UserController");
        User user = userService.findUserById(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(user);
    }

    // Get User With Addresses List
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserWithAddresses(
            @PathVariable Long id,
            @RequestHeader(name = "Authorization", required = false) String token) {
        log.info("Inside getUserWithAddresses method of UserController");

        if (token == null)
            return ResponseEntity.badRequest().build();

        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("secret-key")).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            log.info("Valid Token Sent To The Server, {}", jwt.getSubject());
        } catch (JWTVerificationException exception){
            log.info("Invalid Token Sent To The Server");
            return ResponseEntity.badRequest().build();
        }

        log.info("Inside getUserWithAddresses method of UserController");
        return ResponseEntity.ok().body(userService.getUserWithAddresses(id));
    }

    // Check User Exists With Credentials Or Not
    @PostMapping("/check-user-exists")
    public ResponseEntity<?> isUserExistsWithEmailAddressAndPassword(@RequestBody UserCredentialsDto userCredentialsDto) {
        Optional<User> user = userService.findByEmailAddressAndPassword(
                userCredentialsDto.getEmailAddress(), userCredentialsDto.getPassword()
        );

        if (user.isEmpty()) {
            return ResponseEntity.ok().body("NO");
        } else {
            return ResponseEntity.ok().body("YES");
        }
    }
}

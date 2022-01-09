package com.alikmndlu.user.controller;

import com.alikmndlu.user.dto.UserAddressesListDto;
import com.alikmndlu.user.dto.UserCredentialsDto;
import com.alikmndlu.user.model.User;
import com.alikmndlu.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Inside saveUser method of UserController");
        return userService.saveUser(user);
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
    public UserAddressesListDto getUserWithAddresses(@PathVariable Long id) {
        log.info("Inside getUserWithAddresses method of UserController");
        return userService.getUserWithAddresses(id);
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

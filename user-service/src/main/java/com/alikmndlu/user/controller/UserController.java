package com.alikmndlu.user.controller;

import com.alikmndlu.user.dto.UserAddressesListDto;
import com.alikmndlu.user.model.User;
import com.alikmndlu.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public User getUserWithoutAddresses(@PathVariable Long id) {
        log.info("Inside getUserWithoutAddresses method of UserController");
        return userService.findUserById(id);
    }


    // Get User With Addresses List
    @GetMapping("/{id}")
    public UserAddressesListDto getUserWithAddresses(@PathVariable Long id) {
        log.info("Inside getUserWithAddresses method of UserController");
        return userService.getUserWithAddresses(id);
    }
}

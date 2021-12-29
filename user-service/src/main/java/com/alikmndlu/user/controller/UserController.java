package com.alikmndlu.user.controller;

import com.alikmndlu.user.model.User;
import com.alikmndlu.user.service.UserService;
import lombok.AllArgsConstructor;
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
    public User registerUser(@RequestBody User user){
        log.info("Inside registerUser method of UserController");
        return userService.registerUser(user);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id){
        log.info("Inside findUserById method of UserController");
        return userService.findUserById(id);
    }
}

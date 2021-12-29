package com.alikmndlu.user.service.impl;

import com.alikmndlu.user.model.User;
import com.alikmndlu.user.repository.UserRepository;
import com.alikmndlu.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        log.info("Inside registerUser method of UserService");
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        log.info("Inside findUserById method of UserService");
        return userRepository.findById(id).orElse(null);
    }
}

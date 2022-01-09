package com.alikmndlu.address.service.impl;

import com.alikmndlu.address.dto.User;
import com.alikmndlu.address.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;

    @Override
    public Optional<User> findUserById(Long id) {
        ResponseEntity<User> user = restTemplate.getForEntity("http://localhost:9001/users/get/" + id, User.class);
        return Optional.ofNullable(user.getBody());
    }
}

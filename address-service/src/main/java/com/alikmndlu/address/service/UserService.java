package com.alikmndlu.address.service;

import com.alikmndlu.address.dto.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Long id);
}

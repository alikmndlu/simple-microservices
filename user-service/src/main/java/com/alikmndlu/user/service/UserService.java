package com.alikmndlu.user.service;

import com.alikmndlu.user.dto.UserAddressesListDto;
import com.alikmndlu.user.model.User;

import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    User findUserById(Long id);

    UserAddressesListDto getUserWithAddresses(Long id);

    Optional<User> findByEmailAddressAndPassword(String emailAddress, String password);
}

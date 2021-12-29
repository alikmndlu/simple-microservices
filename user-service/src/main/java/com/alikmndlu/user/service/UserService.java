package com.alikmndlu.user.service;

import com.alikmndlu.user.model.User;

public interface UserService {

    User registerUser(User user);

    User findUserById(Long id);

}

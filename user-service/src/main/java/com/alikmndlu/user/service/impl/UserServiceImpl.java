package com.alikmndlu.user.service.impl;

import com.alikmndlu.user.dto.Address;
import com.alikmndlu.user.dto.UserAddressesListDto;
import com.alikmndlu.user.model.User;
import com.alikmndlu.user.repository.UserRepository;
import com.alikmndlu.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        log.info("Inside registerUser method of UserService");
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        log.info("Inside findUserById method of UserService");
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserAddressesListDto getUserWithAddresses(Long id) {
        log.info("Inside getUserWithAddresses method of UserService");
        UserAddressesListDto dto = new UserAddressesListDto();

        User user = userRepository.findById(id).get();
        List<Address> addresses = restTemplate.getForObject(
                "http://localhost:9002/addresses/user/" + user.getId(),
                List.class
        );

        dto.setUser(user);
        dto.setAddress(addresses);

        return dto;
    }

    @Override
    public Optional<User> findByEmailAddressAndPassword(String emailAddress, String password) {
        User user = userRepository.findByEmailAddressAndPassword(emailAddress, password);
        if (user == null) return Optional.empty();
        return Optional.of(user);
    }
}

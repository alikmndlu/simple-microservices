package com.alikmndlu.address.service.impl;

import com.alikmndlu.address.dto.AddressUserDto;
import com.alikmndlu.address.dto.User;
import com.alikmndlu.address.model.Address;
import com.alikmndlu.address.repository.AddressRepository;
import com.alikmndlu.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final RestTemplate restTemplate;

    @Override
    public Address saveAddress(Address address) {
        log.info("Inside saveAddress method of AddressService");
        return addressRepository.save(address);
    }

    @Override
    public AddressUserDto findAddressByIdWithUserDetails(Long id) {
        log.info("Inside findAddressById method of AddressService");
        Address address = addressRepository.findById(id).orElse(null);
        ResponseEntity<User> user = restTemplate.getForEntity(
                "http://localhost:9001/users/get/" + address.getUserId(),
                User.class
        );
        return new AddressUserDto(address, user.getBody());
    }

    @Override
    public List<Address> getAddressesByUserId(Long userId) {
        return addressRepository.findAddressesByUserId(userId);
    }
}

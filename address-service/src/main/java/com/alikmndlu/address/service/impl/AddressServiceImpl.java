package com.alikmndlu.address.service.impl;

import com.alikmndlu.address.model.Address;
import com.alikmndlu.address.repository.AddressRepository;
import com.alikmndlu.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address saveAddress(Address address) {
        log.info("Inside saveAddress method of AddressService");
        return addressRepository.save(address);
    }

    @Override
    public Address findUserById(Long id) {
        log.info("Inside findUserById method of AddressService");
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public List<Address> getAddressesByUserId(Long userId) {
        return addressRepository.findAddressesByUserId(userId);
    }
}

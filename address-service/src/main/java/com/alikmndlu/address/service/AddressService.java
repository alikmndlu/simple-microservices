package com.alikmndlu.address.service;

import com.alikmndlu.address.model.Address;

import java.util.List;

public interface AddressService {

    Address saveAddress(Address address);

    Address findUserById(Long id);

    List<Address> getAddressesByUserId(Long userId);
}

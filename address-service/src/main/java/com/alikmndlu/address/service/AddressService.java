package com.alikmndlu.address.service;

import com.alikmndlu.address.dto.AddressUserDto;
import com.alikmndlu.address.model.Address;

import java.util.List;

public interface AddressService {

    Address saveAddress(Address address);

    AddressUserDto findAddressByIdWithUserDetails(Long id);

    List<Address> getAddressesByUserId(Long userId);
}

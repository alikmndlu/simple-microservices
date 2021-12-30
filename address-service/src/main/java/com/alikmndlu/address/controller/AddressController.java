package com.alikmndlu.address.controller;

import com.alikmndlu.address.model.Address;
import com.alikmndlu.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/")
    public Address saveUser(@RequestBody Address address){
        log.info("Inside saveUser method of AddressController");
        return addressService.saveAddress(address);
    }

    @GetMapping("/{id}")
    public Address findUserById(@PathVariable Long id){
        log.info("Inside findUserById method of AddressController");
        return addressService.findUserById(id);
    }

    @GetMapping("/user/{id}")
    public List<Address> getAddressesByUserId(@PathVariable("id") Long userId){
        log.info("Inside getAddressesByUserId method of AddressController");
        return addressService.getAddressesByUserId(userId);
    }
}

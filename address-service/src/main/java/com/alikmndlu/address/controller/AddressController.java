package com.alikmndlu.address.controller;

import com.alikmndlu.address.dto.AddressUserDto;
import com.alikmndlu.address.model.Address;
import com.alikmndlu.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/")
    public ResponseEntity<Address> saveUser(@RequestBody Address address){
        log.info("Inside saveUser method of AddressController");
        address = addressService.saveAddress(address);
        return ResponseEntity.created(URI.create("/addresses/" + address.getId())).body(address);
    }

    // Get Address With Id
    @GetMapping("/{id}")
    public ResponseEntity<AddressUserDto> findAddressByIdWithUserDetails(@PathVariable Long id){
        log.info("Inside findAddressByIdWithUserDetails method of AddressController");
        return ResponseEntity.ok().body(addressService.findAddressByIdWithUserDetails(id));
    }

    // Find All Addresses That Belongs To User
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Address>> getAddressesByUserId(@PathVariable("id") Long userId){
        log.info("Inside getAddressesByUserId method of AddressController");
        return ResponseEntity.ok().body(addressService.getAddressesByUserId(userId));
    }
}

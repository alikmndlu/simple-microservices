package com.alikmndlu.address.controller;

import com.alikmndlu.address.dto.AddressUserDto;
import com.alikmndlu.address.dto.User;
import com.alikmndlu.address.model.Address;
import com.alikmndlu.address.service.AddressService;
import com.alikmndlu.address.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<Address> saveAddress(
            @RequestBody Address address,
            @RequestHeader(name = "Authorization", required = false) String token){

        if (token == null)
            return ResponseEntity.badRequest().build();

        try {

            DecodedJWT jwt = JWT.require(
                    Algorithm.HMAC256("secret-key")
            ).withIssuer("auth0").build().verify(token);

            User user = userService.findUserById(address.getUserId()).get();
            if (!jwt.getSubject().equals(user.getEmailAddress()))
                return ResponseEntity.badRequest().build();

            address = addressService.saveAddress(address);
            return ResponseEntity.created(URI.create("/addresses/" + address.getId())).body(address);

        } catch (JWTVerificationException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get Address With Id
    @GetMapping("/{id}")
    public ResponseEntity<AddressUserDto> findAddressByIdWithUserDetails(
            @PathVariable Long id,
            @RequestHeader(name = "Authorization", required = false) String token){

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

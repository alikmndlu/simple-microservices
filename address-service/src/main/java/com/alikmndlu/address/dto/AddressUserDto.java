package com.alikmndlu.address.dto;

import com.alikmndlu.address.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressUserDto {

    private Address address;
    
    private User user;
}

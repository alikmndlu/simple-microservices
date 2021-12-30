package com.alikmndlu.user.dto;

import com.alikmndlu.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressesListDto {

    private User user;

    private List<AddressDto> address;
}
